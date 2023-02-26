package telran.spring.service;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Sender {
	
	private  Map<String, SenderService> services;
	private  final List<SenderService> servicesList;
	
	public Map<String, SenderService> getServices() {
		return services;
	}
	@Autowired
	public Sender(List<SenderService> servicesList) {
		this.servicesList = servicesList;
	}
	@PostConstruct
	void displayMap() {
		services = servicesList.stream().collect(Collectors.toMap(SenderService::getType,
				s -> s));
	}
}
