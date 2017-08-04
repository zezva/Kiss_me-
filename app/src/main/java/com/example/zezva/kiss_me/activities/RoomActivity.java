package com.example.zezva.kiss_me.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zezva.kiss_me.model.Client;
import com.example.zezva.kiss_me.R;
import com.example.zezva.kiss_me.model.Room;
import com.example.zezva.kiss_me.observer.Beobachter;
import com.example.zezva.kiss_me.udpconnection.EventReceiver;

import java.util.List;

public class RoomActivity extends AppCompatActivity implements Beobachter {

    private List<Room> roomList ;
    private EventReceiver eventReceiver;
    private Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        eventReceiver = (EventReceiver) getIntent().getExtras().get("eventReceiver");
        eventReceiver.register(this);

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
