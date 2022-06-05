import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseWriter {

    private static final String SQL="INSERT INTO Courses (title,stream,type,start_date,end_date) VALUES (?,?,?,?,?)";


    public void save(Course course){

        Connection connection=createConnection();
        saveCourse(course,connection);


    }
    private Connection createConnection(){
        return new DBConnection().createConnection();
    }

    private void saveCourse(Course course,Connection connection) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);
            insertCourse(course,ps);}
        catch(SQLException e){
            e.printStackTrace();
        }finally{
            releaseResources(connection,ps);
        }

    }

    private void insertCourse(Course course ,PreparedStatement ps)throws SQLException{
        mapCourseToSqlStatement(ps,course);
        ps.executeUpdate();

    }
    private void mapCourseToSqlStatement(PreparedStatement ps, Course course) throws SQLException {
        ps.setString(1, course.getTitle());
        ps.setString(2, course.getStream());
        ps.setString(3, course.getType());
        ps.setDate(4, new java.sql.Date(course.getStartDate().getTime()));
        ps.setDate(5, new java.sql.Date(course.getEndDate().getTime()));
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
