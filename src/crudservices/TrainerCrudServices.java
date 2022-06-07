package crudservices;

import entities.Trainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainerCrudServices implements CrudMethods<Trainer> {




    /*******************  INSERT TRAINER WITH SCANNER FROM KEYBOARD ******************/
    public void save1(Scanner sc){
        Connection connection=createConnection();
        saveTrainer1(connection,sc);




    }

        private void saveTrainer1(Connection connection,Scanner sc){
        final String SQL="INSERT INTO Trainers (first_name,last_name,subject) VALUES (?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);
            insertTrainer1(ps,sc);}
        catch(SQLException e){
            e.printStackTrace();
        }finally{
            sc.close();
            releaseResources(connection,ps);
        }

    }

        private void insertTrainer1(PreparedStatement ps,Scanner sc)throws SQLException{
        mapTrainerToSqlStatement1(ps,sc);
        ps.executeUpdate();

    }

    private void mapTrainerToSqlStatement1(PreparedStatement ps ,Scanner sc)throws SQLException{
        final String quote1="Type Trainer's firstName: ";
        final String quote2="Type Trainer's lastName: ";
        final String quote3="Type Trainer's subject: ";
        stringValidation(ps,sc,1,quote1);
        stringValidation(ps,sc,2,quote2);
        stringValidation(ps,sc,3,quote3);


    }

    private void stringValidation(PreparedStatement ps ,Scanner sc,int paramIndex,String quote) throws SQLException {

        System.out.print(quote);
        while(true) {
            if (sc.hasNext("[a-zA-Z]+$")) {

                ps.setString(paramIndex, sc.nextLine().trim());
                break;
            } else {
                System.out.println("Only letters please!!");
                System.out.print(quote);
                ps.setString(paramIndex, sc.nextLine().trim());
            }
        }

    }



    /****************************************/


    /*****FIND ALL TRAINERS*******/

    @Override
    public List<Trainer> findAll() {
        Connection connection = createConnection();
        return findAllTrainers(connection);
    }

    private Connection createConnection(){
        DBConnection db=new DBConnection();
        return db.createConnection();
    }

    private List<Trainer> findAllTrainers(Connection connection){
        final String SQL="SELECT * FROM Trainers";
        List<Trainer>list=new ArrayList<Trainer>();
        PreparedStatement ps=null;
        try{
            ps=connection.prepareStatement(SQL);
            ListOfTrainers(ps, list);


        }catch(Exception e){
            System.out.println(e.toString());
        }

      return list;
    }


    private void ListOfTrainers(PreparedStatement ps,List<Trainer> list)throws SQLException {
        ResultSet rs=ps.executeQuery();

        while(rs.next()){
            Trainer trainer=new Trainer(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("subject"));
            list.add(trainer);
        }

    }


    /******CLASSIC INSERT WITH CREATING NEW OBJECT ******/

    @Override
    public void save(Trainer trainer) {
        Connection connection=createConnection();
        saveTrainer(trainer,connection);
    }

    private void saveTrainer(Trainer trainer,Connection connection){
        final String SQL="INSERT INTO Trainers (first_name,last_name,subject) VALUES (?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);
            insertTrainer(trainer,ps);}
        catch(SQLException e){
            e.printStackTrace();
        }finally{
            releaseResources(connection,ps);
        }

    }

    private void insertTrainer(Trainer trainer ,PreparedStatement ps)throws SQLException{
        mapTrainerToSqlStatement(ps,trainer);
        ps.executeUpdate();

    }

    private void mapTrainerToSqlStatement(PreparedStatement ps ,Trainer trainer)throws SQLException{
        ps.setString(1, trainer.getFirstName());
        ps.setString(2, trainer.getLastName());
        ps.setString(3, trainer.getSubject());

    }

    private void releaseResources(Connection connection, PreparedStatement ps) {
        try {
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*****************************************************/



    /*********FIND SPECIFIC TRAINER*********************/
    @Override
    public Trainer findById(int id){
        Connection connection=createConnection();
        return findSpecificTrainer(connection,id);
    }

    private Trainer findSpecificTrainer(Connection connection,int id){
        final String SQL="SELECT * FROM Trainers WHERE id=?";
        Trainer trainer=null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(SQL);
            Trainer foundedTrainer= findTrainer(ps,id,trainer);
            trainer=foundedTrainer;

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            releaseResources(connection,ps);
        }


        return trainer;


    }


    private Trainer findTrainer(PreparedStatement ps,int id,Trainer trainer)throws SQLException{
        ps.setInt(1,id);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
            int findId=rs.getInt("id");
            trainer = new Trainer(findId, rs.getString("first_name"), rs.getString("last_name"), rs.getString("subject"));


        }else{
            System.out.println("No course founded with this Id");
        }

        return trainer;




    }

    /****************DELETE TRAINER ************/
    @Override
    public void delete(int id){
        Connection connection=createConnection();
        deleteTrainer(connection,id);

    }

    private void deleteTrainer(Connection connection,int id){
        final String SQL="DELETE FROM Trainers WHERE id=?";
        PreparedStatement ps=null;
        try{
            ps=connection.prepareStatement(SQL);
            chooseTrainerToDelete(ps,id);
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            releaseResources(connection,ps);
        }



    }

    private void chooseTrainerToDelete(PreparedStatement ps,int id)throws SQLException{
        ps.setInt(1,id);
        int result=ps.executeUpdate();
        if(result==1){
            System.out.println("entities.Course has succesfully Deleted!!!");

        }

    }

    /**************************************************/


    /************ UPDATE TRAINER ********************/
    @Override
    public void update(Trainer trainer){
        Connection connection=createConnection();
        updateCourse(connection,trainer);
    }


    private void updateCourse(Connection connection,Trainer trainer){
        final String SQL="UPDATE Trainers SET first_name=?,last_name=?,subject=? WHERE id=?";

        PreparedStatement ps=null;

        try{
            ps=connection.prepareStatement(SQL);
            updateSpecificTrainer(ps,trainer);


        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            releaseResources(connection,ps);
        }

    }

    private void updateSpecificTrainer(PreparedStatement ps,Trainer trainer)throws SQLException{
        setNewValuesToCourse(ps,trainer);
        int result=ps.executeUpdate();
        if(result==1){
            System.out.println("entities.Course has successfully updated");
        }
    }
    private void setNewValuesToCourse(PreparedStatement ps,Trainer trainer)throws SQLException{
        ps.setString(1,trainer.getFirstName());
        ps.setString(2,trainer.getLastName());
        ps.setString(3, trainer.getSubject());
        ps.setInt(4,trainer.getId());

    }


}
