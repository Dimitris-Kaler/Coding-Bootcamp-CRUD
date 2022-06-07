package entities;
import java.util.Date;

public class Assignment {
    private int id;
    private String title;
    private String description;

    private int maxOralMark;
    private int maxTotalMark;
    private Date subDateTime;

   public Assignment(int id,String title,String description,int maxOralMark,int maxTotalMark,Date subDateTime ){
        this.id=id;
        this.title=title;
        this.description=description;
        this.maxOralMark=maxOralMark;
        this.maxTotalMark=maxTotalMark;
        this.subDateTime=subDateTime;
    }
   public  Assignment(String title,String description,int maxOralMark,int maxTotalMark,Date subDateTime ){
        this.title=title;
        this.description=description;
        this.maxOralMark=maxOralMark;
        this.maxTotalMark=maxTotalMark;
        this.subDateTime=subDateTime;
    }


    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxOralMark() {
        return maxOralMark;
    }

    public void setMax_oral_mark(int maxOralMark) {
        this.maxOralMark= maxOralMark;
    }

    public int getMaxTotalMark() {
        return maxTotalMark;
    }

    public void setMaxTotalMark(int max_total_mark) {
        this.maxTotalMark = maxTotalMark;
    }

    public Date getSubDateTime() {
        return subDateTime;
    }

    public void setSub_date_time(Date subDateTime) {
        this.subDateTime = subDateTime;
    }

    public String toString(){
        return "id: "+id+" title: "+title+ " description: "+description+" "+" MaxOralMark: "+maxOralMark+" MaxTotalMark:"+ maxTotalMark +" SubDateTime: "+ subDateTime;
    }
}


