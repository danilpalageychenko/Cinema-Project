package dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import helpers.Hasher;
import model.Entity;
import model.enums.Role;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class UserDTO extends Entity {

    private String login;
    private String name;
    private String surName;
    private LocalDate bday;
    private String password;
    private Role role;
    private ArrayList<Integer> tickets;
    private int money;

    public UserDTO() {

    }

    public UserDTO(String login,String password,String name, String surName, String bday,String role) {
        setLogin(login);
        setPassword(password);
        setName(name);
        setSurName(surName);
        setBday(bday);
        setRole(role);
        tickets = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setBday(String bday) {
        this.bday = LocalDate.parse(bday, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public void addTicket(int seasonId) {
        tickets.add(seasonId);
    }

    public void setTickets(String tickets) {
        this.tickets = new Gson().fromJson(tickets, new TypeToken<ArrayList<Integer>>() {
        }.getType());
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getBday()
    {
        return bday.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public String getTickets() {
        return new Gson().toJson(tickets);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", bday=" + bday +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", tickets=" + tickets +
                '}';
    }

    public String getPassword() {return password;}

    public void setPassword(String password) { this.password = password; }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public String getRole() {
        return role.toString();
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
