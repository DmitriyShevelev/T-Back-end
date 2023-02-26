package telran.pulse.monitoring.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import telran.pulse.monitoring.dto.Sensor;
import telran.pulse.monitoring.entities.SensorList;
import telran.pulse.monitoring.repo.SensorRepository;

@Service
@ManagedResource
public class SensorAvgService {
	static Logger LOG = LoggerFactory.getLogger(SensorAvgService.class);
	@Autowired
	StreamBridge streamBridge;
	@Autowired
SensorRepository sensorRepository;
	Instant timestamp = Instant.now();
	@Value("${app.period.reduction:60000}")
	long reducingPeriod;
	@ManagedOperation
	public long getReducingPeriod() {
		return reducingPeriod;
	}
	@ManagedOperation
	public void setReducingPeriod(long reducingPeriod) {
		this.reducingPeriod = reducingPeriod;
	}
	@Value("${app.size.reduction: 100}")
	int reducingSize;
	@ManagedOperation
	public int getReducingSize() {
		return reducingSize;
	}
	@ManagedOperation
	public void setReducingSize(int reducingSize) {
		this.reducingSize = reducingSize;
	}
	@Bean
	Consumer<Sensor> pulseConsumer() {
		return this::pulseAvgProcessing;
	}
	void pulseAvgProcessing(Sensor sensor) {
		LOG.trace("received sensor id {} value {} ", sensor.id, sensor.value);
		
		SensorList sensorList = sensorRepository.findById(sensor.id).orElse(null);
		
		if (sensorList == null) {
			sensorList = new SensorList(sensor.id);
		} 
		List<Integer> values  = sensorList.getListValues();
		values.add(sensor.value);
		if (checkAverageProcessing(values.size())) {
			averageProcessing(values, sensor.id);
		}
		sensorRepository.save(sensorList);
		
	}
	private void averageProcessing(List<Integer> values, int id) {
		double avg = values.stream().mapToInt(x -> x).average().getAsDouble();
		values.clear();
		streamBridge.send("avg-values-out-0", new Sensor(id, (int)avg));
		LOG.debug("sensor id {} avg value {} has been sent to aeage topic", id, avg);
		timestamp = Instant.now();
		
		
	}
	private boolean checkAverageProcessing(int valuesSize) {
		
		return ChronoUnit.MILLIS.between(timestamp, Instant.now()) > reducingPeriod
				|| valuesSize > reducingSize;
	}
	
	
}
