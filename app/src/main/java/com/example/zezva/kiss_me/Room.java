package com.example.zezva.kiss_me;

import java.io.Serializable;

/**
 * Created by Zezva on 01.08.2017.
 */

public class Room implements Serializable {
    private  String name ;
    private  int id;
    private  String password ;

    public  Room(int id, String name, String password){
        this.id = id ;
        this.name = name ;
        this.password = password;
    }
}
