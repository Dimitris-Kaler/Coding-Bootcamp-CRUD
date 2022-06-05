import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseUpdater {

    public void update(Course course){
        Connection connection=createConnection();
        updateCourse(connection,course);
    }


    private Connection createConnection(){
        return new DBConnection().createConnection();
    }

   private void updateCourse(Connection connection, Course course){
        final String SQL="UPDATE COURSES SET title=?,stream=?,type=?,start_date=?,end_date=? WHERE id=?";

        PreparedStatement ps=null;

        try{
            ps=connection.prepareStatement(SQL);
            updateSpecificCourse(ps,course);


        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            releaseResources(connection,ps);
        }

    }

    private void releaseResources(Connection connection, PreparedStatement ps) {
        try {
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateSpecificCourse(PreparedStatement ps,Course course)throws SQLException{
        setNewValuesToCourse(ps,course);
        int result=ps.executeUpdate();
        if(result==1){
            System.out.println("Course has successfully updated");
        }
    }
    private void setNewValuesToCourse(PreparedStatement ps,Course course)throws SQLException{
        ps.setString(1,course.getTitle());
        ps.setString(2,course.getStream());
        ps.setString(3,course.getType());
        ps.setDate(4,new java.sql.Date(course.getStartDate().getTime()));
        ps.setDate(5,new java.sql.Date(course.getEndDate().getTime()));
        ps.setInt(6,course.getId());

    }


}
