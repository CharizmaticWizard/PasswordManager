package com.example.passwordmanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends Activity  {
    Button b1,b2;
    EditText ed, ed1, ed2;
    TextView tx1;

    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        b1 = findViewById(R.id.button);
        ed = findViewById(R.id.editText);
        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);

        b2 = findViewById(R.id.button2);
        tx1 = findViewById(R.id.textView2);

        ref = FirebaseDatabase.getInstance().getReference().child("Logins");

        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if((ed1.getText().toString().length()>= 8)&& (ed1.getText().toString().equals(ed2.getText().toString()))){
                    tx1.setText("");
                    Toast.makeText(getApplicationContext(),
                            "Registered...",Toast.LENGTH_SHORT).show();
                    basicWrite();
                    goToSecondActivity();
                }else if(ed1.getText().toString().length()< 8){
                    Toast.makeText(getApplicationContext(),
                            "User not Registered",Toast.LENGTH_SHORT).show();
                    tx1.setText("Password should be longer than 8 characters.");
                }else if (!ed1.getText().toString().equals(ed2.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "User not Registered",Toast.LENGTH_SHORT).show();
                    tx1.setText("Registered Password should be identical");
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSecondActivity();
            }
        });
    }

    private void goToSecondActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    private void basicWrite() {
        // Write a message to the database
        String u=ed.getText().toString();
        int p=ed1.getText().toString().hashCode();

        Login e=new Login(u, p);
        ref.child(e.username).setValue(e);
    }
}


