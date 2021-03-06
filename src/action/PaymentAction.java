package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Customer;
import model.Payment;
import model.Traveler;
import org.apache.struts2.ServletActionContext;
import service.CustomerService;
import service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PaymentAction extends ActionSupport {


    private PaymentService paymentService = new PaymentService();

    private String cardNumber;
    private String cardFirstname;
    private String cardLastname;
    private String expDate;
    private String cvv;
    private String billingAddress;

    private String index;

    private CustomerService customerService = new CustomerService();

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardFirstname() {
        return cardFirstname;
    }

    public void setCardFirstname(String cardFirstname) {
        this.cardFirstname = cardFirstname;
    }

    public String getCardLastname() {
        return cardLastname;
    }

    public void setCardLastname(String cardLastname) {
        this.cardLastname = cardLastname;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String PaymentInfo() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        if (null != index && index instanceof String) {
            String indexNumber = ((String) index).split("/")[0];
            Payment p = (Payment) ((List) session.getAttribute("paymentsHistoryList")).get(Integer.valueOf(indexNumber));
            session.setAttribute("payment", p);

        } else {
            Payment payment = new Payment();
            payment.setCardNumber(cardNumber);
            payment.setCardLastname(cardLastname);
            payment.setCardFirstname(cardFirstname);
            payment.setExpDate(expDate);
            payment.setCvv(cvv);
            payment.setBillingAddress(billingAddress);
            String username = (String) session.getAttribute("username");
            Set<Customer> customerSet = new HashSet<>();
            customerSet.add(customerService.getCustomer(username));
            payment.setCustomerSet(customerSet);

            session.setAttribute("payment", payment);
        }

        return SUCCESS;
    }

    public String updateCard() throws  Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String cardNumber = (String)session.getAttribute("updateNumber");
        Payment payment = paymentService.getPayment(cardNumber);
        payment.setCardLastname(cardLastname);
        payment.setCardFirstname(cardFirstname);
        payment.setExpDate(expDate);
        payment.setCvv(cvv);
        payment.setBillingAddress(billingAddress);
        String username = (String) session.getAttribute("username");
        Set<Customer> customerSet = new HashSet<>();
        customerSet.add(customerService.getCustomer(username));
        paymentService.updateCreditCard(payment);
        return  SUCCESS;
    }

    public String addCard() throws  Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        Payment payment = new Payment();
        payment.setCardNumber(cardNumber);
        payment.setCardLastname(cardLastname);
        payment.setCardFirstname(cardFirstname);
        payment.setExpDate(expDate);
        payment.setCvv(cvv);
        payment.setBillingAddress(billingAddress);
        String username = (String) session.getAttribute("username");
        Set<Customer> customerSet = new HashSet<>();
        customerSet.add(customerService.getCustomer(username));
        payment.setCustomerSet(customerSet);
        paymentService.addCreditCard(payment);
        return  SUCCESS;
    }


//
//    public void validatePaymentInfo(){
//
//        if (null == cardNumber || !isNumeric(cardNumber) || cardNumber.length()!=16){
//            this.addActionError("Invalid Card Number");
//        }
//
//        if (null == cardLastname|| cardLastname.length() < 1){
//            this.addActionError("Last Name can not be empty");
//        }
//
//        if (null == cardFirstname || cardFirstname.length() < 1){
//            this.addActionError("First Name can not be empty");
//        }
//
//        if (null == cvv || cvv.length()!=3 || !isNumeric(cvv) ){
//            this.addActionError("Invalid CVV number");
//        }
//
//
//
//
//    }


    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}
