package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToBase(View v){
        EditText name = (EditText) findViewById(R.id.editTextTextPersonName);
        String userName = String.valueOf(name.getText().toString());
        Intent goToNext = new Intent();
        goToNext.setClass(this,BaseActivity.class);
        goToNext.putExtra("name",userName);
        startActivity(goToNext);
        finish();
    }
}