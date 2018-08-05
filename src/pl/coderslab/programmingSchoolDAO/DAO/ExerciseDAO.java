package pl.coderslab.programmingSchoolDAO.DAO;

import pl.coderslab.programmingSchoolDAO.entity.Exercise;
import pl.coderslab.programmingSchoolDAO.service.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseDAO {
    static public void saveToDb(Exercise exercise) throws Exception {
        if (exercise.getId() == null) {
            addExercise(exercise);
        } else {
            updateExercise(exercise);
        }
    }

    public static List<Exercise> findAll() throws Exception {
        String query = "Select * from exercise";
        List<Map<String, String>> data = DBManager.getData(query, null);

        List<Exercise> result = new ArrayList<>();
        for (Map<String, String> row : data) {
            result.add(createExercise(row));
        }
        return result;
    }

    private static Exercise createExercise(Map<String, String> row) {
        Exercise exercise = new Exercise();
        exercise.setId(Integer.parseInt(row.get("id")));
        exercise.setTitle(row.get("title"));
        exercise.setDescription(row.get("description"));
        return exercise;
    }

    private static void updateExercise(Exercise exercise) throws Exception {
        String query = "UPDATE exercise SET title=?, description=? WHERE id =?";
        List<String> params = new ArrayList<>();
        params.add(exercise.getTitle());
        params.add(exercise.getDescription());
        params.add(String.valueOf(exercise.getId()));
        DBManager.executeQuery(query, params);
    }

    private static void addExercise(Exercise exercise) throws Exception {
        String query = "INSERT INTO exercise VALUES (null, ?, ?)";
        List<String> params = new ArrayList<>();
        params.add(exercise.getTitle());
        params.add(exercise.getDescription());
        Integer id = DBManager.insertIntoDatabase(query, params);
        exercise.setId(id);
    }

    public static void delete(Integer id) {
        String query = "DELETE FROM exercise WHERE id =?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        try {
            DBManager.executeQuery(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Exercise loadExerciseById(int id) throws Exception {
        String query = "SELECT * FROM exercise where id=?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        List<Map<String, String>> data = DBManager.getData(query, params);

        List<Exercise> result = new ArrayList<>();
        for (Map<String, String> row : data) {
            result.add(createExercise(row));
            return result.get(0);
        }
        return null;
    }

}