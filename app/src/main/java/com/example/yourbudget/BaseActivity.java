package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {

    ListView listViewMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Intent user = getIntent();
        String userName = user.getStringExtra("name");
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText("Hi, "+userName);
        listViewMenu = (ListView) findViewById(R.id.listView);
        ArrayAdapter<CharSequence> adapterMenu = ArrayAdapter.createFromResource(this,R.array.menu,android.R.layout.simple_list_item_1);
        listViewMenu.setAdapter(adapterMenu);
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    Intent goToCreate = new Intent(listViewMenu.getContext(),CreateActivity.class);
                    listViewMenu.getContext().startActivity(goToCreate);
                }
                if(position==3) {
                    Intent goToTips = new Intent(listViewMenu.getContext(),ToolsActivity.class);
                    listViewMenu.getContext().startActivities(new Intent[]{goToTips});
                }
                else if(position==4){
                    finish();
                }
            }
        });
    }
}