package fooddeliverytest.domain;

import fooddeliverytest.domain.OrderPlaced;
import fooddeliverytest.domain.OrderCanceled;
import fooddeliverytest.FrontApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Order_table")
@Data

public class Order  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String foodId;
    
    
    
    
    
    private String customerId;
    
    
    
    
    
    private String preference;
    
    
    
    
    
    private String options;
    
    
    
    
    
    private String address;
    
    
    
    
    
    private Long storeId;
    
    
    
    
    
    private Integer qty;
    
    
    
    
    
    private String status;

    @PostPersist
    public void onPostPersist(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.


        fooddeliverytest.external.Payment payment = new fooddeliverytest.external.Payment();
        // mappings goes here
        FrontApplication.applicationContext.getBean(fooddeliverytest.external.PaymentService.class)
            .pay(payment);


        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){

        OrderCanceled orderCanceled = new OrderCanceled(this);
        if(!orderCanceled.getStatus().equals("startcook")) {
            orderCanceled.publishAfterCommit();
        }

    }
    
    @PrePersist
    public void onPrePersist(){
    }
    @PreUpdate
    public void onPreUpdate(){
        // Get request from StoreOrder
        //fooddeliverytest.external.StoreOrder storeOrder =
        //    Application.applicationContext.getBean(fooddeliverytest.external.StoreOrderService.class)
        //    .getStoreOrder(/** mapping value needed */);

    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = FrontApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }




    public static void updateStatus(Accepted accepted){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process */
        
        repository().findById(accepted.getOrderId()).ifPresent(order->{
            
            order.setStatus(accepted.getStatus());
            repository().save(order);


         });

        
    }
    public static void updateStatus(Rejected rejected){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process */
        
        repository().findById(rejected.getOrderId()).ifPresent(order->{
            
            order.setStatus(rejected.getStatus());
            repository().save(order);


         });

        
    }
    public static void updateStatus(CookStarted cookStarted){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process */
        
        repository().findById(cookStarted.getOrderId()).ifPresent(order->{
            
            order.setStatus(cookStarted.getStatus());
            repository().save(order);


         });

        
    }


}
