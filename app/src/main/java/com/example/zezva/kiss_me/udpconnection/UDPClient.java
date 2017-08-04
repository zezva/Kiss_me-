package com.example.zezva.kiss_me.udpconnection;

import com.example.zezva.kiss_me.model.Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by EmpaT on 04.08.2017.
 */

public class UDPClient
{
    private static final String SERVER_IP = "192.168.2.101";
    private static final int SERVER_PORT = 9876;
    byte[] sendData;

    public void send(Client client)
    {
        String testMessage = "Zezva_Aptsiauri";
        try
        {
            //create a socket to make the connection with the server
            DatagramSocket senderSocket = new DatagramSocket();
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

            sendData = testMessage.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, testMessage.length(), serverAddr, SERVER_PORT);
            senderSocket.send(sendPacket);
            senderSocket.close();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
