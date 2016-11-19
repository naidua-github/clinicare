package com.techstomach.justdental.model.schema;
// Generated Nov 19, 2016 4:08:03 PM by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SurgeryDb generated by hbm2java
 */
@Entity
@Table(name = "surgery_db", catalog = "jd_database")
public class SurgeryDb implements java.io.Serializable {

	private Integer surgeryId;
	private LocationDb locationDb;
	private PatientCheckinDb patientCheckinDb;
	private Date startTime;
	private Date endTime;
	private boolean caseType;
	private List<SurgeryStaffDb> surgeryStaffDbs;

	public SurgeryDb() {
	}

	public SurgeryDb(PatientCheckinDb patientCheckinDb, Date startTime, boolean caseType) {
		this.patientCheckinDb = patientCheckinDb;
		this.startTime = startTime;
		this.caseType = caseType;
	}

	public SurgeryDb(LocationDb locationDb, PatientCheckinDb patientCheckinDb, Date startTime, Date endTime,
			boolean caseType, List<SurgeryStaffDb> surgeryStaffDbs) {
		this.locationDb = locationDb;
		this.patientCheckinDb = patientCheckinDb;
		this.startTime = startTime;
		this.endTime = endTime;
		this.caseType = caseType;
		this.surgeryStaffDbs = surgeryStaffDbs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "SurgeryID", unique = true, nullable = false)
	public Integer getSurgeryId() {
		return this.surgeryId;
	}

	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LocationID")
	public LocationDb getLocationDb() {
		return this.locationDb;
	}

	public void setLocationDb(LocationDb locationDb) {
		this.locationDb = locationDb;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PatientCheckInID", nullable = false)
	public PatientCheckinDb getPatientCheckinDb() {
		return this.patientCheckinDb;
	}

	public void setPatientCheckinDb(PatientCheckinDb patientCheckinDb) {
		this.patientCheckinDb = patientCheckinDb;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "StartTime", nullable = false, length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndTime", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "CaseType", nullable = false)
	public boolean isCaseType() {
		return this.caseType;
	}

	public void setCaseType(boolean caseType) {
		this.caseType = caseType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "surgeryDb")
	public List<SurgeryStaffDb> getSurgeryStaffDbs() {
		return this.surgeryStaffDbs;
	}

	public void setSurgeryStaffDbs(List<SurgeryStaffDb> surgeryStaffDbs) {
		this.surgeryStaffDbs = surgeryStaffDbs;
	}

}