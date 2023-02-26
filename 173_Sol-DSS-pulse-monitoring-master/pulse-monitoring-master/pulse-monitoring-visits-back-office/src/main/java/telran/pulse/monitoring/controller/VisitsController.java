package telran.pulse.monitoring.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;

import telran.pulse.monitoring.dto.*;
import telran.pulse.monitoring.service.VisitsService;

@RestController
@RequestMapping("/visits")
public class VisitsController {
	@Autowired
	VisitsService visitsService;
	@PostMapping("/patients")
	PatientData addPatient(@RequestBody PatientData patient) {
		visitsService.addPatient(patient.patientId, patient.patientName);
		return patient;
	}
	@PostMapping ("/doctors")
	DoctorData addDoctor(@RequestBody @Valid DoctorData doctor) {
		visitsService.addDoctor(doctor.email, doctor.name);
		return doctor;
	}
	@PostMapping 
	Map<String, Object> addVisit(@RequestBody Map<String, Object> visit) {
		visitsService.addVisit((Integer)visit.get("patientId"), (String)visit.get("email"),
				LocalDateTime.parse((String) visit.get("date")) );
		return visit;
	}
	
	@GetMapping("/{patientId}")
	List<VisitData> getVisitsPatientDates(@PathVariable int patientId,
			@DateTimeFormat(iso = ISO.DATE_TIME ) LocalDateTime from,
			@DateTimeFormat(iso = ISO.DATE_TIME )LocalDateTime to) {
		return visitsService.getVisits(patientId, from, to);
		
	}
	

}
