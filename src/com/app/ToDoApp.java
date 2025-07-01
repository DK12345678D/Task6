package com.app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoApp {

    // Swing components
    private JFrame frame;
    private JTextField taskInput;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JButton addButton;
    private JButton deleteButton;

    public ToDoApp() {
        // Initialize the GUI
        frame = new JFrame("📝 To-Do List App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // Center the window

        // Panel for input and buttons
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        taskInput = new JTextField(20);
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");

        topPanel.add(taskInput);
        topPanel.add(addButton);
        topPanel.add(deleteButton);

        // Task list setup
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Add components to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Event handling
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        taskInput.addActionListener(e -> addTask()); // Enter key also adds

        // Display GUI
        frame.setVisible(true);
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskInput.setText(""); // Clear input field
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a task.");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a task to delete.");
        }
    }

    public static void main(String[] args) {
        // Ensure GUI is created on Event Dispatch Thread
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}
