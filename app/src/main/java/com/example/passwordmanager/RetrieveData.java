package com.example.passwordmanager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetrieveData extends AppCompatActivity {
    EditText ed;
    TextView ed1, ed2, ed5;
    Button button, button2;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);

        ed = findViewById(R.id.editText);
        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                goToSecondActivity();

            }

        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                basicRead();

            }

        });
    }

    private void goToSecondActivity() {

        Intent intent = new Intent(this, AddData.class);

        startActivity(intent);

    }

    public void basicRead() {
        String i= ed.getText().toString();
        ref = FirebaseDatabase.getInstance().getReference().child("Entities").child(i);

        ref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String u = dataSnapshot.child("username").getValue().toString();
                String p = dataSnapshot.child("password").getValue().toString();

                    ed1.setText(AppUtils.decrypt(u));
                    ed2.setText(AppUtils.decrypt(p));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RetrieveData.this, "Retrieval failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}