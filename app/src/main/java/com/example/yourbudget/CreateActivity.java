package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;


public class CreateActivity extends AppCompatActivity {

    SharedPreferences list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        list= getSharedPreferences("LIST_PREF",MODE_PRIVATE);
        final SharedPreferences.Editor editor = list.edit();

        RadioGroup dur = (RadioGroup) findViewById(R.id.radioGroup);
        dur.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String duration = "None";
                switch (checkedId){
                    case R.id.radioButtonDay:
                        duration = "Day";
                        break;
                    case R.id.radioButtonMonth:
                        duration = "Month";
                        break;
                    case R.id.radioButtonYear:
                        duration = "Year";
                }
                editor.putString("Duration",duration);
                editor.apply();
            }
        });
    }
    public void createList(View v) {
        final SharedPreferences.Editor editor = list.edit();
        EditText listName = (EditText) findViewById(R.id.editTextListName);
        EditText budget = (EditText) findViewById(R.id.editTextBudgetLimit);
        String nameList = listName.getText().toString();
        int budgetLimit = Integer.valueOf(budget.getText().toString());

        if(nameList.length() == 0)
            listName.setError("List Name is required!!");

        editor.putString("ListName",nameList);
        editor.putInt("BudgetLimit",budgetLimit);
        editor.apply();

        Intent addItems = new Intent();
        addItems.setClass(this,AddItemsActivity.class);
        startActivity(addItems);
        finish();
    }
}