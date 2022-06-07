import crudservices.StudentCrudServices;
import entities.Student;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

public class StudentTest {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        Student student=new Student("Sakis","Mpoulas",new Date(),234);
        StudentCrudServices scs=new StudentCrudServices();
//        scs.save(student);
        scs.save1(sc);


        List<Student> studentList=scs.findAll();
        for(int i=0; i<studentList.size(); i++){
            System.out.println(studentList.get(i));
        }
        System.out.println("The entities.Student you are looking for is:");
        System.out.println(scs.findById(1));
//
        scs.delete(2);

        scs.update(new Student(12,"alekos","alekopoulos",new Date(),1500));

    }
}
