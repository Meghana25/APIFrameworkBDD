package resources;

import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

import java.util.Arrays;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayload(String name,String language,String address)
    {
        AddPlace addPlace = new AddPlace();
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress(address);
        List<String> types = Arrays.asList("shoe park", "shop");
        addPlace.setTypes(types);
        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage(language);
        return addPlace;
    }
    public DeletePlace deletePlacePayLoad(String place_id)
    {
        DeletePlace deletePlace = new DeletePlace();
        deletePlace.setPlace_id(place_id);
        return deletePlace;
    }
}
