import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseReader {

    public List <Course> findAll() {
        Connection connection = createConnection();
        return findAllCourses(connection);
    }


    private Connection createConnection(){
        return new DBConnection().createConnection();
    }
    private List<Course> findAllCourses(Connection connection){
        final String SQL="SELECT * FROM Courses";
        List<Course> list = new ArrayList<Course>();
        PreparedStatement ps=null;
        try {
            ps = connection.prepareStatement(SQL);
            ListOfCourses(ps, list);
        }catch(Exception e) {
            System.out.println(e.toString());
        }finally{
            releaseResources(connection,ps);
        }
        return list;

    }

    private void ListOfCourses(PreparedStatement ps,List<Course> list)throws SQLException {
        ResultSet rs=ps.executeQuery();

        while(rs.next()){
            Course s=new Course(rs.getInt("id"),rs.getString("title"),rs.getString("stream"),rs.getString("type"),rs.getDate("start_date"),rs.getDate("end_date"));
            list.add(s);
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


//    private static final String SQL="SELECT * FROM Courses";
//
//    public List<Course> FindAll(){
//        List<Course> list=new ArrayList<Course>();
//        DBConnection db = new DBConnection();
//        Connection connection = db.createConnection();
//        try{
//            Statement ps=connection.createStatement();
//            ResultSet rs=ps.executeQuery(SQL);
//            while(rs.next()){
//
////                System.out.println("ID: " + rs.getInt("id") +"Title: "+ rs.getString("title")+"Stream: "+rs.getString("stream")+"Type: "+ rs.getString("type")+ rs.getDate("start_date")+ rs.getDate("end_date"));
//
//                Course s = new Course(rs.getInt("ID"), rs.getString("title"), rs.getString("stream"), rs.getString("type"), rs.getDate("start_date"), rs.getDate("end_date"));
////
//                    list.add(s);
////                    System.out.println(rs.getString(2));
////                System.out.println("ID:" + s.getId() + " Title: " + s.getTitle() + " Stream: " + s.getStream() + "Type: " + s.getType() + " Start_Date: " + s.getStartDate() + "End_Date: " + s.getEndDate());
//
////                System.out.println("Connection closed");
//            }
//            connection.close();
//        }catch(Exception e){
//            System.out.println(e.toString());
//
//        }
//        return list;
//    }
}
