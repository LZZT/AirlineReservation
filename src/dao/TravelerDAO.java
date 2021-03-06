package dao;

//import model.CustomerOwnsTraveler;
import model.Payment;
import model.Traveler;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;


public class TravelerDAO {

    public Traveler getTraveler(String phone) {

        Traveler traveler = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try {
            traveler = session.get(Traveler.class, phone);
            tx.commit();

        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return traveler;
    }

    public void saveTraveler(Traveler traveler) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try {
            if (traveler != getTraveler(traveler.getPhone())) {
                session.save(traveler);
                tx.commit();
            }


        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    public boolean deleteTraveler(String phone) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {

            String hql = String.format("DELETE Traveler WHERE contactPhone = '%s'", phone);
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();

        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return true;
    }

    public List<Traveler> getTravelerByUsername(String username) {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        List<Traveler> travelerList = new ArrayList<>();

        try {


            String hql = String.format("SELECT distinct ticket.travellerID FROM Transactions trans, Ticket ticket WHERE trans.username = '%s' AND trans.transactionID = ticket.transactionID", username);
            Query query= session.createQuery(hql);
            List<String> travellerIDList = (List<String>) query.list();
            for(String t :travellerIDList){

                travelerList.add(getTraveler(t));
            }
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
        return travelerList;

    }

    public List<Traveler> getTravelerList( String username) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();
        List<Traveler> travelerList = new ArrayList<>();
        try {
            String hql = String.format("SELECT contactPhone FROM CustomerOwnsTraveler WHERE username = '%s'", username);
            Query query= session.createQuery(hql);
            List<String> travellerIDList = (List<String>) query.list();
            System.out.println("abc"+travellerIDList.size());

            for(String t :travellerIDList){

                travelerList.add(getTraveler(t));
            }
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
        return travelerList;
    }


    public void updateTravelerInfo(Traveler t){
        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            session.update(t);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }

        }finally {
            HibernateUtil.close(session);
        }
    }
}
