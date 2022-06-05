import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerCrudServices implements CrudMethods<Trainer>{

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
            System.out.println("Course has succesfully Deleted!!!");

        }

    }
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
            System.out.println("Course has successfully updated");
        }
    }
    private void setNewValuesToCourse(PreparedStatement ps,Trainer trainer)throws SQLException{
        ps.setString(1,trainer.getFirstName());
        ps.setString(2,trainer.getLastName());
        ps.setString(3, trainer.getSubject());
        ps.setInt(4,trainer.getId());

    }


}
