package dto;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Entity;
import model.enums.Place;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by gaara on 3/10/17.
 */
public class SeasonDTO extends Entity {

    private long filmId;
    private String hallName;
    private ArrayList<ArrayList<Place>> places;
    private LocalDateTime time;
    private int price;

    public SeasonDTO()
    {

    }

    public SeasonDTO(long filmId, String hallName, ArrayList<ArrayList<Place>> places, LocalDateTime time, int price) {
        this.filmId = filmId;
        this.hallName = hallName;
        this.places = places;
        this.time = time;
        this.price = price;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public void setPlaces(String places)
    {
        this.places = new Gson().fromJson(places, new TypeToken<ArrayList<ArrayList<Place>>>() {
        }.getType());
    }


    public String getPlaces() {
        return new Gson().toJson(places);
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
