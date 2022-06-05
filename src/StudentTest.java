import java.util.Date;
import java.util.List;

public class StudentTest {
    public static void main(String[] args) {

        Student student=new Student("Sakis","Mpoulas",new Date(),234);
        StudentCrudServices scs=new StudentCrudServices();
        scs.save(student);


        List<Student> studentList=scs.findAll();
        for(int i=0; i<studentList.size(); i++){
            System.out.println(studentList.get(i));
        }
        System.out.println("The Student you are looking for is:");
        System.out.println(scs.findById(1));
//
        scs.delete(2);

        scs.update(new Student(12,"alekos","alekopoulos",new Date(),1500));

    }
}
