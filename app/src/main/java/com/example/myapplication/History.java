package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import TSPTW.Event;
public class History extends AppCompatActivity {

    ArrayList<String> schedule = new ArrayList<String>();
    ArrayList<String> historyobjectdetail = new ArrayList<String>();
    ListView LV_historylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        init();
    }
    private void init() {
        Intent i = getIntent();
        schedule = (ArrayList<String>) getIntent().getSerializableExtra("historyobject");
        LV_historylist= (ListView) findViewById(R.id.history_list);
        set_ScheduleList_View();
        historyobjectdetail = (ArrayList<String>) getIntent().getSerializableExtra("historyobjectdetail");
    }
    public void set_ScheduleList_View() {
        ListAdapter mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule);
        LV_historylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //    @Override
           public void onItemClick(AdapterView<?> parent, View view, int index,long id) {
               Intent intent = new Intent();
               intent.setClass(History.this,Lv2_History.class);
               intent.putExtra("historyobjectdetail", historyobjectdetail.get(index));
               startActivity(intent);
            }
        });
        LV_historylist.setAdapter(mAdapter);

    }
}