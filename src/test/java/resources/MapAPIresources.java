package resources;

//enum is special class in java which has collection of constants or methods

public enum MapAPIresources {
    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json"),
    updatePlaceAPI("/maps/api/place/update/json");

   private String resource;

    MapAPIresources(String resource) {
     this.resource=resource;
    }

    public String getAPIresource()
    {
     return resource;
    }
}
