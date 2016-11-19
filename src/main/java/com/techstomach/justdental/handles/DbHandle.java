package com.techstomach.justdental.handles;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
            factory = new Configuration().configure().buildSessionFactory();
        }
        catch (Exception e)
        {
            System.err.println("failed to create sessionFactory object." + e);
            return false;
        }

        return true;
    }
    public String registerPatient(String firstName, String lastName, String phoneNumber, String logPrefix)
    {
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
    public List getPatientByPhoneNumber(String phoneNumber, String logPrefix)
    {
        Session session = null;

        try
        {
            session = factory.openSession();

            String sqlQuery = "SELECT t from PatientDb t WHERE t.phoneNumber = :phoneNumber";
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
}
