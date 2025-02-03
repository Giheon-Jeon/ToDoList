package todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoList {
    private JFrame frame;
    private JTextField taskField;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    public TodoList() {
        initializeFrame();
        initializeTaskField();
        initializeTaskList();

        frame.setVisible(true);
    }

    // 프레임을 초기화하는 메서드
    private void initializeFrame() {
        frame = new JFrame("Todo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
    }

    // 작업 입력 필드 및 버튼을 초기화하는 메서드
    private void initializeTaskField() {
        taskField = new JTextField();
        taskField.setBorder(null);
        taskField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        
        JPanel totalPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton addButton = createAddButton();
        JButton removeButton = createRemoveButton();

        addButton.setBorder(null);
        addButton.setBackground(Color.LIGHT_GRAY);
        removeButton.setBorder(null);
        removeButton.setBackground(Color.LIGHT_GRAY);
        
        totalPanel.add(taskField, BorderLayout.NORTH);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        totalPanel.add(buttonPanel);
        
        frame.add(totalPanel, BorderLayout.NORTH);
    }

    // 작업 목록을 초기화하는 메서드
    private void initializeTaskList() {
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);
    }

    // 작업 추가 버튼을 생성하는 메서드
    private JButton createAddButton() {
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        return addButton;
    }

    // 작업 제거 버튼을 생성하는 메서드
    private JButton createRemoveButton() {
        JButton removeButton = new JButton("Remove Task");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });
        return removeButton;
    }

    // 작업을 추가하는 메서드
    private void addTask() {
        String task = taskField.getText();
        if (!task.isEmpty()) {
            listModel.addElement(task);
            taskField.setText("");
        }
    }

    // 작업을 제거하는 메서드
    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TodoList();
            }
        });
    }
}
