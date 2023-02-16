package fooddeliverytest.domain;

import fooddeliverytest.RiderApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Delivery_table")
@Data

public class Delivery  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String address;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private String riderId;
    
    
    
    
    
    private Long storeId;
    
    
    
    
    
    private String status;

    @PreUpdate
    public void onPreUpdate(){
    }

    public static DeliveryRepository repository(){
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(DeliveryRepository.class);
        return deliveryRepository;
    }



    public void pick(){
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();

    }
    public void confirmDelivery(){
        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();

    }

    public static void deliveryInfoTransfer(CookFinished cookFinished){

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process */
        
        repository().findById(cookFinished.getOrderId()).ifPresent(delivery->{
            
            delivery.setOrderId(cookFinished.getOrderId());
            delivery.setStoreId(cookFinished.getStoreId());
            delivery.setAddress(cookFinished.getAddress());
            delivery.setStatus("delivery");
            repository().save(delivery);


         });

        
    }


}
