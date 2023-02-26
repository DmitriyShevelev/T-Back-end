package telran.spring.service.impl;

import org.slf4j.*;
import org.springframework.stereotype.Service;

import telran.spring.service.SenderService;
@Service
public class SenderServiceSms implements SenderService {
static Logger LOG = LoggerFactory.getLogger(SenderServiceSms.class);
	@Override
	public void send(String message) {
		System.out.println("sms message: " + message);
		

	}

	@Override
	public String getType() {
		LOG.debug("getType for SMS server has been called");
		return "sms";
	}

}
