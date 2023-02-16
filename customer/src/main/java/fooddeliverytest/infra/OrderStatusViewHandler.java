package fooddeliverytest.infra;

import fooddeliverytest.domain.*;
import fooddeliverytest.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusViewHandler {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1 (@Payload OrderPlaced orderPlaced) {
        try {

            if (!orderPlaced.validate()) return;

            // view 객체 생성
            OrderStatus orderStatus = new OrderStatus();
            // view 객체에 이벤트의 Value 를 set 함
            orderStatus.setFoodId(orderPlaced.getFoodId());
            orderStatus.setOrderId(orderPlaced.getId());
            orderStatus.setStatus(orderPlaced.getStatus());
            // view 레파지 토리에 save
            orderStatusRepository.save(orderStatus);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenAccepted_then_UPDATE_1(@Payload Accepted accepted) {
        try {
            if (!accepted.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(accepted.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setStatus(accepted.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookStarted_then_UPDATE_2(@Payload CookStarted cookStarted) {
        try {
            if (!cookStarted.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(cookStarted.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setStatus(cookStarted.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_UPDATE_3(@Payload DeliveryStarted deliveryStarted) {
        try {
            if (!deliveryStarted.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(deliveryStarted.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setStatus(deliveryStarted.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentApproved_then_UPDATE_4(@Payload PaymentApproved paymentApproved) {
        try {
            if (!paymentApproved.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(paymentApproved.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setStatus(paymentApproved.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCanceled_then_UPDATE_5(@Payload PaymentCanceled paymentCanceled) {
        try {
            if (!paymentCanceled.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(paymentCanceled.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setStatus(paymentCanceled.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenRejected_then_UPDATE_6(@Payload Rejected rejected) {
        try {
            if (!rejected.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(rejected.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setStatus(rejected.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookFinished_then_UPDATE_7(@Payload CookFinished cookFinished) {
        try {
            if (!cookFinished.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(cookFinished.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setStatus(cookFinished.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

