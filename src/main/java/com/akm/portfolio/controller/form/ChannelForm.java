package com.akm.portfolio.controller.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ChannelForm implements Serializable {

	/**
	 * generate serialVersionUID.
	 */
	private static final long serialVersionUID = 8959290760916683098L;

	private Integer id;
	
	@NotBlank(message = "Required iput")
	private String name;

	@NotBlank(message = "Required iput")
	private String location;

	private String machineCount;

}
