package com.service.mutantdetector;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.mutantdetector.service.impl.MutantDetectorServiceImpl;

class MutantDetectorServiceImplTest {

	@Autowired
	private MutantDetectorServiceImpl mutantDetectorServiceImpl;
	
	@Test
	void isMutant() {
		 String[] adn = {"ATGCGG","CATTGC","TTATAT","AGAAGG","CCCCTA","TCACTG"};
		 
		 Assertions.assertEquals(false, mutantDetectorServiceImpl.isMutant(adn));
	}

}
