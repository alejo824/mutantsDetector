package com.service.mutantdetector.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.mutantdetector.dto.AdnDTO;
import com.service.mutantdetector.dto.ResponseDTO;
import com.service.mutantdetector.model.Adn;
import com.service.mutantdetector.service.api.AdnServiceAPI;
import com.service.mutantdetector.service.impl.MutantDetectorServiceImpl;

@RestController
@RequestMapping(value = "/")
@CrossOrigin("*")
public class AdnRestController {

	@Autowired
	private AdnServiceAPI adnServiceAPI;

	@Autowired
	private MutantDetectorServiceImpl mutantDetectorServiceImpl;

	@PostMapping(value = "/mutant/")
	public ResponseEntity mutantDetector(@RequestBody Map<String, Object> body) throws Exception {

		ResponseEntity responseEntity = null;

		if (!body.isEmpty()) {
			List<String> stringArray = (List<String>) body.get("dna");
			String[] dna = stringArray.toArray(new String[0]);

			if (mutantDetectorServiceImpl.isMutant(dna)) {
				Adn adn = new Adn();
				adn.setType(1L);
				adnServiceAPI.save(adn);
				responseEntity = ResponseEntity.ok(HttpStatus.OK);
			} else {
				Adn adn = new Adn();
				adn.setType(0L);
				adnServiceAPI.save(adn);
				responseEntity = (ResponseEntity) ResponseEntity.status(HttpStatus.FORBIDDEN);
			}

		}
		return responseEntity;
	}

	@PostMapping(value = "/stats/")
	public ResponseDTO getAllForType() throws Exception {
		List<AdnDTO> humans = adnServiceAPI.getAll(0);
		List<AdnDTO> mutants = adnServiceAPI.getAll(1);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setCount_human_dna(humans.size());
		responseDTO.setCount_mutant_dna(mutants.size());

		if (humans.size() > 0) {

			responseDTO.setRatio(mutants.size() / humans.size());
		}
		return responseDTO;
	}
}
