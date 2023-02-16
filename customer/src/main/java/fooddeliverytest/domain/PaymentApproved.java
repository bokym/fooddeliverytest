package fooddeliverytest.domain;

import fooddeliverytest.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class PaymentApproved extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String payMethod;
    private Integer amount;
    private String status;
}
