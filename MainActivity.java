package com.example.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button submit;
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        username =(EditText)(findViewById(R.id.editText));
        password = (EditText)(findViewById(R.id.editText3));
        submit = (Button)(findViewById(R.id.button));
        createAccount = (Button)(findViewById(R.id.button2));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CreateAccount.class);
                startActivity(i);
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToGroups();
            }
        });

  }



    private void moveToGroups() {
        Intent intent = new Intent(MainActivity.this,CreateAccount.class);
        startActivity(intent);

    }


}
