package com.example.zezva.kiss_me.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zezva on 01.08.2017.
 */

public class Client implements Serializable {

    private  String name ;
    private  String gender ;
    private List<Room> roomList;
    private Room room;
    private String status = "";

    public Client(String name, String gender){
        this.name = name ;
        this.gender = gender;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
