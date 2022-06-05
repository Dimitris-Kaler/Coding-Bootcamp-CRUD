import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL ="jdbc:mysql://localhost:3306/cube_coding_bootcamp1";

    private static final String USERNAME="root";

    private static final String PASS="***********";

    public Connection createConnection() {
        Connection connection=null;
        try{
            connection= DriverManager.getConnection(URL,USERNAME,PASS);
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return connection;

    }}
