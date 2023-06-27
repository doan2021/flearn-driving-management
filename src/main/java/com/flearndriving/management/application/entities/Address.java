package com.flearndriving.management.application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Address extends AbstractEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
    private String line;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = Fields.id)
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = Fields.id)
    private Customer customer;
}
