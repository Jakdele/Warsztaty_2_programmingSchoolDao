package pl.coderslab.programmingSchoolDAO.entity;

import java.sql.Timestamp;
import java.util.Date;


public class Solution {
    private Integer id;
    private Date created;
    private Date updated;
    private String description;
    private Integer exercise_id;
    private Integer users_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Integer exercise_id) {
        this.exercise_id = exercise_id;
    }

    public Integer getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Integer users_id) {
        this.users_id = users_id;
    }

    public Solution() {
    }

    public Solution(Integer exercise_id, Integer users_id) {
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

    public Solution(String description, Integer exercise_id, Integer users_id) {
        this.description = description;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

    public Solution(Timestamp created, Timestamp updated, String description, Integer exercise_id, Integer users_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

    @Override
    public String toString() {
        if(description==null || updated ==null){
            return "Solution " +
                    "id: " + id +
                    " created: " + created +
                    " updated: " + "Hasn't been solved yet" +
                    " description: '" + "Hasn't been solved yet" +
                    " exercise_id: " + exercise_id +
                    " users_id: " + users_id;
        }else {
            return "Solution " +
                    "id: " + id +
                    " created: " + created +
                    " updated: " + updated +
                    " description: '"  +
                    " exercise_id: " + exercise_id +
                    " users_id: " + users_id;
        }
    }

}