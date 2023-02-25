package telran.spring;

import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.spring.aop.SendersStatisticsAspect;
import telran.spring.service.Sender;
import telran.spring.service.SenderService;

@SpringBootApplication
public class SpringIntroductionApplication {
	static Logger LOG = LoggerFactory.getLogger(SpringIntroductionApplication.class);
	@Autowired
	SendersStatisticsAspect statisticsAspect;
	static ConfigurableApplicationContext ctx;
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		ctx = SpringApplication.run(SpringIntroductionApplication.class, args);

		
		Sender sender = ctx.getBean(Sender.class);
		Map<String, SenderService> senderServices = sender.getServices();
		LOG.info("Number of sender services is {}", senderServices.size());
		System.out.println("Enter exit for stopping application");
		while (true) {
			System.out.println("Enter sender type");
			String line = scanner.nextLine();
			if (line.equals("exit")) {

				break;
			}

			SenderService service = senderServices.get(line);
			if (service == null) {
				System.out.printf("Service %s is not implemented yet\n", line);
				LOG.error("  service {} not found", line);
				continue;
			}
			System.out.println("Enter message");
			line = scanner.nextLine();
			service.send(line);

		}

	}

	@PostConstruct
	void init() {
		LOG.debug("Post Construct method has been called");
		System.out.println("Application context has been created");
	}

	@PreDestroy
	void preDestroy() {
		
		System.out.println(statisticsAspect.getStatistics());
	}

}
