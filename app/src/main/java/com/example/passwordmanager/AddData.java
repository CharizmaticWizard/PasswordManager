package com.example.passwordmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddData extends AppCompatActivity {

    EditText ed, ed1, ed2;
    Button button, button2;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        ed = findViewById(R.id.editText);
        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        ref = FirebaseDatabase.getInstance().getReference().child("Entities");


        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                goToSecondActivity();

            }

        });
    }

    private void goToSecondActivity() {

        Intent intent = new Intent(this, Biometric.class);

        startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void basicWrite(View view) {
        // Write a message to the database
        String i=ed.getText().toString();
        String u=AppUtils.encrypt(ed1.getText().toString());
        String p=AppUtils.encrypt(ed2.getText().toString());

        Entity e=new Entity(i, u, p);
        ref.child(e.identifier).setValue(e);
        Toast.makeText(AddData.this,"data inserted successfully",Toast.LENGTH_LONG).show();
    }

}
