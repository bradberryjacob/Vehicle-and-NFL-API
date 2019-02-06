package com.example.demo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.nio.cs.UTF_8;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.demo.MyTasks.importNFLData;


@RestController
public class Controller {

    private static int id = 0;

    @Autowired
    private VehicleDbo vehicleDbo;
    @Autowired
    private NFLUserDbo nflUserDbo;


    @RequestMapping(value="/addVehicle", method= RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException{
        vehicleDbo.create(newVehicle);
        return newVehicle;
    }

    @RequestMapping(value="getVehicle/{id}", method=RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException{
        return vehicleDbo.getById(id);
    }

    @RequestMapping(value="updateVehicle", method=RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException{
        vehicleDbo.updateVehicle(newVehicle);
        return newVehicle;
    }



    @RequestMapping(value="deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        vehicleDbo.deleteVehicle(id);
        return null;
    }

    // TEST METHOD TO MAKE SURE THE REST API IS FUNCTIONING
    @RequestMapping("/greeting")
    public Greeting greeting() { return new Greeting(id, "WORKING YAYAYAYAYAY"); }


    /* NFL CODE  */
    @RequestMapping(value="/addUser", method= RequestMethod.POST)
    public NFLUser addNflUser(@RequestBody String s) throws IOException{
        String[] a = s.split("/");
        a[2] = a[2].trim();
        NFLUser n = new NFLUser(Integer.parseInt(a[0]), a[1] , a[2]);
        System.out.println(n == null ? "yes" : "no");
        System.out.println(nflUserDbo == null ? "eys" : "no");
        nflUserDbo.create(n);
        return n;
    }

    @RequestMapping(value="/getNFLUser/{id}", method=RequestMethod.GET)
    public NFLUser getNFLUser(@PathVariable("id") int id) throws IOException{
        return nflUserDbo.getById(id);
    }

    @RequestMapping(value="updateNFLUser", method=RequestMethod.PUT)
    public NFLUser updateVehicle(@RequestBody String s) throws IOException{
        String[] a = s.split("/");
        NFLUser n = new NFLUser(Integer.parseInt(a[0]), a[1] , a[2].trim());
        nflUserDbo.updateVehicle(n);
        return n;
    }



    @RequestMapping(value="deleteNFLUser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteNFLUser(@PathVariable("id") int id) throws IOException {
        nflUserDbo.deleteVehicle(id);
        return null;
    }

    @RequestMapping(value="checkScores/{id}", method = RequestMethod.GET)
    public String checkScores(@PathVariable("id") int id) throws Exception {
        String l = (importNFLData(nflUserDbo.getById(id).getFavTeam()) + "");
        String addTwitter = l + "  TwitterHandle " + nflUserDbo.getById(id).getUserTwitterHandle() + "    ";
        Files.write(Paths.get("Scores.txt"), (addTwitter).getBytes(), StandardOpenOption.APPEND);
        return l;
    }
}
