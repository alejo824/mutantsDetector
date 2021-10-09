package com.service.mutantdetector.commons;

import java.util.List;
import java.util.Map;

public interface GenericServiceAPI<I,O> {

	String save(I entity, String id) throws Exception;
	
	String save(I entity) throws Exception;
	
	
	O get(String id) throws Exception;
	
	Map<String, Object> getAsMap(String id) throws Exception;
	
	List<O> getAll(int type) throws Exception;
}