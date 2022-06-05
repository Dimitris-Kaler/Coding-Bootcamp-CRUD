import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseDeleter {
    public void delete(int id) {
        Connection connection = createConnection();
        deleteCourse(connection, id);

    }
    private Connection createConnection(){
        return new DBConnection().createConnection();
    }

    private void deleteCourse(Connection connection, int id) {
        final String SQL = "DELETE FROM COURSES WHERE ID=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);
            chooseCourseToDelete(ps, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(connection, ps);
        }


    }


    private void chooseCourseToDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        int result = ps.executeUpdate();
        if (result == 1) {
            System.out.println("Course has succesfully Deleted!!!");

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
}

