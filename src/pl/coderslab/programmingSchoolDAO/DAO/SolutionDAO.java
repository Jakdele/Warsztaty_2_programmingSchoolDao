package pl.coderslab.programmingSchoolDAO.DAO;

import pl.coderslab.programmingSchoolDAO.entity.Solution;
import pl.coderslab.programmingSchoolDAO.service.DBManager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolutionDAO {

    static public void saveToDb(Solution solution) throws Exception {
        if (solution.getId() == null) {
            addSolution(solution);
        } else {
            updateSolution(solution);
        }
    }

    public static List<Solution> findAll() throws Exception {
        String query = "Select * from solution";
        List<Map<String, String>> data = DBManager.getData(query, null);

        List<Solution> result = new ArrayList<>();
        for (Map<String, String> row : data) {
            result.add(createSolution(row));
        }
        return result;
    }

    private static Solution createSolution(Map<String, String> row) throws Exception {
        Solution solution = new Solution();
        solution.setId(Integer.parseInt(row.get("id")));
        solution.setDescription(row.get("description"));
        solution.setCreated(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(row.get("created")));
        solution.setUpdated(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(row.get("updated")));
        solution.setExercise_id(Integer.parseInt(row.get("exercise_id")));
        solution.setUsers_id(Integer.parseInt(row.get("users_id")));
        return solution;
    }

    private static void updateSolution(Solution solution) throws Exception {
        String query = "UPDATE solution SET description=?, users_id=? WHERE id =?";
        List<String> params = new ArrayList<>();
        params.add(solution.getDescription());
        params.add(String.valueOf(solution.getUsers_id()));
        params.add(String.valueOf(solution.getId()));
        DBManager.executeQuery(query, params);
    }

    private static void addSolution(Solution solution) throws Exception {
        String query = "INSERT INTO solution(exercise_id, solutions_id) VALUES ( ?, ?)";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(solution.getExercise_id()));
        params.add(String.valueOf(solution.getUsers_id()));
        Integer id = DBManager.insertIntoDatabase(query, params);
        solution.setId(id);
    }

    public static void delete(Integer id) {
        String query = "DELETE FROM solution WHERE id =?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        try {
            DBManager.executeQuery(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Solution loadSolutionById(int id) throws Exception {
        String query = "SELECT * FROM solution where id=?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        List<Map<String, String>> data = DBManager.getData(query, params);

        List<Solution> result = new ArrayList<>();
        for (Map<String, String> row : data) {
            result.add(createSolution(row));
            return result.get(0);
        }
        return null;
    }


    public static List<Solution> loadAllByUserID(int users_id) throws Exception {
        String query = "SELECT * FROM solution JOIN users ON solution.users_id=users.id WHERE users.id=? ORDER BY created DESC";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(users_id));
        List<Map<String, String>> data = DBManager.getData(query, params);

        List<Solution> result = new ArrayList<>();
        for (Map<String, String> row : data) {
            result.add(createSolution(row));
            return result;
        }
        return null;
    }

    public static List<Solution> loadAllNotDone(int userId) throws Exception {

            String query = "SELECT * FROM solution JOIN users ON solution.users_id=users.id WHERE users.id=? AND solution.description IS NULL ORDER BY created DESC";
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(userId));
            List<Map<String, String>> data = DBManager.getData(query, params);

            List<Solution> result = new ArrayList<>();
            for (Map<String, String> row : data) {
                result.add(createSolution(row));
                return result;
            }
            return null;
    }


}
