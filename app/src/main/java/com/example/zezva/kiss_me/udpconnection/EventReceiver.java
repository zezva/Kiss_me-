package com.example.zezva.kiss_me.udpconnection;

import com.example.zezva.kiss_me.observer.Beobachtbare;
import com.example.zezva.kiss_me.observer.Beobachter;

import java.io.Serializable;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by EmpaT on 04.08.2017.
 */

public class EventReceiver extends Thread implements Beobachtbare, Serializable{
    private Beobachter beobachter = null;

    private transient  DatagramSocket receiverSocket = null;
    private InetAddress IPAddress = null;
    private  boolean  run = true ;

    public EventReceiver() throws SocketException {
        receiverSocket = new DatagramSocket();
    }


    //Aq unda chaiweros receiveristvis sachiro kodi
    @Override
    public void run(){
      
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        while(run)
        {
        }
    }

    @Override
    public void register(Beobachter beobachter)
    {
        this.beobachter = beobachter;
    }

    public Beobachter getBeobachter() {
        return beobachter;
    }

    public void setBeobachter(Beobachter beobachter) {
        this.beobachter = beobachter;
    }

    public DatagramSocket getReceiverSocket() {
        return receiverSocket;
    }

    public void setReceiverSocket(DatagramSocket receiverSocket) {
        this.receiverSocket = receiverSocket;
    }

    public InetAddress getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(InetAddress IPAddress) {
        this.IPAddress = IPAddress;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
