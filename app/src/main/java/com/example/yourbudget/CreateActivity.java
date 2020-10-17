package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class CreateActivity extends AppCompatActivity {

    SharedPreferences list;
    private DBController dbCon;
    String duration = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        list = getSharedPreferences("LIST_PREF",MODE_PRIVATE);
        final SharedPreferences.Editor editor = list.edit();

        final EditText listName = findViewById(R.id.editTextListName);
        final EditText budget = findViewById(R.id.editTextBudgetLimit);
        Button buttonCreateList = findViewById(R.id.buttonCreate);

        dbCon = new DBController(this);

        RadioGroup dur = findViewById(R.id.radioGroup);
        dur.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonDay:
                        duration = "0";
                        break;
                    case R.id.radioButtonMonth:
                        duration = "1";
                        break;
                    case R.id.radioButtonYear:
                        duration = "2";
                }
            }
        });

        buttonCreateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = listName.getText().toString();
                int cost = Integer.parseInt(budget.getText().toString());
                int dur = Integer.parseInt(duration);

                if (TextUtils.isEmpty(name)) {
                    listName.setError("List name is empty/not valid");
                    return;
                }

                ListDetails list = new ListDetails(name, dur, cost);
                dbCon.addList(list);
                int id = list.get_id();
                editor.putInt("listId",id);
                editor.apply();
                Toast.makeText(CreateActivity.this, "List Created", Toast.LENGTH_SHORT).show();

                Intent addItems = new Intent(CreateActivity.this,AddItemsActivity.class);
                CreateActivity.this.startActivity(addItems);

            }
        });
    }
}