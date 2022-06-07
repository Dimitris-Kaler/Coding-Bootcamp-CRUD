package entities;

import java.util.Date;

public class Course {

    private int id;

    private String title;

    private String stream;

    private String type;

    private Date startDate;

    private Date endDate;
   public Course(int id,String title,String stream,String type,Date startDate,Date endDate){
        this.id=id;
        this.title=title;
        this.stream=stream;
        this.type=type;
        this.startDate=startDate;
        this.endDate=endDate;
    }



  public  Course(String title, String stream, String type, Date startDate, Date endDate){
        this.title=title;
        this.stream=stream;
        this.type=type;
        this.startDate=startDate;
        this.endDate=endDate;

    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;

    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



    public String toString(){
        return "id: "+id+" title: "+" stream: "+stream+" "+" type: "+type+" start_date:"+ startDate +" end_date: "+ endDate;
    }


}

