package com.example.zezva.kiss_me.udpconnection;

import android.os.AsyncTask;

import com.example.zezva.kiss_me.model.Client;

/**
 * Created by EmpaT on 04.08.2017.
 */

public class CommandSender extends AsyncTask<Object, Void, Void>
{
    UDPClient udpClient;

    @Override
    protected Void doInBackground(Object... objects)
    {
        udpClient = new UDPClient();
        if(objects[0] instanceof Client)
        {
            udpClient.send((Client) objects[0]);
        }
        return null;
    }
}
