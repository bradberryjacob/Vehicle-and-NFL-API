package com.example.demo;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Random;
import java.util.Scanner;

@Component
public class MyTasks {

    @Autowired
    private static NFLUserDbo dbo;

    RestTemplate restTemplate = new RestTemplate();
    private  int lastID = 4300000;

   // @Scheduled(fixedRate = 10000)
    public void runData() throws Exception {
        String URL = "http://localhost:8080/checkScores/1";
        restTemplate.getForObject(URL, String.class);
    }

    public static String importNFLData(String team) throws Exception {
        URL website = new URL("http://www.nfl.com/liveupdate/scorestrip/ss.xml");
        Scanner in = new Scanner(new InputStreamReader(website.openStream()));
        while(in.hasNext()) {
            String a = in.nextLine();
            if(a.toLowerCase().contains(team.toLowerCase()))
            {
                String[] spliced = a.split(" ");
                String homeTeam;
                String homeScore;
                String awayTeam;
                String awayScore;
                // System.out.println(spliced[9]);
                if(!spliced[9].contains("F")){
                    homeTeam = spliced[11].substring(3,6);
                    homeScore = spliced[13].substring(4,6).contains("\"") ? spliced[13].substring(4,5) : spliced[13].substring(4,6);
                    awayTeam = spliced[14].substring(3,6);
                    awayScore = spliced[16].substring(4,6).contains("\"") ? spliced[16].substring(4,5) : spliced[16].substring(4,6);}
                else {
                    homeTeam = spliced[10].substring(3,6);
                    homeScore = spliced[12].substring(4,6).contains("\"") ? spliced[12].substring(4,5) : spliced[12].substring(4,6);
                    awayTeam = spliced[13].substring(3,6);
                    awayScore = spliced[15].substring(4,6).contains("\"") ? spliced[15].substring(4,5) : spliced[15].substring(4,6);
                }
                return (homeTeam + " Score:" + homeScore + " " + awayTeam + " Score:" + awayScore);
            }
        }
        return "Team not found";
    }

  @Scheduled(fixedRate = 1000)
    public void addVehicle() {
        String URL = "http://localhost:8080/addVehicle";
        Random r = new Random();
        int year = (int) Math.floor(Math.random()*(2016-1986+1)+1986);
        int price = (int) Math.floor(Math.random()*(45000-15000+1)+15000);
        String generatedString = RandomStringUtils.random(25, true, false);
        restTemplate.postForObject(URL, new Vehicle(lastID,generatedString,year,price), Vehicle.class);
        lastID++;
    }

    @Scheduled(fixedRate = 6000)
    public void deleteVehicle() {
        String URL = "http://localhost:8080/deleteVehicle";
        Random r = new Random();
        int l = r.nextInt(lastID + 100);
        restTemplate.delete(URL + "/" + l);
    }

    @Scheduled(fixedRate = 20000)
    public void updateVehicle() {
        String URL = "http://localhost:8080/updateVehicle";
        Random r = new Random();
        int id = r.nextInt(lastID);
        restTemplate.put(URL, new Vehicle(id, "Ferrari", 2000, 45000));
    }

}
