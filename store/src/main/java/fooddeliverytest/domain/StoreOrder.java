package fooddeliverytest.domain;

import fooddeliverytest.StoreApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="StoreOrder_table")
@Data

public class StoreOrder  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String foodId;
    
    
    
    
    
    private String preference;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private String status;
    
    
    
    
    
    private String address;

    @PreUpdate
    public void onPreUpdate(){
    }

    public static StoreOrderRepository repository(){
        StoreOrderRepository storeOrderRepository = StoreApplication.applicationContext.getBean(StoreOrderRepository.class);
        return storeOrderRepository;
    }



    public void finishCook(){
        CookFinished cookFinished = new CookFinished(this);
        cookFinished.setStatus("cookFinished");
        cookFinished.publishAfterCommit();

    }
    public void accept(){
        Accepted accepted = new Accepted(this);
        accepted.setStatus("accepted");
        accepted.publishAfterCommit();

    }
    public void reject(){
        Rejected rejected = new Rejected(this);
        rejected.setStatus("rejected");
        rejected.publishAfterCommit();

    }
    public void startCook(){
        CookStarted cookStarted = new CookStarted(this);
        cookStarted.setStatus("cookStarted");
        cookStarted.publishAfterCommit();

    }

    public static void orderInfoTransfer(OrderPlaced orderPlaced){

        /** Example 1:  new item */
        StoreOrder storeOrder = new StoreOrder();
        storeOrder.setFoodId(orderPlaced.getFoodId());
        storeOrder.setOrderId(orderPlaced.getId());
        storeOrder.setAddress(orderPlaced.getAddress());
        storeOrder.setStatus("orderPlaced");

        repository().save(storeOrder);

        

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);


         });
        */

        
    }
    public static void notifyOrderCancelled(OrderCanceled orderCanceled){

        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        */

        /** Example 2:  finding and process */
        
        repository().findById(orderCanceled.getId()).ifPresent(storeOrder->{
            
            storeOrder.setStatus("orderCanceled");
            repository().save(storeOrder);


         });

        
    }


}
