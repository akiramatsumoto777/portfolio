package com.akm.portfolio.domain.model.packet;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Channel implements Serializable {

	/**
	 * generate serialVersionUID.
	 */
	private static final long serialVersionUID = -3469850894429468557L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer status;
	private Integer machineCount;
	private String location;

}
