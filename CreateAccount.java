package com.example.myapplication;

import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button submit;
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        setUpUI();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToGroups();
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToGroups();
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToGroups();
            }
        });
    }

    public void setUpUI() {
        username = (EditText)findViewById(R.id.editText2);
        password = (EditText)findViewById(R.id.editText5);
        submit = (Button)findViewById(R.id.button3);
        createAccount = (Button)findViewById(R.id.button4);
    }

    private void moveToGroups() {
        Intent intent = new Intent(CreateAccount.this,MainActivity.class);
        startActivity(intent);

    }
}
