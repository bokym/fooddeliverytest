package fooddeliverytest.domain;

import fooddeliverytest.CustomerApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Log_table")
@Data

public class Log  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String customerId;
    
    
    
    
    
    private String message;


    public static LogRepository repository(){
        LogRepository logRepository = CustomerApplication.applicationContext.getBean(LogRepository.class);
        return logRepository;
    }




    public static void alertToUser(Accepted accepted){

        /** Example 1:  new item 
        Log log = new Log();
        repository().save(log);

        */

        /** Example 2:  finding and process */
        
        repository().findById(accepted.getOrderId()).ifPresent(log->{
            
            log.setMessage(accepted.getStatus());
            repository().save(log);


         });

        
    }
    public static void alertToUser(Rejected rejected){

        /** Example 1:  new item 
        Log log = new Log();
        repository().save(log);

        */

        /** Example 2:  finding and process */
        
        repository().findById(rejected.getOrderId()).ifPresent(log->{
            
            log.setMessage(rejected.getStatus());
            repository().save(log);


         });

        
    }
    public static void alertToUser(OrderPlaced orderPlaced){

        /** Example 1:  new item 
        Log log = new Log();
        repository().save(log);

        */

        /** Example 2:  finding and process */
        
        repository().findById(orderPlaced.getId()).ifPresent(log->{
            
            log.setMessage(orderPlaced.getStatus());
            repository().save(log);


         });

        
    }
    public static void alertToUser(DeliveryStarted deliveryStarted){

        /** Example 1:  new item 
        Log log = new Log();
        repository().save(log);

        */

        /** Example 2:  finding and process */
        
        repository().findById(deliveryStarted.getOrderId()).ifPresent(log->{
            
            log.setMessage(deliveryStarted.getStatus());
            repository().save(log);


         });

        
    }


}
