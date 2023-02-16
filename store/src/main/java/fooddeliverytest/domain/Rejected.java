package fooddeliverytest.domain;

import fooddeliverytest.domain.*;
import fooddeliverytest.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class Rejected extends AbstractEvent {

    private Long id;
    private String foodId;
    private String preference;
    private Long orderId;
    private String status;
    private String address;

    public Rejected(StoreOrder aggregate){
        super(aggregate);
    }
    public Rejected(){
        super();
    }
}
