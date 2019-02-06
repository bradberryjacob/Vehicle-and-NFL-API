package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="nfl")
public class NFLUser implements Serializable {
    @Id
    private int id;

    private String favTeam;
    private String userTwitterHandle;

    public NFLUser(int id, String fav, String twitter){
        this.id = id;
        favTeam = fav;
        userTwitterHandle = twitter;
    }
    public NFLUser(){}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFavTeam() { return favTeam; }
    public void setFavTeam(String favTeam) { this.favTeam = favTeam; }
    public String getUserTwitterHandle() { return userTwitterHandle; }
    public void setUserTwitterHandle(String userTwitterHandle) {this.userTwitterHandle = userTwitterHandle; }

}
