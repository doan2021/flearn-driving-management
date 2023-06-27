package com.flearndriving.management.application.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Customer extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(unique = true, length = 36)
	private String userName;

	@Column(length = 128, nullable = false)
	private String password;

	@Column
	private String firstName;

	@Column
	private String middleName;

	@Column
	private String lastName;

	@Column
	private Date birthDay;

	@Column
	private Integer gender;

	@Column
	private String email;

	@Column(length = 10)
	private String numberPhone;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = Fields.id)
	private Role role;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(length = 15)
	private String authProvider;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Document> listImages;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<StatusLearn> listStatusLearn;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Address> listAddess;
}
