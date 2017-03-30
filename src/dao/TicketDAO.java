package dao;

import model.Ticket;
import model.Transactions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;


public class TicketDAO {

    public Ticket getTicket(String ticketID){

        Ticket ticket = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{

            ticket = (Ticket) session.get(Ticket.class, ticketID);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return ticket;
    }

    public boolean deleteTicketByTransID(String transactionID) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {

            String hql = String.format("DELETE Ticket WHERE transactionID = '%s'", transactionID);
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

    public void saveTicket(Ticket ticket){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            session.save(ticket);
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
