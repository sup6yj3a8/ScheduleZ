package com.example.myapplication;


public class Task {
    private String title;
    private String pos;
    private String type;
    private String date_start;
    private String date_end;
    private String time_start;
    private String time_end;
    private String time_service;
    private String desc;
    private Double lat;
    private Double lon;

    // constructor
    public Task(String title, String pos,Double lat,Double lon,String radio_type,String date_start,String date_end, String time_start,String time_end,String time_service,String desc) {
        this.title = title;
        this.pos = pos;
        this.lat = lat;
        this.lon = lon;
        this.type = radio_type;
        this.date_start = date_start;
        this.date_end = date_end;
        this.time_start = time_start;
        this.time_end = time_end;
        this.time_service = time_service;
        this.desc = desc;
    }

    public String getTitle() { return title; }
    public String getPos() { return pos; }
    public String getType() { return type; }
    public String getDate_start() { return date_start; }
    public String getTime_start() { return time_start; }
    public String getDate_end() { return date_end; }
    public String getTime_end() { return time_end; }
    public String getTime_service() { return time_service; }
    public String getDesc() { return desc; }

    public String getLatAndLon() { return lat.toString()+","+lon.toString(); }
    public String getdetail() {
        String message= "title:"+title+"\r\n"+
                "pos:"+pos+"\r\n"+
                "radio_type:"+type+"\r\n"+
                "date_start:"+date_start+"\r\n"+
                "time_start:"+time_start+"\r\n"+
                "date_end:"+date_end+"\r\n"+
                "time_end:"+time_end+"\r\n"+
                "time_service:"+time_service+"\r\n"+
                "desc:"+desc+"\r\n";
        return message;
    }

}