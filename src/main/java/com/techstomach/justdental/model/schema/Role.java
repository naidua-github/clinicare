package com.techstomach.justdental.model.schema;
// Generated Jan 4, 2017 9:42:35 AM by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name = "Role")
public class Role implements java.io.Serializable {

	private Integer roleId;
	private String name;
	private String description;
//	private Vector<UserRole> userRoles = new Vector<UserRole>();

	public Role() {
	}

	public Role(String name, String description) {
		this.name = name;
		this.description = description;
	}

//	public Role(String name, String description, Vector<UserRole> userRoles) {
//		this.name = name;
//		this.description = description;
//		this.userRoles = userRoles;
//	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "RoleID", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
//	public Vector<UserRole> getUserRoles() {
//		return this.userRoles;
//	}
//
//	public void setUserRoles(Vector<UserRole> userRoles) {
//		this.userRoles = userRoles;
//	}

}
