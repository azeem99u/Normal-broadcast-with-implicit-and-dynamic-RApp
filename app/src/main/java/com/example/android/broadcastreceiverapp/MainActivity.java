package com.example.android.broadcastreceiverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_KEY = "message_for_receiverApp";
    public static final String ACTION_BR = "com.example.android.broadcastsenderapp.SEND_BR_STRING";
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_BR.equals(intent.getAction())){
                if (intent.hasExtra(MESSAGE_KEY)){
                    String s = intent.getStringExtra(MESSAGE_KEY);
                    textView.setText(s.toString());
                    Toast.makeText(context, s.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    };


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter(ACTION_BR);
        registerReceiver(broadcastReceiver,intentFilter);
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}