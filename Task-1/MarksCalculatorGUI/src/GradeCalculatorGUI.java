import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GradeCalculatorGUI {

    private JFrame frame;
    private JTextField numberOfStudentsField;
    private JTextArea gradesArea;
    private JLabel averageLabel;
    private JLabel highestLabel;
    private JLabel lowestLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new GradeCalculatorGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GradeCalculatorGUI() {
        frame = new JFrame("Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new GridLayout(8, 1));

        numberOfStudentsField = new JTextField();
        gradesArea = new JTextArea();
        averageLabel = new JLabel("Average Grade: ");
        highestLabel = new JLabel("Highest Grade: ");
        lowestLabel = new JLabel("Lowest Grade: ");

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrades();
            }
        });

        frame.add(new JLabel("Enter the number of students:"));
        frame.add(numberOfStudentsField);
        frame.add(new JLabel("Enter the grades (separated by commas):"));
        frame.add(gradesArea);
        frame.add(calculateButton);
        frame.add(averageLabel);
        frame.add(highestLabel);
        frame.add(lowestLabel);

        frame.setVisible(true);
    }

    private void calculateGrades() {
        try {
            int numberOfStudents = Integer.parseInt(numberOfStudentsField.getText());
            String[] gradesStr = gradesArea.getText().split(",");
            ArrayList<Integer> grades = new ArrayList<>();

            for (String gradeStr : gradesStr) {
                grades.add(Integer.parseInt(gradeStr.trim()));
            }

            double average = calculateAverage(grades);
            int highest = findHighest(grades);
            int lowest = findLowest(grades);

            averageLabel.setText("Average Grade: " + average);
            highestLabel.setText("Highest Grade: " + highest);
            lowestLabel.setText("Lowest Grade: " + lowest);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    private int findHighest(ArrayList<Integer> grades) {
        return Collections.max(grades);
    }

    private int findLowest(ArrayList<Integer> grades) {
        return Collections.min(grades);
    }
}
