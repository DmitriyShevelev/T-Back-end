package telran.pulse.monitoring;

import java.util.function.Consumer;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import telran.pulse.monitoring.dto.Sensor;
import telran.pulse.monitoring.entities.SensorDoc;
import telran.pulse.monitoring.repo.SensorRepository;

@SpringBootApplication
public class AveragePopulatorAppl {
	static Logger LOG = LoggerFactory.getLogger(AveragePopulatorAppl.class);
	@Autowired
	SensorRepository sensorRepository;

	public static void main(String[] args) {
		SpringApplication.run(AveragePopulatorAppl.class, args);

	}

	@Bean
	Consumer<Sensor> averageConsumer() {
		return this::averageProcessing;
	}

	void averageProcessing(Sensor sensor) {
		LOG.debug("received sesnsor id {} with average value {}", sensor.id, sensor.value);
		sensorRepository.insert(SensorDoc.build(sensor));
	}

}
