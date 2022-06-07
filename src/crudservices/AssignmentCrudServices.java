package crudservices;

import entities.Assignment;

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

public class AssignmentCrudServices implements CrudMethods<Assignment> {

/******* INSERT ASSIGNMENT WITH SCANNER CALL FROM KEYBOARD ********/
    @Override
    public void save1(Scanner sc) {
        Connection connection=createConnection();
        saveAssignment1(connection,sc);
    }

    private void saveAssignment1(Connection connection,Scanner sc){
        final String SQL="INSERT INTO Assignments (title,description,max_oral_mark,max_total_mark,sub_date_time) VALUES (?,?,?,?,?)";
        PreparedStatement ps=null;
        try{
            ps=connection.prepareStatement(SQL);
            insertAssignment1(ps,sc);
        }catch(SQLException | ParseException e){
            e.printStackTrace();
        }
        finally{
            sc.close();
            releaseResources(connection,ps);
        }

    }

    private void insertAssignment1(PreparedStatement ps,Scanner sc) throws SQLException, ParseException {
        mapAssignmentToSqlStatement1(ps,sc);
        ps.executeUpdate();
    }

    private void mapAssignmentToSqlStatement1(PreparedStatement ps,Scanner sc) throws SQLException, ParseException {
        System.out.print("Type Assignment's Title: ");
        ps.setString(1,sc.nextLine());
        System.out.print("Type Assignment's Description: ");
        ps.setString(2, sc.nextLine());
        System.out.print("Type Assignment's Max Oral Mark: ");
        ps.setInt(3, sc.nextInt());
        System.out.print("Type Assignment's Max Total Mark: ");
        ps.setInt(4, sc.nextInt());
        System.out.print("Type Assignment's SubDateTime: ");
        takeDate(ps,sc);

    }
    private void takeDate(PreparedStatement ps,Scanner sc) throws SQLException, ParseException {
        DateFormat formatter=new SimpleDateFormat("yyyy-mm-dd" );
        String dateString=sc.next();
        Date date = formatter.parse(dateString);
        ps.setDate( 5, new java.sql.Date(date.getTime()));
    }

    @Override
        public List<Assignment> findAll() {
            Connection connection = createConnection();
            return findAllAssignments(connection);
        }


        private Connection createConnection(){
            return new DBConnection().createConnection();
        }
        private List<Assignment> findAllAssignments(Connection connection){
            final String SQL="SELECT * FROM Assignments";
            List<Assignment> list = new ArrayList<Assignment>();
            PreparedStatement ps=null;
            try {
                ps = connection.prepareStatement(SQL);
                ListOfAssignments(ps, list);
            }catch(Exception e) {
                System.out.println(e.toString());
            }finally{
                releaseResources(connection,ps);
            }
            return list;

        }

        private void ListOfAssignments(PreparedStatement ps,List<Assignment> list)throws SQLException {
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                Assignment assignment=new Assignment(rs.getInt("id"),rs.getString("title"),rs.getString("description"),rs.getInt("max_oral_mark"),rs.getInt("max_total_mark"),rs.getDate("sub_date_time"));
                list.add(assignment);
            }


        }


        @Override
        public void save(Assignment assignment) {
            Connection connection=createConnection();
            saveAssignment(assignment,connection);
        }


        private void saveAssignment(Assignment assignment,Connection connection) {
            final String SQL="INSERT INTO Assignments (title,description,max_oral_mark,max_total_mark,sub_date_time) VALUES (?,?,?,?,?)";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(SQL);
                insertAssignment(assignment,ps);}
            catch(SQLException e){
                e.printStackTrace();
            }finally{
                releaseResources(connection,ps);
            }

        }

        private void insertAssignment(Assignment assignment ,PreparedStatement ps)throws SQLException{
            mapAssignmentToSqlStatement(ps,assignment);
            ps.executeUpdate();

        }
        private void mapAssignmentToSqlStatement(PreparedStatement ps, Assignment assignment) throws SQLException {
            ps.setString(1, assignment.getTitle());
            ps.setString(2, assignment.getDescription());
            ps.setInt(3, assignment.getMaxOralMark());
            ps.setInt(4, assignment.getMaxTotalMark());
            ps.setDate(5, new java.sql.Date(assignment.getSubDateTime().getTime()));
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
        public Assignment findById(int id){
            Connection connection=createConnection();
            return findSpecificAssignment(connection,id);
        }

        private Assignment findSpecificAssignment(Connection connection,int id){
            final String SQL="SELECT * FROM Assignments WHERE id=?";
            Assignment assignment=null;
            PreparedStatement ps = null;
            try{
                ps = connection.prepareStatement(SQL);
                Assignment foundedAssignment= findAssignment(ps,id,assignment);
                assignment=foundedAssignment;

            }
            catch(SQLException e){
                e.printStackTrace();
            }
            finally{
                releaseResources(connection,ps);
            }


            return assignment;


        }


        private Assignment findAssignment(PreparedStatement ps,int id,Assignment assignment)throws SQLException{
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                int findId=rs.getInt("id");
                assignment = new Assignment(findId, rs.getString("title"), rs.getString("description"), rs.getInt("max_oral_mark"), rs.getInt("max_total_mark"), rs.getDate("sub_date_time"));
//

            }else{
                System.out.println("No entities.Assignment founded with this Id");
            }

            return assignment;




        }

        @Override
        public void delete(int id){
            Connection connection=createConnection();
            deleteAssignment(connection,id);

        }

        private void deleteAssignment(Connection connection,int id){
            final String SQL="DELETE FROM Assignments WHERE ID=?";
            PreparedStatement ps=null;
            try{
                ps=connection.prepareStatement(SQL);
                chooseAssignmentToDelete(ps,id);
            }catch(SQLException e){
                e.printStackTrace();
            }
            finally{
                releaseResources(connection,ps);
            }



        }

        private void chooseAssignmentToDelete(PreparedStatement ps,int id)throws SQLException{
            ps.setInt(1,id);
            int result=ps.executeUpdate();
            if(result==1){
                System.out.println("entities.Assignment has succesfully Deleted!!!");

            }

        }


        @Override
        public void update(Assignment assignment){
            Connection connection=createConnection();
            updateAssignment(connection,assignment);
        }


        private void updateAssignment(Connection connection,Assignment assignment){
            final String SQL="UPDATE ASSIGNMENTS SET title=?,description=?,max_oral_mark=?,max_total_mark=?,sub_date_time=? WHERE id=?";

            PreparedStatement ps=null;

            try{
                ps=connection.prepareStatement(SQL);
                updateSpecificAssignment(ps,assignment);


            }catch(SQLException e){
                e.printStackTrace();
            }
            finally{
                releaseResources(connection,ps);
            }

        }

        private void updateSpecificAssignment(PreparedStatement ps,Assignment assignment)throws SQLException{
            setNewValuesToAssignment(ps,assignment);
            int result=ps.executeUpdate();
            if(result==1){
                System.out.println("entities.Assignment has successfully updated");
            }
        }
        private void setNewValuesToAssignment(PreparedStatement ps,Assignment assignment)throws SQLException{
            ps.setString(1,assignment.getTitle());
            ps.setString(2,assignment.getDescription());
            ps.setInt(3,assignment.getMaxOralMark());
            ps.setInt(4,assignment.getMaxTotalMark());
            ps.setDate(5,new java.sql.Date(assignment.getSubDateTime().getTime()));
            ps.setInt(6,assignment.getId());

        }

    }





