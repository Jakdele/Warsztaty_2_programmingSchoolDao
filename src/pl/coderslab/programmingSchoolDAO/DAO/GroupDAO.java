package pl.coderslab.programmingSchoolDAO.DAO;

import pl.coderslab.programmingSchoolDAO.entity.Group;
import pl.coderslab.programmingSchoolDAO.entity.Group;
import pl.coderslab.programmingSchoolDAO.service.DBManager;
import pl.coderslab.programmingSchoolDAO.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupDAO {


    static public void saveToDb(Group group) throws Exception {
        if (group.getId() == null) {
            addGroup(group);
        } else {
            updateGroup(group);
        }
    }

    public static List<Group> findAll() throws Exception {
        String query = "Select * from user_group";
        List<Map<String, String>> data = DBManager.getData(query, null);

        List<Group> result = new ArrayList<>();
        for (Map<String, String> row : data) {
            result.add(createGroup(row));
        }
        return result;
    }

    private static Group createGroup(Map<String, String> row) {
        Group group = new Group();
        group.setId(Integer.parseInt(row.get("id")));
        group.setName(row.get("name"));

        return group;
    }

    private static void updateGroup(Group group) throws Exception {
        String query = "UPDATE user_group SET name=? WHERE id =?";
        List<String> params = new ArrayList<>();
        params.add(group.getName());
        params.add(String.valueOf(group.getId()));
        DBManager.executeQuery(query, params);
    }

    private static void addGroup(Group group) throws Exception {
        String query = "INSERT INTO user_group VALUES(null,?)";
        List<String> params = new ArrayList<>();
        params.add(group.getName());
        Integer id = DBManager.insertIntoDatabase(query, params);
        group.setId(id);
    }

    public static void delete(Integer id) {
        String query = "DELETE FROM user_group WHERE id =?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        try {
            DBManager.executeQuery(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Group loadGroupById(Integer id) throws Exception {
        String query = "SELECT * FROM user_group where id=?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        List<Map<String, String>> data = DBManager.getData(query, params);

        List<Group> result = new ArrayList<>();
        for (Map<String, String> row : data) {
            result.add(createGroup(row));
            return result.get(0);
        }
        return null;
    }

}
