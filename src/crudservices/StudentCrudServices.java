package crudservices;

import entities.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentCrudServices implements CrudMethods<Student> {

/******************* INSERT STUDENT FROM SCANNER **************/
    @Override
    public void save1(Scanner sc) {
        Connection connection=createConnection();
        saveStudent1(connection,sc);

    }

    private void saveStudent1(Connection connection,Scanner sc){
        final String SQL="INSERT INTO Students (first_name,last_name,date_of_birth,tuition_fees)VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);
            insertStudent1(ps,sc);
        }
        catch(SQLException | ParseException pe){
            pe.printStackTrace();
        }finally{
            sc.close();
            releaseResources(connection,ps);
        }

    }

    private void insertStudent1(PreparedStatement ps,Scanner sc) throws SQLException,ParseException{
        mapStudentToSqlStatement1(ps,sc);
        ps.executeUpdate();
    }

   private void  mapStudentToSqlStatement1(PreparedStatement ps,Scanner sc)throws SQLException,ParseException{
       System.out.print("Type Student's FirstName: ");
       ps.setString(1,sc.nextLine());
       System.out.print("Type Student's LastName: ");
       ps.setString(2, sc.nextLine());
       System.out.print("Type Student's Date Of Birth: ");
       takeDate(ps,sc);
       System.out.print("Type Student's Tuition Fees: ");
       ps.setInt(4,sc.nextInt());


    }

    private void takeDate(PreparedStatement ps,Scanner sc) throws SQLException, ParseException {
        DateFormat formatter=new SimpleDateFormat("yyyy-mm-dd" );
        String dateString=sc.next();
        Date date = formatter.parse(dateString);
        ps.setDate( 3, new java.sql.Date(date.getTime()));
    }

    /********************************************************************/

    /****************FIND ALL STUDENTS *******************************/
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
            Student student=new Student(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getDate("date_of_birth"),rs.getInt("tuition_fees"));
            list.add(student);
        }

    }

    /****************************************************************************/



    /**********************INSERT STUDENTS****************************************/

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


    /*************** FIND SPECIFIC STUDENT ***********************************/


    private Student findStudent(PreparedStatement ps,int id,Student student)throws SQLException{
        ps.setInt(1,id);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
            int findId=rs.getInt("id");
            student = new Student(findId, rs.getString("first_name"), rs.getString("last_name"), rs.getDate("date_of_birth"),rs.getInt("tuition_fees"));


        }else{
            System.out.println("No entities.Student founded with this Id");
        }

        return student;


    }
    /*********************************************************************************/


    /**************** DELETE STUDENT ***********************************************/
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
            System.out.println("entities.Student has succesfully Deleted!!!");

        }

    }

    /*************************************************************************/

    /********************* UPDATE STUDENT ***********************************/
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
            System.out.println("entities.Student has successfully updated");
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


