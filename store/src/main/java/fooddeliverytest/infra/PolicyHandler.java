package fooddeliverytest.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;
import javax.transaction.Transactional;

import fooddeliverytest.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import fooddeliverytest.domain.*;

@Service
@Transactional
public class PolicyHandler{
    @Autowired StoreOrderRepository storeOrderRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderPlaced'")
    public void wheneverOrderPlaced_OrderInfoTransfer(@Payload OrderPlaced orderPlaced){

        OrderPlaced event = orderPlaced;
        System.out.println("\n\n##### listener OrderInfoTransfer : " + orderPlaced + "\n\n");


        

        // Sample Logic //
        StoreOrder.orderInfoTransfer(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderCanceled'")
    public void wheneverOrderCanceled_NotifyOrderCancelled(@Payload OrderCanceled orderCanceled){

        OrderCanceled event = orderCanceled;
        System.out.println("\n\n##### listener NotifyOrderCancelled : " + orderCanceled + "\n\n");


        

        // Sample Logic //
        StoreOrder.notifyOrderCancelled(event);
        

        

    }

}


