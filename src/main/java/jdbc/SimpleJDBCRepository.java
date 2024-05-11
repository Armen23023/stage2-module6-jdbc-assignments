package jdbc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleJDBCRepository {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private Statement st = null;
    CustomDataSource dataSource;


    public SimpleJDBCRepository(CustomDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String createUserSQL = "INSERT INTO myfirstdb.public.myusers (firstName, lastName, age) VALUES (?, ?, ?)";
    private static final String updateUserSQL = "UPDATE myfirstdb.public.myusers SET firstName=?, lastName=?, age=? WHERE id=?";
    private static final String deleteUser = "DELETE FROM myfirstdb.public.myusers WHERE id = ? ";
    private static final String findUserByIdSQL = "SELECT * from myfirstdb.public.myusers WHERE id =?";
    private static final String findUserByNameSQL = "SELECT * from myfirstdb.public.myusers WHERE firstname =?";
    private static final String findAllUserSQL = "SELECT * from myfirstdb.public.myusers";



    public Long createUser() {
//        try {
//            connection = dataSource.getConnection();
//            ps = connection.prepareStatement(createUserSQL);
//            ps.setString(1,user.getFirstName());
//            ps.setString(2,user.getLastName());
//            ps.setInt(3,user.getAge());
//            ps.executeUpdate();
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                return rs.getLong(1);
//            }
//            throw new SQLException("Creating user failed, no ID obtained.");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            try {
//                connection.close();
//                ps.close();
//                st.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
return null;
    }

    public User findUserById(Long userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(findUserByIdSQL)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("age");

                return new User(id, firstName, lastName, age);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findUserByName(String userName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(findUserByNameSQL)) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("id");
                String userFirstName = rs.getString("firstName");
                String userLastName = rs.getString("lastName");
                int age = rs.getInt("age");
                return new User(id, userFirstName, userLastName, age);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<User> findAllUser() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(findAllUserSQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("age");
                users.add(new User(id, firstName, lastName, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User updateUser() {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement ps = connection.prepareStatement(updateUserSQL)) {
//            ps.setString(1, user.getFirstName());
//            ps.setString(2, user.getLastName());
//            ps.setInt(3, user.getAge());
//            ps.setLong(4, user.getId());
//            ps.executeUpdate();
//
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                long id = rs.getLong("id");
//                String firstName = rs.getString("firstName");
//                String lastName = rs.getString("lastName");
//                int age = rs.getInt("age");
//
//                return new User(id, firstName, lastName, age);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
        return  null;
    }

    private void deleteUser(Long userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteUser)) {
            ps.setLong(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
