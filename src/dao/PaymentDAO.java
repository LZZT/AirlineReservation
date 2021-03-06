package dao;

import model.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;


public class PaymentDAO {

    public Payment getPayment(String cardNumber){

        Payment payment = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{

            payment = (Payment) session.get(Payment.class, cardNumber);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return payment;
    }

    public void savePayment(Payment payment){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            session.save(payment);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }
    }

    public boolean deletePayment(String cardNumber) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {
            String hql = String.format("DELETE Payment WHERE cardNumber = '%s'", cardNumber);
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


    public List<Payment> getPaymentInfoByUsername(String username){
        List<Payment> paymentsList=null;
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try{

            String hql1 = String.format("SELECT cardNumber FROM CustomerOwnsPayment WHERE username = '%s'", username);
            Query query1 = session.createQuery(hql1);
            List<String> cardNumberList =(List<String>)query1.list();
            paymentsList = new ArrayList<>();
            for(String o: cardNumberList){
                paymentsList.add(getPayment(o));
            }
            tx.commit();
        }catch(Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }
        return paymentsList;
    }

    public void updatePayment(Payment payment){
        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            session.update(payment);
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
