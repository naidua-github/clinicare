package com.techstomach.justdental.model.schema;
// Generated Jan 4, 2017 9:42:35 AM by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PatientCheckin generated by hbm2java
 */
@Entity
@Table(name = "patient_checkin")
public class PatientCheckin implements java.io.Serializable {

	private Integer patientCheckInId;
	private Appointment appointment;
	private Invoice invoice;
	private Date checkInDateTime;
	private Date checkoutDateTime;
//	private Vector<PatientMedication> patientMedications = new Vector<PatientMedication>();

	public PatientCheckin() {
	}

	public PatientCheckin(Appointment appointment, Invoice invoice, Date checkInDateTime, Date checkoutDateTime) {
		this.appointment = appointment;
		this.invoice = invoice;
		this.checkInDateTime = checkInDateTime;
		this.checkoutDateTime = checkoutDateTime;
	}

//	public PatientCheckin(Appointment appointment, Invoice invoice, Date checkInDateTime, Date checkoutDateTime,
//						  Vector<PatientMedication> patientMedications) {
//		this.appointment = appointment;
//		this.invoice = invoice;
//		this.checkInDateTime = checkInDateTime;
//		this.checkoutDateTime = checkoutDateTime;
//		this.patientMedications = patientMedications;
//	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "PatientCheckInID", unique = true, nullable = false)
	public Integer getPatientCheckInId() {
		return this.patientCheckInId;
	}

	public void setPatientCheckInId(Integer patientCheckInId) {
		this.patientCheckInId = patientCheckInId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AppointmentID", nullable = false)
	public Appointment getAppointment() {
		return this.appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InvoiceID", nullable = false)
	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CheckInDateTime", nullable = false, length = 19)
	public Date getCheckInDateTime() {
		return this.checkInDateTime;
	}

	public void setCheckInDateTime(Date checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CheckoutDateTime", nullable = false, length = 19)
	public Date getCheckoutDateTime() {
		return this.checkoutDateTime;
	}

	public void setCheckoutDateTime(Date checkoutDateTime) {
		this.checkoutDateTime = checkoutDateTime;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patientCheckin")
//	public Vector<PatientMedication> getPatientMedications() {
//		return this.patientMedications;
//	}
//
//	public void setPatientMedications(Vector<PatientMedication> patientMedications) {
//		this.patientMedications = patientMedications;
//	}

}
