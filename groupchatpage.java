package com.example.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class groupchatpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchatpage);

        Button btn = (Button)findViewById(R.id.go);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText text = (EditText)findViewById(R.id.newgroup);
                String roomName = text.getText().toString();
                Intent i = new Intent(groupchatpage.this, MainActivityChat.class);
                i.putExtra("chatroomname", roomName);
                startActivity(i);
            }
        });
    }

}
