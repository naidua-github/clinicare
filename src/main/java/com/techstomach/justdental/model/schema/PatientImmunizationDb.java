package com.techstomach.justdental.model.schema;
// Generated Nov 19, 2016 4:08:03 PM by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PatientImmunizationDb generated by hbm2java
 */
@Entity
@Table(name = "patient_immunization_db", catalog = "jd_database")
public class PatientImmunizationDb implements java.io.Serializable {

	private Integer patientImmunizationId;
	private ImmunizationDb immunizationDb;
	private PatientDb patientDb;
	private Date dateAdministered;

	public PatientImmunizationDb() {
	}

	public PatientImmunizationDb(ImmunizationDb immunizationDb, PatientDb patientDb, Date dateAdministered) {
		this.immunizationDb = immunizationDb;
		this.patientDb = patientDb;
		this.dateAdministered = dateAdministered;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "PatientImmunizationID", unique = true, nullable = false)
	public Integer getPatientImmunizationId() {
		return this.patientImmunizationId;
	}

	public void setPatientImmunizationId(Integer patientImmunizationId) {
		this.patientImmunizationId = patientImmunizationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ImmunizationID", nullable = false)
	public ImmunizationDb getImmunizationDb() {
		return this.immunizationDb;
	}

	public void setImmunizationDb(ImmunizationDb immunizationDb) {
		this.immunizationDb = immunizationDb;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PatientID", nullable = false)
	public PatientDb getPatientDb() {
		return this.patientDb;
	}

	public void setPatientDb(PatientDb patientDb) {
		this.patientDb = patientDb;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateAdministered", nullable = false, length = 19)
	public Date getDateAdministered() {
		return this.dateAdministered;
	}

	public void setDateAdministered(Date dateAdministered) {
		this.dateAdministered = dateAdministered;
	}

}