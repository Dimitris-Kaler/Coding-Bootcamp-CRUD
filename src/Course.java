import java.util.Date;
//import java.time.LocalDate;

public class Course {
    private int id;
    private String title;
    private String stream;
    private String type;
    private Date start_date;
    private Date end_date;


    Course(String title,String stream,String type,Date start_date,Date end_date){
        this.title=title;
        this.stream=stream;
        this.type=type;
        this.start_date=start_date;
        this.end_date=end_date;

    }



    Course(int id,String title,String stream,String type,Date start_date,Date end_date){
        this.id=id;
        this.title=title;
        this.stream=stream;
        this.type=type;
        this.start_date=start_date;
        this.end_date=end_date;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
    public String toString(){
        return "id: "+id+" title: "+" stream: "+stream+" "+" type: "+type+" start_date:"+start_date+" end_date: "+end_date;

    }
}

