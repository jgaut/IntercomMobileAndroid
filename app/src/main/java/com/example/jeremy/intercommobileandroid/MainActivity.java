package com.example.jeremy.intercommobileandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft_6455;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private MyWebSocketClient myWebSocketClient;
    private TextView textView;
    private Button bOpenDoor;
    private Button bEcho;
    private Button bConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebSocketImpl.DEBUG = true;

        textView = (TextView) findViewById(R.id.textView);
        bOpenDoor = (Button) findViewById(R.id.bOpenDoor);
        bEcho = (Button) findViewById(R.id.bEcho);
        bConnect = (Button) findViewById(R.id.bConnect);

        bOpenDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myWebSocketClient.send("open door");
            }
        });

        bEcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myWebSocketClient.send("echo");
            }
        });

        bConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myWebserverConnect();
            }
        });

        myWebserverConnect();
    }

    public void myWebserverConnect(){
        try {
            if(myWebSocketClient!=null){
                myWebSocketClient.close();
            }
            myWebSocketClient = new MyWebSocketClient(new URI("ws://82.251.99.8:8887"), new Draft_6455(), textView, bConnect);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        myWebSocketClient.connect();
    }
}
