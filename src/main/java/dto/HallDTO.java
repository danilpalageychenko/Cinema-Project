package dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Entity;
import model.enums.Place;

import java.util.ArrayList;

/**
 * Created by gaara on 3/27/17.
 */
public class HallDTO extends Entity {
    private String hallName;
    private ArrayList<ArrayList<Place>> places;

    public HallDTO()
    {

    }

    public HallDTO(String hallName,ArrayList<ArrayList<Place>> places){
        setHallName(hallName);
        this.places = places;
    }


    public void setPlaces(String places)
    {
        this.places = new Gson().fromJson(places, new TypeToken<ArrayList<ArrayList<Place>>>() {
        }.getType());
    }

    public String getPlaces() {
        return new Gson().toJson(places);
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public ArrayList<ArrayList<Place>> getListOfPlaces()
    {
        return places;
    }
}

