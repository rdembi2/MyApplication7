package com.example.teamfinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

//import package.InfoSet;

public class MainActivity1 extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TOPIC = "topic";

    private EditText editTextName;
    private EditText editTextEmail;
    private TextView textViewData;
    private EditText editTextTopic;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private DocumentReference infoRef = db.collection("Data").document("Data set");

    private CollectionReference allInfoRef = db.collection("Data");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.edit_text_title);
        editTextEmail = findViewById(R.id.edit_text_email);
        textViewData = findViewById(R.id.text_view_data);
        editTextTopic = findViewById(R.id.edit_text_topic);
    }

    protected void onStart() {
        super.onStart();
        allInfoRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null) {
                    return;
                }

                String data = "";

                for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots) {
                    InfoSet info = documentSnapshot.toObject(InfoSet.class);

                    //info.setDocumentId(documentSnapshot.getId());

                    //String documentID = info.getDocumentId();

                    String title = info.getTitle();
                    String email = info.getEmail();
                    String topic = info.getTopic();

                    data+= "Title" + title + "\nEmail: " + email + "\nTopic" + topic;
                }

                textViewData.setText(data);
            }
        });
    }

    public void updateDescription(View v) {
        String topic = editTextTopic.getText().toString();

        infoRef.update(KEY_TOPIC, topic);
    }
    public void addInfo(View v) {
        String title = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String topic = editTextTopic.getText().toString();

        InfoSet info = new InfoSet(title, email, topic);

//        Map<String, Object> info = new HashMap<>();
//        info.put(KEY_NAME, title);
//        info.put(KEY_AGE, age);

        //db.document("Data/First Data");

        allInfoRef.add(info);

        infoRef.set(info).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity1.this, "Info saved", Toast.LENGTH_SHORT).show();
            }
        } )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity1.this, "Error",Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });

    }

    public void loadSingleInfo(View v) {
        infoRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    String title = documentSnapshot.getString(KEY_NAME);
                    String email = documentSnapshot.getString(KEY_EMAIL);
                    String topic = documentSnapshot.getString(KEY_TOPIC);

                    textViewData.setText("Name" + title + "\n" + "Email" + email + "\nTopic" + topic);

                } else {
                    Toast.makeText(MainActivity1.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity1.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

    public void loadInfo(View v) {
        allInfoRef.whereEqualTo("Topic", editTextTopic).orderBy("Topic", Query.Direction.ASCENDING)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            //Map<String, Object> info = new HashMap<>();

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String data = "";

                for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots) {
                    InfoSet info = documentSnapshot.toObject(InfoSet.class);

                    //info.setDocumentId(documentSnapshot.getId());

                    // String documentID = info.getDocumentId();
                    String title = info.getTitle();
                    String email = info.getEmail();
                    String topic = info.getTopic();

                    data+=  "Name" + title + "\nEmail: " + email + "\nTopic" + topic;
                }

                textViewData.setText(data);
            }
        });
    }
}
