package com.techstomach.justdental.model.schema;
// Generated Dec 23, 2016 10:46:00 AM by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "User")
public class User implements java.io.Serializable {

	private int userId;
	private Location location;
	private String firstName;
	private String middleName;
	private String lastName;
	private String userName;
	private String password;
	private String emailAddress;
	private String phoneNumber;
	private String roleId;
	private Integer licenseNumber;
	private Integer applicationName;
	private Integer passwordQuestion;
	private Integer dateCreated;
	private Integer dateModified;
	private Integer modifiedBy;
	private Integer lastLogin;
	private Integer lastPasswordChanged;
	private Integer isActive;
	private Integer numberOfLogins;
	private Integer qualification;
//	private Set userRoles = new HashSet(0);
//	private Set clinicdoctors = new HashSet(0);

	public User() {
	}

	public User(int userId) {
		this.userId = userId;
	}

//	public User(int userId, Location location, String firstName, String middleName, String lastName, String userName,
//			String password, String emailAddress, String phoneNumber, String roleId, Integer licenseNumber,
//			Integer applicationName, Integer passwordQuestion, Integer dateCreated, Integer dateModified,
//			Integer modifiedBy, Integer lastLogin, Integer lastPasswordChanged, Integer isActive,
//			Integer numberOfLogins, Integer qualification, Set userRoles, Set clinicdoctors) {
//		this.userId = userId;
//		this.location = location;
//		this.firstName = firstName;
//		this.middleName = middleName;
//		this.lastName = lastName;
//		this.userName = userName;
//		this.password = password;
//		this.emailAddress = emailAddress;
//		this.phoneNumber = phoneNumber;
//		this.roleId = roleId;
//		this.licenseNumber = licenseNumber;
//		this.applicationName = applicationName;
//		this.passwordQuestion = passwordQuestion;
//		this.dateCreated = dateCreated;
//		this.dateModified = dateModified;
//		this.modifiedBy = modifiedBy;
//		this.lastLogin = lastLogin;
//		this.lastPasswordChanged = lastPasswordChanged;
//		this.isActive = isActive;
//		this.numberOfLogins = numberOfLogins;
//		this.qualification = qualification;
//		this.userRoles = userRoles;
//		this.clinicdoctors = clinicdoctors;
//	}

	@Id

	@Column(name = "UserID", unique = true, nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AddressID")
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Column(name = "FirstName", length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "MiddleName", length = 50)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "LastName", length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "UserName", length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "Password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EmailAddress", length = 50)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name = "PhoneNumber", length = 50)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "RoleID", length = 50)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "LicenseNumber")
	public Integer getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setLicenseNumber(Integer licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	@Column(name = "ApplicationName")
	public Integer getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(Integer applicationName) {
		this.applicationName = applicationName;
	}

	@Column(name = "PasswordQuestion")
	public Integer getPasswordQuestion() {
		return this.passwordQuestion;
	}

	public void setPasswordQuestion(Integer passwordQuestion) {
		this.passwordQuestion = passwordQuestion;
	}

	@Column(name = "DateCreated")
	public Integer getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Integer dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "DateModified")
	public Integer getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Integer dateModified) {
		this.dateModified = dateModified;
	}

	@Column(name = "ModifiedBy")
	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "LastLogin")
	public Integer getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Integer lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "LastPasswordChanged")
	public Integer getLastPasswordChanged() {
		return this.lastPasswordChanged;
	}

	public void setLastPasswordChanged(Integer lastPasswordChanged) {
		this.lastPasswordChanged = lastPasswordChanged;
	}

	@Column(name = "IsActive")
	public Integer getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	@Column(name = "NumberOfLogins")
	public Integer getNumberOfLogins() {
		return this.numberOfLogins;
	}

	public void setNumberOfLogins(Integer numberOfLogins) {
		this.numberOfLogins = numberOfLogins;
	}

	@Column(name = "Qualification")
	public Integer getQualification() {
		return this.qualification;
	}

	public void setQualification(Integer qualification) {
		this.qualification = qualification;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//	public Set getUserRoles() {
//		return this.userRoles;
//	}
//
//	public void setUserRoles(Set userRoles) {
//		this.userRoles = userRoles;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//	public Set getClinicdoctors() {
//		return this.clinicdoctors;
//	}
//
//	public void setClinicdoctors(Set clinicdoctors) {
//		this.clinicdoctors = clinicdoctors;
//	}

}
