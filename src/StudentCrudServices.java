import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCrudServices implements CrudMethods<Student>{

    @Override
    public List<Student> findAll() {
        Connection connection = createConnection();
        return findAllStudents(connection);
    }

    private Connection createConnection(){
        DBConnection db=new DBConnection();
        return db.createConnection();
    }

    private List<Student> findAllStudents(Connection connection){
        final String SQL="SELECT * FROM Students";
        List<Student>list=new ArrayList<Student>();
        PreparedStatement ps=null;
        try{
            ps=connection.prepareStatement(SQL);
            ListOfStudents(ps, list);


        }catch(Exception e){
            System.out.println(e.toString());
        }

        return list;
    }


    private void ListOfStudents(PreparedStatement ps,List<Student> list)throws SQLException {
        ResultSet rs=ps.executeQuery();

        while(rs.next()){
            Student student=new Student(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getDate("date_of_birth"),rs.getInt("tuition_feees"));
            list.add(student);
        }

    }

    @Override
    public void save(Student student) {
        Connection connection=createConnection();
        saveStudent(student,connection);
    }

    private void saveStudent(Student student,Connection connection){
        final String SQL="INSERT INTO Students (first_name,last_name,date_of_birth,tuition_fees)VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);
            insertStudent(student,ps);}
        catch(SQLException e){
            e.printStackTrace();
        }finally{
            releaseResources(connection,ps);
        }

    }

    private void insertStudent(Student student ,PreparedStatement ps)throws SQLException{
        mapStudentToSqlStatement(ps,student);
        ps.executeUpdate();

    }

    private void mapStudentToSqlStatement(PreparedStatement ps ,Student student)throws SQLException{
        ps.setString(1, student.getFirstName());
        ps.setString(2, student.getLastName());
        ps.setDate(3, new java.sql.Date(student.getDateOfBirth().getTime()));
        ps.setInt(4, student.getTuitionFees());

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
    public Student findById(int id){
        Connection connection=createConnection();
        return findSpecificStudent(connection,id);
    }

    private Student findSpecificStudent(Connection connection,int id){
        final String SQL="SELECT * FROM Students WHERE id=?";
        Student student=null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(SQL);
            Student foundedStudent= findStudent(ps,id,student);
            student=foundedStudent;

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            releaseResources(connection,ps);
        }


        return student;


    }


    private Student findStudent(PreparedStatement ps,int id,Student student)throws SQLException{
        ps.setInt(1,id);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
            int findId=rs.getInt("id");
            student = new Student(findId, rs.getString("first_name"), rs.getString("last_name"), rs.getDate("date_of_birth"),rs.getInt("tuition_fees"));


        }else{
            System.out.println("No Student founded with this Id");
        }

        return student;


    }
    @Override
    public void delete(int id){
        Connection connection=createConnection();
        deleteStudent(connection,id);

    }

    private void deleteStudent(Connection connection,int id){
        final String SQL="DELETE FROM Students WHERE id=?";
        PreparedStatement ps=null;
        try{
            ps=connection.prepareStatement(SQL);
            chooseStudentToDelete(ps,id);
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            releaseResources(connection,ps);
        }



    }

    private void chooseStudentToDelete(PreparedStatement ps,int id)throws SQLException{
        ps.setInt(1,id);
        int result=ps.executeUpdate();
        if(result==1){
            System.out.println("Student has succesfully Deleted!!!");

        }

    }
    @Override
    public void update(Student student){
        Connection connection=createConnection();
        updateStudent(connection,student);
    }


    private void updateStudent(Connection connection,Student student){
        final String SQL="UPDATE Students SET first_name=?,last_name=?,date_of_birth=?,tuition_fees=? WHERE id=?";

        PreparedStatement ps=null;

        try{
            ps=connection.prepareStatement(SQL);
            updateSpecificStudent(ps,student);


        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            releaseResources(connection,ps);
        }

    }

    private void updateSpecificStudent(PreparedStatement ps,Student student)throws SQLException{
        setNewValuesToStudent(ps,student);
        int result=ps.executeUpdate();
        if(result==1){
            System.out.println("Student has successfully updated");
        }
    }
    private void setNewValuesToStudent(PreparedStatement ps,Student student)throws SQLException{
        ps.setString(1,student.getFirstName());
        ps.setString(2,student.getLastName());
        ps.setDate(3, new java.sql.Date(student.getDateOfBirth().getTime()));
        ps.setInt(4,student.getTuitionFees());
        ps.setInt(5,student.getId());

    }


}


