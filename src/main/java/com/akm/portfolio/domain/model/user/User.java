package com.akm.portfolio.domain.model.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "usr")
public class User implements Serializable {

	/**
	 * generate serialVersionUID.
	 */
	private static final long serialVersionUID = 7039071261488775833L;
	
	@Id
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	private RoleName roleName;

}
