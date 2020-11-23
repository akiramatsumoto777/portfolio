package com.akm.portfolio.controller.dto.rest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class CurrentDropData implements Serializable{

	/**
	 * generate serialVersionUID.
	 */
	private static final long serialVersionUID = -1816934813928749236L;

	Map<Integer, Integer> dropValues = new HashMap<Integer, Integer>();
	
}
