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
 * Problem generated by hbm2java
 */
@Entity
@Table(name = "problem")
public class Problem implements java.io.Serializable {

	private Integer problemId;
	private String problemDescription;
//	private Vector<Patientdiagnosis> patientdiagnosises = new Vector<Patientdiagnosis>();

	public Problem() {
	}

	public Problem(String problemDescription) {
		this.problemDescription = problemDescription;
	}

//	public Problem(String problemDescription, Vector<Patientdiagnosis> patientdiagnosises) {
//		this.problemDescription = problemDescription;
//		this.patientdiagnosises = patientdiagnosises;
//	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ProblemID", unique = true, nullable = false)
	public Integer getProblemId() {
		return this.problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}

	@Column(name = "ProblemDescription", nullable = false)
	public String getProblemDescription() {
		return this.problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "problem")
//	public Vector<Patientdiagnosis> getPatientdiagnosises() {
//		return this.patientdiagnosises;
//	}
//
//	public void setPatientdiagnosises(Vector<Patientdiagnosis> patientdiagnosises) {
//		this.patientdiagnosises = patientdiagnosises;
//	}

}