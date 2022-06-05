import java.util.Date;
import java.util.List;

public class AssignmentTest {
    public static void main(String[] args) {
        Assignment assignment=new Assignment("responsive","learn the basics",20,80,new Date());
        AssignmentCrudServices acs=new AssignmentCrudServices();
        acs.save(assignment);


        List<Assignment> assignmentList=acs.findAll();
        for(int i=0; i<assignmentList.size(); i++){
            System.out.println(assignmentList.get(i));
        }
        System.out.println("The course you are looking for is:");
        System.out.println(acs.findById(1));

        acs.delete(2);

        acs.update(new Assignment(1,"podarakia","krata tin mpala psila",19,92,new Date()));
    }
}
