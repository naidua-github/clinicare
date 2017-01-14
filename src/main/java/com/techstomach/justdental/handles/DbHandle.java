package com.techstomach.justdental.handles;

import com.techstomach.justdental.model.schema.AppUsers;
import com.techstomach.justdental.model.schema.Clinic;
import com.techstomach.justdental.model.schema.Patient;
import com.techstomach.justdental.model.schema.Role;
import com.techstomach.justdental.model.service.DbRequest;
import com.techstomach.justdental.model.service.PatientResp;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.List;

public class DbHandle {

    private static volatile DbHandle instance;
    private static volatile Object sync = new Object();

    private static SessionFactory factory;
    private  Configuration configuration;

    private static Logger log = LoggerFactory.getLogger(DbHandle.class);

    private DbHandle() {
    }

    public static DbHandle getInstance(){
        if(instance != null)  return instance;

        synchronized (sync)
        {
            if(instance != null)  return instance;

            instance = new DbHandle();
            if(instance.initialize() == false)
            {
                instance = null;
                return null;
            }

            return instance;
        }
    }

    private boolean initialize(){

        try
        {
//            String hibernate_properties = System.getProperty("config.base") + "/hibernate.cfg.xml";
//            String hibernate_properties = "/config/hibernate.cfg.xml";
//            log.info("using hibernate properties file in location: " + hibernate_properties);
//            factory = new Configuration().configure(System.getProperty("config.base")).buildSessionFactory();
            factory = new Configuration().configure().buildSessionFactory();
        }
        catch (Exception e)
        {
            System.err.println("failed to create sessionFactory object." + e);
            return false;
        }

        return true;
    }
    public String registerPatient(String firstName, String lastName, String phoneNumber, String logPrefix)    {
        log.info("registering patient with phoneNumber = " + phoneNumber + ", firstName = " + firstName+ ", lastName = " + lastName);
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "INSERT INTO `jd_database`.`patient_db` (`FirstName`,`LastName`,`PhoneNumber`) VALUES('" + firstName + "','" + lastName + "','" + phoneNumber + "')";
            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot register patient details: " + phoneNumber + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }

    //PATIENT
    public String insertPatient(DbRequest dbRequest, String logPrefix)    {
        log.info("registering patient with phoneNumber = " + dbRequest.getPhoneNumber() + ", firstName = " + dbRequest.getFirstName()+ ", lastName = " + dbRequest.getLastName());
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_insert_patient`('" + dbRequest.getFirstName()
                    + "', '" + dbRequest.getMiddleName()
                    + "', '" + dbRequest.getLastName()
                    + "', '" + dbRequest.getGender()
                    + "', '" + dbRequest.getDateOfBirth()
                    + "', '" + dbRequest.getPlaceOfBirth()
                    + "', '" + dbRequest.getPhoneNumber()
                    + "', '" + dbRequest.getMaritalStatus()
                    + "', '" + dbRequest.getBloodType()
                    + "', '" + dbRequest.getReligion()
                    + "', '" + dbRequest.getOccupation()
                    + "', '" + dbRequest.getEducation()
                    + "', '" + dbRequest.getInsuranceNumber()
                    + "', '" + dbRequest.getInsuranceExpiration()
                    + "', '" + dbRequest.getEmergencyContactName()
                    + "', '" + dbRequest.getEmergencyContactRelationship()
                    + "', '" + dbRequest.getEmergencyContactNumber()
                    + "', '" + dbRequest.getCreationDate()
                    + "', '" + dbRequest.getModifiedDate()
                    + "', '" + dbRequest.getModifiedBy()

                    + "', '" + dbRequest.getIsActive()
                    + "', '" + dbRequest.getPatientNote()
                    + "', '" + dbRequest.getEmailAddress()
                    + "', '" + dbRequest.getStreet1()
                    + "', '" + dbRequest.getStreet2()

                    + "', '" + dbRequest.getLocality()
                    + "', '" + dbRequest.getCity()
                    + "', '" + dbRequest.getState()
                    + "', '" + dbRequest.getCountry()
                    + "', '" + dbRequest.getPincode() + "')";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot register patient details: " + dbRequest.getPhoneNumber() + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public String updatePatient(DbRequest dbRequest, String logPrefix)    {
        log.info("updating patient with phoneNumber = " + dbRequest.getPhoneNumber() + ", firstName = " + dbRequest.getFirstName()+ ", lastName = " + dbRequest.getLastName());
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_update_patient`('" + dbRequest.getFirstName()
                    + "', '" + dbRequest.getMiddleName()
                    + "', '" + dbRequest.getLastName()
                    + "', '" + dbRequest.getGender()
                    + "', '" + dbRequest.getDateOfBirth()
                    + "', '" + dbRequest.getPlaceOfBirth()
                    + "', '" + dbRequest.getPhoneNumber()
                    + "', '" + dbRequest.getMaritalStatus()
                    + "', '" + dbRequest.getBloodType()
                    + "', '" + dbRequest.getReligion()
                    + "', '" + dbRequest.getOccupation()
                    + "', '" + dbRequest.getEducation()
                    + "', '" + dbRequest.getInsuranceNumber()
                    + "', '" + dbRequest.getInsuranceExpiration()
                    + "', '" + dbRequest.getEmergencyContactName()
                    + "', '" + dbRequest.getEmergencyContactRelationship()
                    + "', '" + dbRequest.getEmergencyContactNumber()
                    + "', '" + dbRequest.getCreationDate()
                    + "', '" + dbRequest.getModifiedDate()
                    + "', '" + dbRequest.getModifiedBy()

                    + "', '" + dbRequest.getIsActive()
                    + "', '" + dbRequest.getPatientNote()
                    + "', '" + dbRequest.getPatientId()
                    + "', '" + dbRequest.getEmailAddress()
                    + "', '" + dbRequest.getStreet1()
                    + "', '" + dbRequest.getStreet2()

                    + "', '" + dbRequest.getLocality()
                    + "', '" + dbRequest.getCity()
                    + "', '" + dbRequest.getState()
                    + "', '" + dbRequest.getCountry()
                    + "', '" + dbRequest.getPincode() + "')";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot update patient details: " + dbRequest.getPhoneNumber() + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public String deletePatient(Integer patientId, String logPrefix)    {
        log.info("deleting patient with patientId = " + patientId);
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_delete_patient`("+ patientId + ")";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot delete patient details: " + patientId + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public Patient showPatient(Integer patientId, String logPrefix)    {
        log.info("showing patient with patientId = " + patientId);
        Session session = null;

        try
        {
            session = factory.openSession();
            String sqlQuery = "SELECT t from Patient t WHERE t.patientId=" + patientId ;

            log.info("sql query: " + sqlQuery);
            Query query = session.createQuery(sqlQuery);

            List<Patient> result = query.list();
            log.info("sql query successfully executed: result size = " + result.size());
            if(result.size() == 0)
            {
                log.error(logPrefix + "cannot show patient details: " + patientId + ", result set is empty");
                return null;
            }

            Patient patient = (Patient) result.get(0);
            return patient;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot show patient details: " + patientId + ", exception received: " + e.toString());
            return null;
        }
        finally
        {
            if(session != null) session.close();
        }
    }

    //USER
    public String insertUser(DbRequest dbRequest, String logPrefix)    {
        log.info("registering user with phoneNumber = " + dbRequest.getPhoneNumber() + ", firstName = " + dbRequest.getFirstName()+ ", lastName = " + dbRequest.getLastName());
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_insert_user`('" + dbRequest.getFirstName()
                    + "', '" + dbRequest.getMiddleName()
                    + "', '" + dbRequest.getLastName()
                    + "', '" + dbRequest.getUserName()
                    + "', '" + dbRequest.getPassword()
                    + "', '" + dbRequest.getEmailAddress()
                    + "', '" + dbRequest.getPhoneNumber()
                    + "', '" + dbRequest.getLicenseNumber()
                    + "', '" + dbRequest.getApplicationName()
                    + "', '" + dbRequest.getPasswordQuestion()
                    + "', '" + dbRequest.getDateCreated()
                    + "', '" + dbRequest.getDateModified()
                    + "', '" + dbRequest.getModifiedBy()
                    + "', '" + dbRequest.getLastLogin()
                    + "', '" + dbRequest.getLastPasswordChanged()
                    + "', '" + dbRequest.getIsActive()
                    + "', '" + dbRequest.getNumberOfLogins()
                    + "', '" + dbRequest.getQualification()
                    + "', '" + dbRequest.getPasswordAnswer()
                    + "', @userId)";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot register user details: " + dbRequest.getPhoneNumber() + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public String updateUser(DbRequest dbRequest, String logPrefix)    {
        log.info("updating user with phoneNumber = " + dbRequest.getPhoneNumber() + ", firstName = " + dbRequest.getFirstName()+ ", lastName = " + dbRequest.getLastName());
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_update_user`('" + dbRequest.getUserId()
                    + "', '" + dbRequest.getFirstName()
                    + "', '" + dbRequest.getMiddleName()
                    + "', '" + dbRequest.getLastName()
                    + "', '" + dbRequest.getUserName()
                    + "', '" + dbRequest.getPassword()
                    + "', '" + dbRequest.getEmailAddress()
                    + "', '" + dbRequest.getPhoneNumber()
                    + "', '" + dbRequest.getLicenseNumber()
                    + "', '" + dbRequest.getApplicationName()
                    + "', '" + dbRequest.getPasswordQuestion()
                    + "', '" + dbRequest.getDateCreated()
                    + "', '" + dbRequest.getDateModified()
                    + "', '" + dbRequest.getModifiedBy()
                    + "', '" + dbRequest.getLastLogin()
                    + "', '" + dbRequest.getLastPasswordChanged()
                    + "', '" + dbRequest.getIsActive()
                    + "', '" + dbRequest.getNumberOfLogins()
                    + "', '" + dbRequest.getQualification()
                    + "', '" + dbRequest.getPasswordAnswer() + "')";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot update user details: " + dbRequest.getPhoneNumber() + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public String deleteUser(Integer userId, String logPrefix)    {
        log.info("deleting user with userId = " + userId);
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_delete_user`("+ userId + ")";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot delete user details: " + userId + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public AppUsers showUser(Integer userId, String logPrefix)    {
        log.info("showing user with userId = " + userId);
        Session session = null;

        try
        {
            session = factory.openSession();
            String sqlQuery = "SELECT t from AppUsers t WHERE t.userId=" + userId ;

            log.info("sql query: " + sqlQuery);
            Query query = session.createQuery(sqlQuery);
            List<AppUsers> result = query.list();
            log.info("sql query successfully executed: result size = " + result.size());
            if(result.size() == 0)
            {
                log.error(logPrefix + "cannot show user details: " + userId + ", result set is empty");
                return null;
            }

            AppUsers appUsers = (AppUsers) result.get(0);
            return appUsers;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot show user details: " + userId + ", exception received: " + e.toString());
            return null;
        }
        finally
        {
            if(session != null) session.close();
        }
    }

    //CLINIC
    public String insertClinic(DbRequest dbRequest, String logPrefix)    {
        log.info("registering clinic with name = " + dbRequest.getClinicName());
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_insert_clinic`('" + dbRequest.getClinicName()
                    + "', '" + dbRequest.getTaxNumber()
                    + "', '" + dbRequest.getPrimaryContactName()
                    + "', '" + dbRequest.getPrimaryContactNumber()
                    + "', '" + dbRequest.getIsActive()
                    + "', '" + dbRequest.getStreet1()
                    + "', '" + dbRequest.getStreet2()
                    + "', '" + dbRequest.getLocality()
                    + "', '" + dbRequest.getCity()
                    + "', '" + dbRequest.getState()
                    + "', '" + dbRequest.getCountry()
                    + "', '" + dbRequest.getGmap()
                    + "', '" + dbRequest.getPincode()
                    + "')";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot register clinic details: " + dbRequest.getClinicName() + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public String updateClinic(DbRequest dbRequest, String logPrefix)    {
        log.info("updating clinic with name = " + dbRequest.getClinicName());
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_update_clinic`('" + dbRequest.getClinicId()
                    + "', '" + dbRequest.getClinicName()
                    + "', '" + dbRequest.getTaxNumber()
                    + "', '" + dbRequest.getPrimaryContactName()
                    + "', '" + dbRequest.getPrimaryContactNumber()
                    + "', '" + dbRequest.getIsActive()
                    + "', '" + dbRequest.getStreet1()
                    + "', '" + dbRequest.getStreet2()
                    + "', '" + dbRequest.getLocality()
                    + "', '" + dbRequest.getCity()
                    + "', '" + dbRequest.getState()
                    + "', '" + dbRequest.getCountry()
                    + "', '" + dbRequest.getGmap()
                    + "', '" + dbRequest.getPincode()
                    + "')";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot update clinic details: " + dbRequest.getClinicName() + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public String deleteClinic(Integer clinicId, String logPrefix)    {
        log.info("deleting clinic with id = " + clinicId);
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_delete_clinic`("+ clinicId + ")";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot delete clinic details: " + clinicId + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public Clinic showClinic(Integer clinicId, String logPrefix)    {
        log.info("showing clinic with clinicId = " + clinicId);
        Session session = null;

        try
        {
            session = factory.openSession();
            String sqlQuery = "SELECT t from Clinic t WHERE t.clinicId=" + clinicId ;

            log.info("sql query: " + sqlQuery);
            Query query = session.createQuery(sqlQuery);
            List<Clinic> result = query.list();
            log.info("sql query successfully executed: result size = " + result.size());

            if(result.size() == 0)
            {
                log.error(logPrefix + "cannot show clinic details: " + clinicId + ", result set is empty");
                return null;
            }

            Clinic clinic = (Clinic) result.get(0);
            return clinic;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot show clinic details: " + clinicId + ", exception received: " + e.toString());
            return null;
        }
        finally
        {
            if(session != null) session.close();
        }
    }

    //ROLE
    public String insertRole(DbRequest dbRequest, String logPrefix)    {
        log.info("registering role with name = " + dbRequest.getName() + ", description = " + dbRequest.getDescription());
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_insert_role`('" + dbRequest.getName()
                    + "', '" + dbRequest.getDescription()
                    + "', @roleId)";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot register role details: " + dbRequest.getName() + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public String updateRole(DbRequest dbRequest, String logPrefix)    {
        log.info("update role with name = " + dbRequest.getName() + ", description = " + dbRequest.getDescription());
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_update_role`('" + dbRequest.getRoleId()
                    + "', '" + dbRequest.getName()
                    + "', '" + dbRequest.getDescription() + "')";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot update role details: " + dbRequest.getName() + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public String deleteRole(Integer roleId, String logPrefix)    {
        log.info("deleting role with roleId = " + roleId);
        Session session = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();

            String sqlQuery = "CALL `OpenEHS_database`.`sp_delete_role`("+ roleId + ")";

            log.info("sql query: " + sqlQuery);
            Query query = session.createSQLQuery(sqlQuery);
            int result = query.executeUpdate();
            log.info("sql query successfully executed: result = " + result);

            session.getTransaction().commit();
            return null;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot delete role details: " + roleId + ", exception received: " + e.toString());
            return e.toString();
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public Role showRole(Integer roleId, String logPrefix)    {
        log.info("showing role with roleId = " + roleId);
        Session session = null;

        try
        {
            session = factory.openSession();
            String sqlQuery = "SELECT t from Role t WHERE t.roleId=" + roleId ;

            log.info("sql query: " + sqlQuery);
            Query query = session.createQuery(sqlQuery);

            List<Role> result = query.list();
            log.info("sql query successfully executed: result size = " + result.size());
            if(result.size() == 0)
            {
                log.error(logPrefix + "cannot show role details: " + roleId + ", result set is empty");
                return null;
            }

            Role role = (Role) result.get(0);
            return role;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot show role details: " + roleId + ", exception received: " + e.toString());
            return null;
        }
        finally
        {
            if(session != null) session.close();
        }
    }

    public List getPatientByPhoneNumber(String phoneNumber, String logPrefix)    {
        Session session = null;

        try
        {
            session = factory.openSession();

            String sqlQuery = "SELECT t from Patient t WHERE t.phoneNumber = :phoneNumber";
            Query query = session.createQuery(sqlQuery);
            query.setParameter("phoneNumber", phoneNumber);
            List list = query.list();
            return list;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot get patient details by phone number " + phoneNumber + ", exception received: " + e.toString());
            return null;
        }
        finally
        {
            if(session != null) session.close();
        }
    }
    public List getUserByPhoneNumber(String phoneNumber, String logPrefix)    {
        Session session = null;

        try
        {
            session = factory.openSession();

            String sqlQuery = "SELECT t from User t WHERE t.phoneNumber = :phoneNumber";
            Query query = session.createQuery(sqlQuery);
            query.setParameter("phoneNumber", phoneNumber);
            List list = query.list();
            return list;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            log.error(logPrefix + "cannot get user details by phone number " + phoneNumber + ", exception received: " + e.toString());
            return null;
        }
        finally
        {
            if(session != null) session.close();
        }
    }
}
