package com.akm.portfolio.controller.dto.web;

import java.io.Serializable;

import lombok.Data;

@Data
public class PacketCheckRateDisp implements Serializable{

	/**
	 * generate serialVersionUID.
	 */
	private static final long serialVersionUID = -7355700495428183009L;

	private Integer lossRate;
	
}
