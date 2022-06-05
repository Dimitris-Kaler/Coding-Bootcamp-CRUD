import java.util.Date;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int tuitionFees;

    Student(int id, String firstName,String lastName,Date dateOfBirth,int tuitionFees){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
        this.tuitionFees=tuitionFees;

    }

    Student(String firstName,String lastName,Date dateOfBirth,int tuitionFees){
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
        this.tuitionFees=tuitionFees;

    }

    public int getId(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public String toString(){
        return "id: "+id+" First_Name: "+firstName+" Last_Name: "+lastName+" DateOfBirth: "+ dateOfBirth+" tuitionFees: "+tuitionFees+" !!";
    }

}

