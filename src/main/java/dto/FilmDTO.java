package dto;


import model.Entity;
import model.enums.Genre;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class FilmDTO extends Entity {

    private String name;
    private String descr;
    private int year;
    private String country;
    private String directors;
    private String studio;
    private Genre genre;
    private int length;
    private int ageRestr;
    private String image;

    public FilmDTO()
    {

    }

    public FilmDTO(String name, String descr, int year, String country, String directors, String studio, Genre genre, int length, int agerestr)
    {
        setName(name);
        setDescr(descr);
        setYear(year);
        setCountry(country);
        setDirectors(directors);
        setStudio(studio);
        setGenre(genre);
        setLength(length);
        setAgeRestr(agerestr);
    }

    public FilmDTO(String name, String descr, int year, String country, String directors, String studio, Genre genre, int length, int agerestr,String image) {
        this(name,descr,year,country,directors,studio,genre,length,agerestr);
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setAgeRestr(int agerestr) {
        this.ageRestr = agerestr;
    }

    public void setImageFromBlob(Blob image) {
        try {

            byte[] imgData = image.getBytes(1,(int)image.length());
            this.image = Base64.getEncoder().encodeToString(imgData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescr() {
        return descr;
    }

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getDirectors() {
        return directors;
    }

    public String getStudio() {
        return studio;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getLength() {
        return length;
    }

    public int getAgeRestr() {
        return ageRestr;
    }

    public String getImage(){
        return image;
    }

    public void setImageFromStream(InputStream is)
    {
        try {
            this.image = Base64.getEncoder().encodeToString(org.apache.commons.io.IOUtils.toByteArray(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "FilmDTO{" +
                "name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                ", directors='" + directors + '\'' +
                ", studio='" + studio + '\'' +
                ", genre=" + genre +
                ", length=" + length +
                ", ageRestr=" + ageRestr +
                ", image='" + image + '\'' +
                '}';
    }
}