package fooddeliverytest.domain;

import fooddeliverytest.domain.PaymentApproved;
import fooddeliverytest.domain.PaymentCanceled;
import fooddeliverytest.FrontApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Payment_table")
@Data

public class Payment  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private String payMethod;
    
    
    
    
    
    private Integer amount;
    
    
    
    
    
    private String status;

    @PostPersist
    public void onPostPersist(){
    }
    @PostUpdate
    public void onPostUpdate(){


        PaymentApproved paymentApproved = new PaymentApproved(this);
        paymentApproved.publishAfterCommit();



        PaymentCanceled paymentCanceled = new PaymentCanceled(this);
        paymentCanceled.publishAfterCommit();

    }

    public static PaymentRepository repository(){
        PaymentRepository paymentRepository = FrontApplication.applicationContext.getBean(PaymentRepository.class);
        return paymentRepository;
    }




    public static void cancelPayment(Rejected rejected){

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process */
        
        repository().findById(rejected.getOrderId()).ifPresent(payment->{
            
            payment.setStatus(rejected.getStatus());
            repository().save(payment);


         });

        
    }
    public static void cancelPayment(OrderCanceled orderCanceled){

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process */
        
        repository().findById(orderCanceled.getId()).ifPresent(payment->{
            
            payment.setStatus(orderCanceled.getStatus());
            repository().save(payment);


         });

        
    }


}
