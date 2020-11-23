package com.akm.portfolio.controller.dto.web;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChannelEditDisp implements Serializable{

	/**
	 * generate serialVersionUID.
	 */
	private static final long serialVersionUID = -5276674896228155272L;

	private Integer id;
	private String name;
	private Integer machineCount;
	private String location;
	
}
