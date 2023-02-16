package fooddeliverytest.domain;

import fooddeliverytest.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class CookFinished extends AbstractEvent {

    private Long id;
    private String foodId;
    private String preference;
    private Long orderId;
    private String status;
    private String address;
}
