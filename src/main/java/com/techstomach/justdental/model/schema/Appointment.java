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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Appointment generated by hbm2java
 */
@Entity
@Table(name = "Appointment")
public class Appointment implements java.io.Serializable {

	private Integer appointmentId;
	private Clinicdoctor clinicdoctor;
	private int patientId;
	private String timeSlots;
	private int confirmed;
	private int isActive;
	//private Vector<PatientCheckin> patientCheckins = new Vector<PatientCheckin>();

	public Appointment() {
	}

	public Appointment(Clinicdoctor clinicdoctor, int patientId, String timeSlots, int confirmed, int isActive) {
		this.clinicdoctor = clinicdoctor;
		this.patientId = patientId;
		this.timeSlots = timeSlots;
		this.confirmed = confirmed;
		this.isActive = isActive;
	}

//	public Appointment(Clinicdoctor clinicdoctor, int patientId, String timeSlots, int confirmed, int isActive,
//					   Vector<PatientCheckin> patientCheckins) {
//		this.clinicdoctor = clinicdoctor;
//		this.patientId = patientId;
//		this.timeSlots = timeSlots;
//		this.confirmed = confirmed;
//		this.isActive = isActive;
//		//this.patientCheckins = patientCheckins;
//	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "AppointmentID", unique = true, nullable = false)
	public Integer getAppointmentId() {
		return this.appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ClinicDoctorID", nullable = false)
	public Clinicdoctor getClinicdoctor() {
		return this.clinicdoctor;
	}

	public void setClinicdoctor(Clinicdoctor clinicdoctor) {
		this.clinicdoctor = clinicdoctor;
	}

	@Column(name = "PatientID", nullable = false)
	public int getPatientId() {
		return this.patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	@Column(name = "TimeSlots", nullable = false)
	public String getTimeSlots() {
		return this.timeSlots;
	}

	public void setTimeSlots(String timeSlots) {
		this.timeSlots = timeSlots;
	}

	@Column(name = "Confirmed", nullable = false)
	public int getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	@Column(name = "IsActive", nullable = false)
	public int getIsActive() {
		return this.isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
//	public Vector<PatientCheckin> getPatientCheckins() {
//		return this.patientCheckins;
//	}
//
//	public void setPatientCheckins(Vector<PatientCheckin> patientCheckins) {
//		this.patientCheckins = patientCheckins;
//	}

}