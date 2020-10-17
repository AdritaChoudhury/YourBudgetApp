package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EditListActivity extends AppCompatActivity {

    Button buttonUpdate;
    private DBController dbCon;
    private ListView lv;
    ArrayList<String> data;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        //button
        buttonUpdate = (Button) findViewById(R.id.buttonUpdateList);

        // ListView
        lv = (ListView) findViewById(R.id.listView);
        data = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);

        //DB
        dbCon = new DBController(this);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                data.clear();
                adapter.notifyDataSetChanged();

                List<ListDetails> budList = dbCon.getAllLists();

                for (int i = 0; i < budList.size(); i++) {
                    ListDetails bl = budList.get(i);
                    String info = "ID : " + bl.get_id() + ", " + "Name : " + bl.get_name() + ", " + "Budget : " + bl.get_budget();
                    data.add(info);
                    System.out.println(info);
                }

                adapter.notifyDataSetChanged();
            }
        });
    }
    public void goToBase(View v) {
        Intent goToBase = new Intent();
        goToBase.setClass(this,BaseActivity.class);
        startActivity(goToBase);
        finish();
    }
}