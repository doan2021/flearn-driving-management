package com.flearndriving.management.application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Province extends AbstractEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
    private String provinceName;
}
