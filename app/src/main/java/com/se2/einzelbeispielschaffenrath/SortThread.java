package com.se2.einzelbeispielschaffenrath;

import android.os.Handler;
import android.os.Message;

import java.util.Arrays;

public class SortThread extends Thread {

    String input;
    Handler handler;

    public SortThread(String input, Handler handler){
        this.input = input;
        this.handler = handler;
    }

    @Override
    public void run(){
        char[] array = input.toCharArray();
        Arrays.sort(array);
        String retString = "";
        for (int i = 0; i < array.length; i++) {
            if(array[i] % 2 == 0){
                retString = retString.concat(""+((int)array[i]-48));
            }
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i] % 2 == 1){
                retString = retString.concat(""+((int)array[i]-48));
            }
        }

        Message msg = Message.obtain();
        msg.arg1 = 1;
        msg.obj = retString;
        handler.sendMessage(msg);
    }

}
