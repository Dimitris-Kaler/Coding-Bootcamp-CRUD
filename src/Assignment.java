import java.util.Date;

public class Assignment {
    private int id;
    private String title;
    private String description;
    private int max_oral_mark;
    private int max_total_mark;
    private Date sub_date_time;

    Assignment(int id,String title,String description,int max_oral_mark,int max_total_mark,Date sub_date_time ){
        this.id=id;
        this.title=title;
        this.description=description;
        this.max_oral_mark=max_oral_mark;
        this.max_total_mark=max_total_mark;
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

    public int getMax_oral_mark() {
        return max_oral_mark;
    }

    public void setMax_oral_mark(int max_oral_mark) {
        this.max_oral_mark = max_oral_mark;
    }

    public int getMax_total_mark() {
        return max_total_mark;
    }

    public void setMax_total_mark(int max_total_mark) {
        this.max_total_mark = max_total_mark;
    }

    public Date getSub_date_time() {
        return sub_date_time;
    }

    public void setSub_date_time(Date sub_date_time) {
        this.sub_date_time = sub_date_time;
    }
}

