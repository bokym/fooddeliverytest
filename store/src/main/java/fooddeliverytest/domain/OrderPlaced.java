package fooddeliverytest.domain;

import fooddeliverytest.domain.*;
import fooddeliverytest.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String foodId;
    private String customerId;
    private String preference;
    private String options;
    private String address;
    private Long storeId;
    private Integer qty;
    private String status;
}


