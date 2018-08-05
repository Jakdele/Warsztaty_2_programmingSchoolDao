package pl.coderslab.programmingSchoolDAO.DAO;

import pl.coderslab.programmingSchoolDAO.entity.User;
import pl.coderslab.programmingSchoolDAO.service.DBManager;
import pl.coderslab.programmingSchoolDAO.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO {

    static public void saveToDb(User user) throws Exception {
        if (user.getId() == null) {
            addUser(user);
        } else {
            updateUser(user);
        }
    }

    public static List<User> findAll() throws Exception {
        String query = "Select * from users";
        List<Map<String, String>> data = DBManager.getData(query, null);

        List<User> result = new ArrayList<>();
        for (Map<String, String> row : data) {
            result.add(createUser(row));
        }
        return result;
    }

    private static User createUser(Map<String, String> row) {
        User user = new User();
        user.setId(Integer.parseInt(row.get("id")));
        user.setUsername(row.get("username"));
        user.setEmail(row.get("email"));
        user.setPassword(row.get("password"));
        user.setUser_group_id(Integer.parseInt(row.get("user_group_id")));
        return user;
    }

    private static void updateUser(User user) throws Exception {
        String query = "UPDATE users SET username=?, email=?, password=?, user_group_id=? WHERE id =?";
        List<String> params = new ArrayList<>();
        params.add(user.getUsername());
        params.add(user.getEmail());
        params.add(UserService.hashPassword(user).getPassword());
        params.add(String.valueOf(user.getUser_group_id()));
        params.add(String.valueOf(user.getId()));
        DBManager.executeQuery(query, params);
    }

    private static void addUser(User user) throws Exception {
        String query = "INSERT INTO users VALUES(null,?, ?, ?, ?)";
        List<String> params = new ArrayList<>();
        params.add(user.getUsername());
        params.add(user.getEmail());
        params.add(UserService.hashPassword(user).getPassword());
        params.add(String.valueOf(user.getUser_group_id()));
        Integer id = DBManager.insertIntoDatabase(query, params);
        user.setId(id);
    }

    public static void delete(Integer id) {
        String query = "DELETE FROM users WHERE id =?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        try {
            DBManager.executeQuery(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User loadUserById(int id) throws Exception {
        String query = "SELECT * FROM users where id=?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        List<Map<String, String>> data = DBManager.getData(query, params);

        List<User> result = new ArrayList<>();
        for (Map<String, String> row : data) {
            result.add(createUser(row));
            return result.get(0);
        }
        return null;
    }

    public static List<User> loadAllByGroupId(int userGroupId) throws Exception {
        String query = "SELECT * FROM users JOIN user_group ON users.user_group_id=user_group.id WHERE user_group.id=?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(userGroupId));
        List<Map<String, String>> data = DBManager.getData(query, params);

        List<User> result = new ArrayList<>();
        for (Map<String, String> row : data) {
            result.add(createUser(row));
            return result;
        }
        return null;
    }
}

