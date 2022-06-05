import java.time.LocalDate;

public class Student {
    private int id;
    private String first_name;
    private String last_name;
    private LocalDate date_of_birth;
    private int tuition_fees;

    Student(int id, String first_name,String last_name,LocalDate date_of_birth,int tuition_fees){
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.date_of_birth=date_of_birth;
        this.tuition_fees=tuition_fees;

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getTuition_fees() {
        return tuition_fees;
    }

    public void setTuition_fees(int tuition_fees) {
        this.tuition_fees = tuition_fees;
    }
}

