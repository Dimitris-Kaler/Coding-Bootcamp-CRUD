public class Trainer {
    private int id;
    private String first_name;
    private String last_name;
    private String subject;

    Trainer(int id,String first_name,String last_name,String subject){
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.subject=subject;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name(){
        return last_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setLast_name(String last_name){
        this.last_name=last_name;
    }
}

