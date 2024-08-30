package com.taskmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connect() {
        String url = "jdbc:sqlite:tasks.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createTaskTable() {
        String sql = "CREATE TABLE IF NOT EXISTS tasks (\n"
                + " id integer PRIMARY KEY,\n"
                + " title text NOT NULL,\n"
                + " description text,\n"
                + " due_date text,\n"
                + " is_complete integer,\n"
                + " priority text\n"
                + ");";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTask(Task task) {
        String sql = "INSERT INTO tasks(title, description, due_date, is_complete, priority) VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getDueDate().toString());
            pstmt.setBoolean(4, task.isComplete());
            pstmt.setString(5, task.getPriority());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM tasks";
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        Date.valueOf(rs.getString("due_date")),
                        rs.getInt("is_complete") == 1,
                        rs.getString("priority")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    // Additional methods for updateTask, deleteTask can be implemented here
}
