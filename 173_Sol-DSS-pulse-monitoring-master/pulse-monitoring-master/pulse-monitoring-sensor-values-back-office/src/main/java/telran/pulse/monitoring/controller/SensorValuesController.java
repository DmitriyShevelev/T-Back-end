package telran.pulse.monitoring.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import telran.pulse.monitoring.service.SensorValuesService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorValuesController {
	@Autowired
	SensorValuesService sensorValuesService;

	@GetMapping("/average/{sensorId}")
	int getAverageSensorDates(@PathVariable("sensorId") int sensorId,
			@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime from,
			@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime to) {
		return sensorValuesService.getAverageDates(sensorId, from, to);
	}
	@GetMapping("/jumps/{sensorId}")
	long getCountJumpsSensorDates(@PathVariable("sensorId") int sensorId,
			@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime from,
			@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime to) {
		return sensorValuesService.getJumpsCountDates(sensorId, from, to);
	}
}
