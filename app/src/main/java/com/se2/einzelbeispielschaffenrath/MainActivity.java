package com.se2.einzelbeispielschaffenrath;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Button submitBtn;
    Button sortBtn;
    EditText inputMatNr;
    TextView answerText;
    TextView sortText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitBtn = findViewById(R.id.submitBtn);
        sortBtn = findViewById(R.id.sortBtn);
        inputMatNr = findViewById(R.id.inputMatNr);
        answerText = findViewById(R.id.answerText);
        sortText = findViewById(R.id.sortText);

        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.arg1 == 0) {
                    answerText.setText((String)(msg.obj));
                }
                if(msg.arg1 == 1){
                    sortText.setText((String)(msg.obj));
                }
            }
        };

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatNrThread thread = new MatNrThread(inputMatNr.getText().toString(),handler);
                thread.start();
            }
        });

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SortThread thread = new SortThread(inputMatNr.getText().toString(),handler);
                thread.start();
            }
        });
    }
}
