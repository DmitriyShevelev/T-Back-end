package telran.pulse.monitoring;

import java.util.function.Consumer;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import telran.pulse.monitoring.dto.SensorJump;
import telran.pulse.monitoring.entities.JumpDoc;
import telran.pulse.monitoring.repo.JumpsRepository;

@SpringBootApplication
public class JumpsPopulatorApplication {
	@Autowired
	JumpsRepository jumpsRepository;
static Logger LOG = LoggerFactory.getLogger(JumpsPopulatorApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(JumpsPopulatorApplication.class, args);

	}
	@Bean
	Consumer<SensorJump> jumpsConsumer() {
		return this::jumpsProcessing;
	}
	void jumpsProcessing(SensorJump sensorJump) {
		LOG.trace("received sensor id {} previous value {} current value {}",
				sensorJump.sensorId, sensorJump.previousValue, sensorJump.currentValue);
		jumpsRepository.insert(JumpDoc.build(sensorJump));
		
	}

}
