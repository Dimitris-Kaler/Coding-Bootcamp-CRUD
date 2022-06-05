import java.util.Date;
import java.util.List;

public class CourseTest {

    public static void main(String[] args) {
        Course course=new Course("JAVABASICS","Stream","Part-Time",new Date(),new Date());
        CourseWriter cw=new CourseWriter();

        cw.save(course);
        CourseReader cr= new CourseReader();
        System.out.println(cr.findAll());

        List<Course> mpa=cr.findAll();
        for(int i=0;i<mpa.size();i++){
            System.out.println(mpa.get(i));
            System.out.println(mpa.get(i).getTitle());
        }

        /**DELETE COURSE */
        CourseDeleter cd=new CourseDeleter();

        cd.delete(10);


        /**READ SPECIFIC COURSE*/
        CourseSpecificReader csr=new CourseSpecificReader();
        System.out.println("THe course you aks is: "+csr.findById(1));

        /**UPDATE COURSE**/

        CourseUpdater cup=new CourseUpdater();
        cup.update(new Course(2,"Solidity","Stream","Full-Time",new Date(),new Date()));
    }
}
