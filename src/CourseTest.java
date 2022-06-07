import crudservices.CourseCrudServices;
import entities.Course;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CourseTest {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);


        Course course=new Course("cb4321","javase","Part-time",new Date(),new Date());
        CourseCrudServices ccs=new CourseCrudServices();
        ccs.save(course);
        System.out.println(course.getTitle());

        ccs.save1(scanner);

       List<Course> courseList=ccs.findAll();
       for(int i=0; i<courseList.size(); i++){
           System.out.println(courseList.get(i));
       }
        System.out.println("The course you are looking for is:");
        System.out.println(ccs.findById(1));

     ccs.delete(11);
     Course up=new Course(1,"CB128","SOLIDITY","Part-time",new Date(),new Date());
     ccs.update(up);
    }

}
