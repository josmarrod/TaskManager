package com.taskmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TaskUI {
    private JFrame frame;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField dueDateField;
    private JComboBox<String> priorityBox;
    private JButton addButton;
    private Database db;

    public TaskUI() {
        db = new Database();
        db.createTaskTable();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Task Manager");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(6, 2));

        JLabel titleLabel = new JLabel("Title:");
        panel.add(titleLabel);

        titleField = new JTextField();
        panel.add(titleField);
        
        JLabel descriptionLabel = new JLabel("Description:");
        panel.add(descriptionLabel);

        descriptionArea = new JTextArea();
        panel.add(descriptionArea);

        JLabel dueDateLabel = new JLabel("Due Date (YYYY-MM-DD):");
        panel.add(dueDateLabel);

        dueDateField = new JTextField();
        panel.add(dueDateField);

        JLabel priorityLabel = new JLabel("Priority:");
        panel.add(priorityLabel);

        String[] priorities = {"High", "Medium", "Low"};
        priorityBox = new JComboBox<>(priorities);
        panel.add(priorityBox);

        addButton = new JButton("Add Task");
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionArea.getText();
                String dueDate = dueDateField.getText();
                String priority = (String) priorityBox.getSelectedItem();

                Task task = new Task(0, title, description, Date.valueOf(dueDate), false, priority);
                db.insertTask(task);
                JOptionPane.showMessageDialog(frame, "Task Added Successfully!");
            }
        });

        frame.setVisible(true);
    }
}
