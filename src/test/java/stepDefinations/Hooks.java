package stepDefinations;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinations step = new StepDefinations();
        if(step.place_id==null)
        {
            step.add_place_with_updated_values("Maggi","French","Asia");
            step.user_calls_with_post_http_request("addPlaceAPI","POST");
            step.verify_place_id_created_maps_to_using("Maggi","getPlaceAPI");
        }
    }
}
