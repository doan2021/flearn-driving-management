package com.flearndriving.management.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.management.application.entities.Role;
import com.flearndriving.management.application.respositories.RoleRespository;

@Service
public class RoleServices {

	@Autowired
	private RoleRespository roleRespository;
	
	public List<Role> findAllRole() {
	    List<Role> listRole = roleRespository.findAll();
		if (listRole != null) {
			return listRole;
		}
		return new ArrayList<Role>();
	}
}
