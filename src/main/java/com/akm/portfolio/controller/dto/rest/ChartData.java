package com.akm.portfolio.controller.dto.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChartData implements Serializable{

	/**
	 * generate serialVersionUID.
	 */
	private static final long serialVersionUID = 5332384516487435050L;

	List<String> labels = new ArrayList<>();
	List<Integer> values = new ArrayList<>();
	
}
