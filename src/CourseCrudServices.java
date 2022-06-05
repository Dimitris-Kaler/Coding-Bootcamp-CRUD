import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseCrudServices implements CrudMethods<Course>{


@Override
    public List<Course> findAll() {
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

    private void ListOfCourses(PreparedStatement ps,List<Course> list)throws SQLException{
        ResultSet rs=ps.executeQuery();

            while(rs.next()){
                Course s=new Course(rs.getInt("id"),rs.getString("title"),rs.getString("stream"),rs.getString("type"),rs.getDate("start_date"),rs.getDate("end_date"));
                list.add(s);
            }


    }


    @Override
    public void save(Course course) {
        Connection connection=createConnection();
        saveCourse(course,connection);
    }


   private void saveCourse(Course course,Connection connection) {
       final String SQL="INSERT INTO Courses (title,stream,type,start_date,end_date) VALUES (?,?,?,?,?)";
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


@Override
    public Course findById(int id){
        Connection connection=createConnection();
        return findSpecificCourse(connection,id);
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

    @Override
    public void delete(int id){
        Connection connection=createConnection();
        deleteCourse(connection,id);

    }

    private void deleteCourse(Connection connection,int id){
        final String SQL="DELETE FROM COURSES WHERE ID=?";
        PreparedStatement ps=null;
        try{
            ps=connection.prepareStatement(SQL);
            chooseCourseToDelete(ps,id);
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            releaseResources(connection,ps);
        }



    }

    private void chooseCourseToDelete(PreparedStatement ps,int id)throws SQLException{
        ps.setInt(1,id);
        int result=ps.executeUpdate();
        if(result==1){
            System.out.println("Course has succesfully Deleted!!!");

        }

    }


    @Override
    public void update(Course course){
        Connection connection=createConnection();
        updateCourse(connection,course);
    }


    private void updateCourse(Connection connection,Course course){
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



