package com.service.mutantdetector.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.service.mutantdetector.commons.GenericServiceImpl;
import com.service.mutantdetector.dto.AdnDTO;
import com.service.mutantdetector.model.Adn;
import com.service.mutantdetector.service.api.AdnServiceAPI;

@Service
public class AdnServiceImpl extends GenericServiceImpl<Adn, AdnDTO> implements AdnServiceAPI {

	@Autowired
	private Firestore firestore;

	@Override
	public CollectionReference getCollection() {
		return firestore.collection("adnType");
	}

}
