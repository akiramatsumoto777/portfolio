package com.akm.portfolio.domain.model.packet;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DropLog implements Serializable {

	/**
	 * generate serialVersionUID.
	 */
	private static final long serialVersionUID = 4461611070884101316L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL")
	private Long id;
	private Integer channelId;
	private Timestamp timestamp;
	private Integer lossRate;

}
