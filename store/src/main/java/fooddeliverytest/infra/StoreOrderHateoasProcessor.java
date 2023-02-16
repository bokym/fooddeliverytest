package fooddeliverytest.infra;
import fooddeliverytest.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class StoreOrderHateoasProcessor implements RepresentationModelProcessor<EntityModel<StoreOrder>>  {

    @Override
    public EntityModel<StoreOrder> process(EntityModel<StoreOrder> model) {
        model.add(Link.of(model.getRequiredLink("self").getHref() + "/finishcook").withRel("finishcook"));
        model.add(Link.of(model.getRequiredLink("self").getHref() + "/accept").withRel("accept"));
        model.add(Link.of(model.getRequiredLink("self").getHref() + "/reject").withRel("reject"));
        model.add(Link.of(model.getRequiredLink("self").getHref() + "/startcook").withRel("startcook"));

        
        return model;
    }
    
}
