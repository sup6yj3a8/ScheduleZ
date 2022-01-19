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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Lv2_History extends AppCompatActivity {
    String historyobjectdetail="";
    JSONArray array;
    ListView Lv2_history_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv2_history);
        init();
    }
    private void init() {
        Lv2_history_list= (ListView) findViewById(R.id.Lv2_history_list);
        historyobjectdetail =  getIntent().getStringExtra("historyobjectdetail");
        set_TaskList_View();
    }
    public void set_TaskList_View() {
         List<String> LocationList = gettasklistdata();
         ListAdapter mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, LocationList);
         Lv2_history_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //    @Override
            public void onItemClick(AdapterView<?> parent, View view, int index,long id) {
                try {
                    array = new JSONArray(historyobjectdetail);
                    JSONObject jsonObject = array.getJSONObject(index);
                    String message= "Title : "+jsonObject.getString("Title")+"\r\n"+
                                "Position : "+jsonObject.getString("Position")+"\r\n"+
                                "Type : "+jsonObject.getString("Type")+"\r\n"+
                                "Start Time : "+jsonObject.getString("Start Time")+"\r\n"+
                                "End Time : "+jsonObject.getString("End Time")+"\r\n"+
                                "Service Time : "+jsonObject.getString("Service Time")+"\r\n"+
                                "Description : "+jsonObject.getString("Description")+"\r\n";
                    AlertDialog.Builder builder = new AlertDialog.Builder(Lv2_History.this);
                    builder.setMessage(message);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } catch (JSONException e) {

                }
            }
         });
         Lv2_history_list.setAdapter(mAdapter);
    }
    public List<String> gettasklistdata(){
        List<String> LocationList= new ArrayList<>();
        try {
            array = new JSONArray(historyobjectdetail);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                LocationList.add(jsonObject.getString("Title"));
            }
        } catch (JSONException e) {
        }
        return  LocationList;
    }
}