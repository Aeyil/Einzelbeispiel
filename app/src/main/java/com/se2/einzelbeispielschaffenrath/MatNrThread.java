package com.se2.einzelbeispielschaffenrath;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MatNrThread extends Thread {

    Socket clientSocket;
    DataOutputStream outToServer;
    BufferedReader inFromServer;
    String input;
    Handler handler;

    public MatNrThread(String input, Handler handler){
        this.input = input;
        this.handler = handler;
    }

    @Override
    public void run(){
        try {
            clientSocket = new Socket("se2-isys.aau.at",53212);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(input + "\n");
            Message msg = Message.obtain();
            msg.arg1 = 0;
            msg.obj = inFromServer.readLine();
            handler.sendMessage(msg);
            clientSocket.close();
        } catch (IOException ex) {
            Log.e("THREADERROR", "Fehler im Übertragungsthread.");
        }
    }

}
