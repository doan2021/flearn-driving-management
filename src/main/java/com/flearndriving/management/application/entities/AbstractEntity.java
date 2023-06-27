package com.flearndriving.management.application.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@MappedSuperclass
@FieldNameConstants
public class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "boolean default false")
	private boolean isDelete;

	@CreatedBy
	@Column(length = 36)
	private String createdBy;

	@CreatedDate
	private Date createdDate;

	@LastModifiedBy
	@Column(length = 36)
	private String lastModifiedBy;

	@LastModifiedDate
	private Date lastModifiedDate;
}
