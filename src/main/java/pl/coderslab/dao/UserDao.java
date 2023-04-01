package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao extends Queries {

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = getConnection(DATABASE)) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int id) {
        try (Connection connection = getConnection(DATABASE);
             PreparedStatement statement = connection.prepareStatement(READ_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return createUser(resultSet);
                    } else {
                        throw new UserNotFoundException(id);
                    }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User readEmail(String email) {
        try (Connection connection = getConnection(DATABASE);
             PreparedStatement statement = connection.prepareStatement(READ_EMAIL_QUERY)
        ) {
            statement.setString(1, email);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createUser(resultSet);
                } else {
                    throw new UserNotFoundException(email);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User readUsername(String username) {
        try (Connection connection = getConnection(DATABASE);
             PreparedStatement statement = connection.prepareStatement(READ_USERNAME_QUERY)
        ) {
            statement.setString(1, username);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createUser(resultSet);
                } else {
                    throw new UserNotFoundException(username);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static User createUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new User(id, email, username, password);
    }

    public void delete(int id) {
        try (PreparedStatement statement = getConnection(DATABASE)
                .prepareStatement(REMOVE_USER_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Successfully deleted entry");
    }

    public void update(User user) {
        try (Connection connection = getConnection(DATABASE);
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY)
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAll() {
        try (PreparedStatement statement = getConnection(DATABASE).prepareStatement(READ_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ArrayList<User>  users= new ArrayList<>(); //
                User user = createUser(resultSet);
                System.out.println(addToArray(user, users));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> addToArray(User u, ArrayList<User> users) {
        users.add(u);
        return users;
    }

}