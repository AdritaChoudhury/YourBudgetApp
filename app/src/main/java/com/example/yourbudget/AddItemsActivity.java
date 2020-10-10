package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddItemsActivity extends AppCompatActivity {

    EditText edt_name, edt_cost;
    Button add_btn, del_btn, query_btn, tot_btn;

    //DB
    private DBController dbCon;

    //ListView
    private ListView lv;
    ArrayList<String> data;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        // UI
        add_btn = (Button) findViewById(R.id.button_add);
        del_btn = (Button) findViewById(R.id.button_del);
        query_btn = (Button) findViewById(R.id.button_query);
        tot_btn = (Button) findViewById(R.id.button_tot);
        edt_name = (EditText) findViewById(R.id.editText_name);
        edt_cost = (EditText) findViewById(R.id.editText_cost);


        // ListView
        lv = (ListView) findViewById(R.id.listView);
        data = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);

        //DB
        dbCon = new DBController(this);

        // OnClickListeners
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edt_name.getText().toString();
                int cost = Integer.parseInt(edt_cost.getText().toString());

                if (name == null || TextUtils.isEmpty(name)) {
                    edt_name.setError("Name field is empty/not valid");
                    return;
                }
                if (cost == 0) {
                    edt_cost.setError("Address field is empty/not valid");
                    return;
                }

                BudgetList budgetList = new BudgetList(name, cost);
                dbCon.addItem(budgetList);
                Toast.makeText(AddItemsActivity.this, "Record Added", Toast.LENGTH_SHORT).show();

                data.clear();
                adapter.notifyDataSetChanged();

                List<BudgetList> budList = dbCon.getAllItems();

                for (int i = 0; i < budList.size(); i++) {
                    BudgetList bl = budList.get(i);
                    String info = "ID : " + bl.get_id() + ", " + "Name : " + bl.get_name()
                            + ", " + "Cost : " + bl.get_cost();
                    data.add(info);
                    System.out.println(info);
                }

                adapter.notifyDataSetChanged();
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_name.getText().toString();
                if (name == null || TextUtils.isEmpty(name)) {
                    edt_name.setError("Name field is empty/not valid");
                    return;
                }

                List<BudgetList> budList = dbCon.getAllItems();

                for (int i = 0; i < budList.size(); i++) {
                    if (budList.get(i).get_name().equals(name)) {
                        BudgetList bl = budList.get(i);
                        dbCon.deleteItem(bl);
                    }
                }
                Toast.makeText(AddItemsActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();

                data.clear();
                adapter.notifyDataSetChanged();

                for (int i = 0; i < budList.size(); i++) {
                    BudgetList bl = budList.get(i);
                    String info = "ID : " + bl.get_id() + ", " + "Name : " + bl.get_name()
                            + ", " + "Cost : " + bl.get_cost();
                    data.add(info);
                    System.out.println(info);
                }

                adapter.notifyDataSetChanged();
            }
        });

        query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edt_name.getText().toString();
                if (name == null || TextUtils.isEmpty(name)) {
                    edt_name.setError("Name field is empty/not valid");
                    return;
                }

                data.clear();
                adapter.notifyDataSetChanged();

                List<BudgetList> budList = dbCon.getAllItems();

                for (int i = 0; i < budList.size(); i++) {
                    if (budList.get(i).get_name().equals(name)) {
                        BudgetList bl = budList.get(i);
                        String info = "ID : " + bl.get_id() + ", " + "Name : " + bl.get_name()
                                + ", " + "Cost : " + bl.get_cost();
                        data.add(info);
                        System.out.println(info);
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });
        tot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                adapter.notifyDataSetChanged();

                int total=0;
                List<BudgetList> budList = dbCon.getAllItems();
                for (int i = 0; i < budList.size(); i++) {
                    BudgetList bl = budList.get(i);
                    String info = "ID : " + bl.get_id() + ", " + "Name : " + bl.get_name()
                            + ", " + "Cost : " + bl.get_cost();
                    data.add(info);
                    System.out.println(info);
                }
                for(int i = 0; i < budList.size();i++) {
                    BudgetList bl = budList.get(i);
                    total = total + bl.get_cost();
                }
                data.add("Total: "+String.valueOf(total));
                System.out.println("Total: "+total);
            }
        });
    }

    public void goToBase(View v) {
        Intent intent = new Intent();
        intent.setClass(this,BaseActivity.class);
        startActivity(intent);
        finish();
    }
}