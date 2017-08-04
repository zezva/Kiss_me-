package com.example.zezva.kiss_me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class RoomActivity extends AppCompatActivity implements  Beobachter {

    private List<Room> roomList ;
    private ClientThread clientThread;
    private  Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        clientThread = (ClientThread) getIntent().getExtras().get("clientthread");
        clientThread.register(this);

        client  = (Client) getIntent().getExtras().get("client");

        /**
         * aq listic unda amovigo intentidan
         * sayisi gaketebuli roomebi ro davsva activityze da mere
         * ukve update methodi gaaketebs amas
         */


    }

    @Override
    public void update(List<Room> rooms)  {
        this.roomList = rooms;

    }
}
