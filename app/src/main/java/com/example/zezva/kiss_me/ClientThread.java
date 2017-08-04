package com.example.zezva.kiss_me;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Zezva on 02.08.2017.
 */

public class ClientThread  implements Runnable,Beobachbarer, Serializable {

    private Beobachter beobachter = null ;
    private DatagramSocket clientSocket = null;
    private InetAddress IPAddress = null;
    private  boolean  run = true ;


    public ClientThread() throws SocketException, UnknownHostException {
        clientSocket = new DatagramSocket();
        IPAddress = InetAddress.getByName("192.168.2.101");
    }




    @Override
    public void run() {
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        while(run){



        }

    }

    public void sendNewClient(final Client client) throws Exception {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String string = "in";
                    byte[] sendData1 = new byte[1024];
                    sendData1 = string.getBytes();

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = null;
                    try {
                        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    DatagramPacket sendPacket1 = new DatagramPacket(sendData1, sendData1.length, IPAddress, 9876);
                    try {
                        clientSocket.send(sendPacket1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        objectOutputStream.writeObject(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] sendData2 = byteArrayOutputStream.toByteArray();
                    DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, IPAddress, 9876);
                    try {
                        clientSocket.send(sendPacket2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();


    }

    @Override
    public void register(Beobachter beobachter) {
        this.beobachter = beobachter;

    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public Beobachter getBeobachter() {
        return beobachter;
    }

    public void setBeobachter(Beobachter beobachter) {
        this.beobachter = beobachter;
    }

    public DatagramSocket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
