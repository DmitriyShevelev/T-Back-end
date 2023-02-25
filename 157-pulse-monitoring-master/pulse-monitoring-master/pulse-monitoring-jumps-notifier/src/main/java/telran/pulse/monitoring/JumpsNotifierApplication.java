package telran.pulse.monitoring;

import java.util.function.Consumer;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import telran.pulse.monitoring.dto.DoctorPatientData;
import telran.pulse.monitoring.dto.SensorJump;

@SpringBootApplication
public class JumpsNotifierApplication {
static Logger LOG = LoggerFactory.getLogger(JumpsNotifierApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(JumpsNotifierApplication.class, args);

	}
	@Value("${app.mail.subject: Critical Pulse Jump}")
	String subject;
	@Autowired
	JavaMailSender jms;
	@Autowired
	DataProviderClient client;
	@Bean
	Consumer<SensorJump> criticalJumpsConsumer() {
		return this::jumpsProcessing;
	}
	void jumpsProcessing(SensorJump sensorJump) {
		LOG.trace("received sensor id {} previous value {} current value {}",
				sensorJump.sensorId, sensorJump.previousValue, sensorJump.currentValue);
		DoctorPatientData data = client.getData(sensorJump.sensorId);
		LOG.debug("data received is {}", data);
		sendMail(data, sensorJump);
		
	}
	private void sendMail(DoctorPatientData data, SensorJump sensorJump) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(data.email);
		message.setSubject(subject);
		message.setText(String.format("Dear %s,\nYour patient %s had the critical pulse"
				+ " jump\nPrevious pulse value %d; current pulse value %d\n",
				data.doctorName, data.patientName, sensorJump.previousValue,
				sensorJump.currentValue));
		jms.send(message);
		LOG.debug("mail sent to {}", data.email);
		
		
	}

}
