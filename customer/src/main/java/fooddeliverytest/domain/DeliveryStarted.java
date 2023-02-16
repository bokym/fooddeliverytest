package fooddeliverytest.domain;

import fooddeliverytest.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private String address;
    private Long orderId;
    private String riderId;
    private Long storeId;
    private String status;
}
