package com.example.zezva.kiss_me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    EditText  editTextName ;
    EditText  editTextGender;
    Button buttonOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextGender = (EditText) findViewById(R.id.editTextGender);
        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonOk:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}
