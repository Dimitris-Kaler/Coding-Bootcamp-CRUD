import java.sql.Connection;

public class Trainer {
    private int id;
    private String firstName;
    private String lastName;
    private String subject;

    Trainer(int id,String first_name,String last_name,String subject){
        this.id=id;
        this.firstName=first_name;
        this.lastName=last_name;
        this.subject=subject;
    }
    Trainer(String firstName,String lastName,String subject){
        this.firstName=firstName;
        this.lastName=lastName;
        this.subject=subject;

    }
    public int getId(){
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String last_name){
        this.lastName=last_name;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String toString(){
        return "id: "+id+" First_Name: "+firstName+" Last_Name: "+lastName+" subject: "+ subject+" !!";
    }




}

