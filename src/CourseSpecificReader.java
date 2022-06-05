import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseSpecificReader {

    public Course findById(int id){
        Connection connection=createConnection();
        return findSpecificCourse(connection,id);
    }
    private Connection createConnection(){
        return new DBConnection().createConnection();
    }

    private Course findSpecificCourse(Connection connection,int id){
        final String SQL="SELECT * FROM Courses WHERE id=?";
        Course course=null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(SQL);
            Course foundedCourse= findCourse(ps,id,course);
            course=foundedCourse;

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            releaseResources(connection,ps);
        }


        return course;


    }


    private Course findCourse(PreparedStatement ps,int id,Course course)throws SQLException{
        ps.setInt(1,id);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
            int findId=rs.getInt("id");
            course = new Course(findId, rs.getString("title"), rs.getString("stream"), rs.getString("type"), rs.getDate("start_date"), rs.getDate("end_date"));
//            System.out.println("ID:" + course.getId() + " Title: " + course.getTitle() + " Stream: " + course.getStream() + "Type: " + course.getType() + " Start_Date: " + course.getStartDate() + "End_Date: " + course.getEndDate());

        }else{
            System.out.println("No course founded with this Id");
        }

        return course;

    }
    private void releaseResources(Connection connection, PreparedStatement ps) {
        try {
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
