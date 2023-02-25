package telran.spring.service.impl;

import org.springframework.stereotype.Service;

import telran.spring.service.SenderService;
@Service
public class SenderServiceInternet implements SenderService {

	@Override
	public void send(String message) {
		System.out.println("Internet message: " + message);

	}

	@Override
	public String getType() {
		
		return "internet";
	}

}
