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
    EditText inputMatNr;
    TextView answerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitBtn = findViewById(R.id.submitBtn);
        inputMatNr = findViewById(R.id.inputMatNr);
        answerText = findViewById(R.id.answerText);

        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                answerText.setText((String)(msg.obj));
            }
        };

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatNrThread thread = new MatNrThread(inputMatNr.getText().toString(),handler);
                thread.start();
            }
        });
    }
}
