package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.chip.Chip;

import android.view.View;
import android.app.Dialog;
import android.app.AlertDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import java.util.Map;

import TSPTW.*;
import android.content.DialogInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private Chip addtask,runtask,maptask,historytask,defaulttask,deltask;
    //新增地點相關
    private Dialog dialog_add_task;
    private ImageButton Ok_Button,del_Button;
    private EditText title,pos,date_start, date_end, time_start, time_end, time_service,desc;
    private RadioGroup radio_type;
    //ListView相關
    private ListView LV_task_list;

    List<Task> TaskList= new ArrayList<Task>();
    ArrayList<String> schedule = new ArrayList<String>();
    ArrayList<String> schedule_datail = new ArrayList<String>();
    JSONArray array;
    Vector<Event> events= new Vector<Event>();
    Task_Mode task_mode;
    int update_index=0;
    //新增地點相關
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        addtask = (Chip) findViewById(R.id.chip1);
        addtask.setOnClickListener(this);
        runtask = (Chip) findViewById(R.id.chip2);
        runtask.setOnClickListener(this);
        maptask = (Chip) findViewById(R.id.chip3);
        maptask.setOnClickListener(this);
        historytask = (Chip) findViewById(R.id.chip4);
        historytask.setOnClickListener(this);
        defaulttask = (Chip) findViewById(R.id.chip5);
        defaulttask.setOnClickListener(this);
        deltask= (Chip) findViewById(R.id.chip6);
        deltask.setOnClickListener(this);


        dialog_add_task = new Dialog(this);
        dialog_add_task.setContentView(R.layout.add_task);   //dialog1是我上面建立xml的檔名
        title= (EditText) dialog_add_task.findViewById(R.id.title);
        pos=  (EditText) dialog_add_task.findViewById(R.id.pos);
        radio_type=  (RadioGroup) dialog_add_task.findViewById(R.id.radio_type);
        date_start =  (EditText) dialog_add_task.findViewById(R.id.date_start);   //dialog上的date_start
        time_start = (EditText) dialog_add_task.findViewById(R.id.time_start);   //dialog上的time_start
        date_end =  (EditText) dialog_add_task.findViewById(R.id.date_end);   //dialog上的date_start
        time_end = (EditText) dialog_add_task.findViewById(R.id.time_end);   //dialog上的time_end
        time_service = (EditText) dialog_add_task.findViewById(R.id.time_service);   //dialog上的time_service
        desc=(EditText) dialog_add_task.findViewById(R.id.desc);
        Ok_Button = (ImageButton) dialog_add_task.findViewById(R.id.Ok_Button);
        Ok_Button.setOnClickListener(this);
        del_Button = (ImageButton) dialog_add_task.findViewById(R.id.del);
        del_Button.setOnClickListener(this);

        LV_task_list= (ListView) findViewById(R.id.history_list);
        LV_task_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int index, long id) {
                task_mode = Task_Mode.Update_Mode;
                update_index=index;
                show_addtask();
                return true;
            }
        });
        set_TaskList_View();
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chip1:
                task_mode=Task_Mode.New_Mode;
                show_addtask();
                break;
            case R.id.Ok_Button:
                set_TaskList();
                set_TaskList_View();
                break;
            case R.id.del:
                del_TaskList();
                set_TaskList_View();
                break;
            case R.id.chip2:
                tsptw_Run();
                break;
            case R.id.chip3:
                showmap();
                break;
            case R.id.chip4:
                showHistory();
                break;
            case R.id.chip5:
                defaultSchedule();
                set_TaskList_View();
                break;
            case R.id.chip6:
                delAllTask();
                set_TaskList_View();
                break;
            /*case R.id.chip7:
                createcalendarEvent();
                break;*/
        }
    }
    public void del_TaskList() {
        events.remove(update_index);
        dialog_add_task.hide();
    }
    //拿到keyin資料
    public void set_TaskList() {
        Double double_lat = 0.0;
        Double double_lon = 0.0;
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocationName(pos.getText().toString(), 8);
            if (addresses.size() > 0) {
                double_lat = (double) (addresses.get(0).getLatitude());
                double_lon = (double) (addresses.get(0).getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str_title = title.getText().toString();
        String str_pos = pos.getText().toString();
        String str_radio_type = ((RadioButton) dialog_add_task.findViewById(radio_type.getCheckedRadioButtonId())).getText().toString();
        int type = 0;
        switch (str_radio_type) {
            case "start":
                type = Event.START;
                break;
            case "end":
                type = Event.END;
                break;
            case "new":
                type = Event.NEW;
                break;
            case "old":
                type = Event.OLD;
                break;
        }
        String str_date_start = date_start.getText().toString();
        String str_time_start = time_start.getText().toString();
        String str_date_end = date_end.getText().toString();
        String str_time_end = time_end.getText().toString();
        String str_time_service = time_service.getText().toString();
        String str_desc = desc.getText().toString();
        String keyinMessage = "";

        if(str_title.length()==0){
            keyinMessage+="請輸入Title\n";
        }
         if(str_pos.length()==0){
             keyinMessage+="請輸入Position\n";
         }
         if(str_radio_type.length()==0){
             keyinMessage+="請輸入Type\n";
         }
         if(str_date_start.length()==0){
             keyinMessage+="請輸入Start Date\n";
         }
         if(str_time_start.length()==0){
             keyinMessage+="請輸入Start Time\n";
         }
         if(str_date_end.length()==0){
             keyinMessage+="請輸入End Date\n";
         }
         if(str_time_end.length()==0){
             keyinMessage+="請輸入End Time\n";
         }
         if(str_time_service.length()==0){
             keyinMessage+="請輸入Service Time\n";
         }
//         if(str_desc.length()==0){
//             keyinMessage+="請輸入Description\n";
//         }
        if(keyinMessage=="") {
            dialog_add_task.hide();
            int[] arr_date_start = Tointarr(str_date_start.split("/"));
            int[] arr_time_start = Tointarr(str_time_start.split(":"));
            int[] arr_date_end = Tointarr(str_date_end.split("/"));
            int[] arr_time_end = Tointarr(str_time_end.split(":"));
            int[] arr_time_service = Tointarr(str_time_service.replace("hr", ",").replace("min", ",").split(","));
            int servicetime = arr_time_service[0] * 60 + arr_time_service[1];
            if (task_mode == Task_Mode.New_Mode) {
                events.add(new Event(str_title,new Position(double_lat, double_lon,str_pos),new Time(arr_date_start[0],arr_date_start[1], arr_date_start[2], arr_time_start[0], arr_time_start[1]),new Time(arr_date_end[0], arr_date_end[1], arr_date_end[2], arr_time_end[0], arr_time_end[1]),new Time(servicetime),type,str_desc));
            } else {
                events.set(update_index, new Event(str_title, new Position(double_lat, double_lon,str_pos), new Time(arr_date_start[0], arr_date_start[1], arr_date_start[2], arr_time_start[0], arr_time_start[1]), new Time(arr_date_end[0], arr_date_end[1], arr_date_end[2], arr_time_end[0], arr_time_end[1]), new Time(servicetime), type, str_desc));
            }
        }
        else{
            Toast.makeText(this, keyinMessage.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void set_TaskList_View() {
        List<String> LocationList = gettasklistdata();
        ListAdapter mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, LocationList);
        LV_task_list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        LV_task_list.setAdapter(mAdapter);
        for ( int i=0; i < LV_task_list.getAdapter().getCount(); i++) {
            LV_task_list.setItemChecked(i, true);
        }
    }
    public List<String> gettasklistdata(){
        List<String> LocationList= new ArrayList<>();;
        String str_schedule_datail="[";
        for(int i=0;i<events.size();i++){
            str_schedule_datail+="{"+events.get(i).toString()+"}";
            if(i!=events.size()-1){
                str_schedule_datail+=",";
            }
        }
        str_schedule_datail+="]";
        try {
            array=new JSONArray(str_schedule_datail);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                LocationList.add(jsonObject.getString("Title"));
            }
        } catch (JSONException e) {
        }
        return  LocationList;
    }
    public void tsptw_Run() {
        if(events.size()>0) {
            AlertDialog.Builder dialog_history = new AlertDialog.Builder(this);
            EditText editText = new EditText(MainActivity.this); //final一個editText
            dialog_history.setView(editText);
            dialog_history.setTitle("請輸入此行程名稱");
            dialog_history.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Vector<Event> new_events = new Vector<Event>();
                    //deepclone(events,new_events);
                    new_events=(Vector)events.clone();

                    TSPTW tsptw = new TSPTW(events,false);

                    boolean result=tsptw.run();
                    String str_schedule_datail="[";
                    for(int i=0;i<events.size();i++){
                        str_schedule_datail+="{"+events.get(i).toString()+"}";
                        if(i!=events.size()-1){
                            str_schedule_datail+=",";
                        }
                    }
                    str_schedule_datail+="]";

                    System.out.println("new_events"+str_schedule_datail);

                    schedule_datail.add(str_schedule_datail);
                    schedule.add(editText.getText().toString());
                    set_TaskList_View();
                }
            });
            dialog_history.create().show();
        }
    }
    private void show_addtask() {
        if(task_mode==Task_Mode.New_Mode) {
            del_Button.setVisibility(View.INVISIBLE);
            title.getText().clear();
            pos.getText().clear();
            radio_type.check(R.id.radio_new);
            date_start.getText().clear();
            date_end.getText().clear();
            time_start.getText().clear();
            time_end.getText().clear();
            time_service.getText().clear();
            desc.getText().clear();
        }
        else{
            del_Button.setVisibility(View.VISIBLE);
            Event event=events.get(update_index);
            title.setText(event.title);
            pos.setText(event.position.toNameString());
            switch (event.type) {
                case 1:
                    radio_type.check(R.id.radio_start);
                    break;
                case 2:
                    radio_type.check(R.id.radio_end);
                    break;
                case 3:
                    radio_type.check(R.id.radio_new);
                    break;
                case 4:
                    radio_type.check(R.id.radio_old);
                    break;
            }
            date_start.setText(event.starTime.toDateString());
            time_start.setText(event.starTime.toTimeString());
            date_end.setText(event.endTime.toDateString());
            time_end.setText(event.endTime.toTimeString());
            time_service.setText(event.serviceTime.toSerString().replace(":","hr")+"min");
            desc.setText(event.description.toString());
        }
        dialog_add_task.show();
    }
    private void showHistory() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,History.class);
        intent.putExtra("historyobject", schedule);
        intent.putExtra("historyobjectdetail", schedule_datail);
        startActivity(intent);
    }
    private void defaultSchedule() {
        events = new Vector<Event>();
        TSPTW.importDefaultData(events);
    }
    public void dateClick(View Dialogview) {   //選擇日期
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(Dialogview.getContext(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String tmpStr;
                if(month<10) {
                    tmpStr = "0" + Integer.toString(month);
                }else{
                    tmpStr = Integer.toString(month);
                }
                if(Dialogview.getId() == R.id.date_start){
                    date_start.setText(year + "/" + tmpStr + "/" + day);
                }else if(Dialogview.getId() == R.id.date_end){
                    date_end.setText(year + "/" + tmpStr + "/" + day);
                }
            }
        }, year, month, day).show();
    }
    public void timeClick(View Dialogview) {   //選擇時間
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(Dialogview.getContext(), new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String tmpStr = Integer.toString(hourOfDay);
                String tmpStr2 = Integer.toString(minute);
                if(hourOfDay<10) {
                    tmpStr = "0" + tmpStr;
                } if(minute<10) {
                    tmpStr2 = "0" + tmpStr2;
                }
                if(Dialogview.getId() == R.id.time_start){
                    time_start.setText( tmpStr + ":" + tmpStr2);
                }else if(Dialogview.getId() == R.id.time_end){
                    time_end.setText( tmpStr + ":" + tmpStr2);
                }else if(Dialogview.getId() == R.id.time_service){
                    time_service.setText( hourOfDay + " hr " + minute + " min");
                }
            }
        }, hour, minute, true).show();
    }
    public void showmap(){
        Boolean checked = false;
        if(events.size()!=0) {
            String tmpStr = "https://www.google.co.in/maps/dir";
            for (int i = 0; i < events.size(); i++) {
                if(LV_task_list.isItemChecked(i)){
                    checked = true;
                    tmpStr = tmpStr + "/" + events.get(i).position.toLoatLonString().replace("(","").replace(")","");
                }
            }
            if(checked == true){
                Uri gmmIntentUri = Uri.parse(tmpStr);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        }
    }
    public void deepclone( Vector<Event>ori_v , Vector<Event> new_v){
        for(int i=0;i<ori_v.size();i++){
           Event ev=ori_v.get(i);
            int[] arr_starDate = Tointarr(ev.starTime.toDateString().replace(" ","").split("/"));
            int[] arr_starTime = Tointarr(ev.starTime.toTimeString().replace(" ","").split(":"));
            int[] arr_endDate = Tointarr(ev.endTime.toDateString().replace(" ","").split("/"));
            int[] arr_endTime = Tointarr(ev.endTime.toTimeString().replace(" ","").split(":"));
            int[] arr_servicetime = Tointarr(ev.serviceTime.toTimeString().replace(" ","").split(":"));
            int servicetime = arr_servicetime[0] * 60 + arr_servicetime[1];
            new_v.add(new Event(ev.title.toString(),new Position(ev.position.getLatitude(), ev.position.getLongitude(),ev.position.toNameString()),
                    new Time(arr_starDate[0],arr_starDate[1], arr_starDate[2], arr_starTime[0], arr_starTime[1]),
                    new Time(arr_endDate[0], arr_endDate[1], arr_endDate[2], arr_endTime[0], arr_endTime[1]),new Time(servicetime),ev.type,ev.description));
        }
    }
    public void delAllTask(){
        int events_size=events.size();
        for(int i=0;i<events_size;i++){
            events.remove(0);
        }
    }
    public void createcalendarEvent(){
        int events_size=events.size();
        for(int i=0;i<events_size;i++){
            events.remove(0);
        }
    }


    public int[] Tointarr(@NonNull String [] arr) {
        int[] intArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            try {
                intArray[i] = Integer.parseInt(arr[i].replace(" ",""));
            } catch (Exception e) {
                System.out.println("失敗");
            }
        }
        return intArray;
    }
    enum  Task_Mode{
        New_Mode,Update_Mode;
    }
}

