package com.akm.portfolio.controller.dto.web;

import java.io.Serializable;

import lombok.Data;

@Data
public class PacketCheckDetailDisp implements Serializable{

	/**
	 * generate serialVersionUID.
	 */
	private static final long serialVersionUID = -6714317155097770019L;

	private Integer id;
	private String name;
	private String status;
	private Integer machineCount;
	private String location;
	
}
