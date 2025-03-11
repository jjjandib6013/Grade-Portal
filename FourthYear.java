import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FourthYear {
    JPanel panel;
    JLabel[] subjectLabels;
    JPanel textFieldPanel, labelPanel;
    JTextField[] subjectTextFields;
    JLabel gradesLabel;
    JButton nextButton;
    private double firstSemAvgGrade;
    private double secondSemAvgGrade;
    private double totalAvgGrade;

    public FourthYear(JFrame frame, String username) {
        frame.setTitle("Welcome " + username + "!");

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] subjects = {
            "First Semester Subjects",
            "SUMMER: IT-CPSTONE30 | Capstone Project 1",
            "SUMMER: CC-PROFIS10 | Professional Issues in Computing",
            "LIT11 | Literatures of the World",
            "IT-CPSTONE40 | Capstone Project 2",
            "IT-EL_____ | IT Elective 3",
            "IT-FRE_____ | Free Elective 4",
        };

        subjectLabels = new JLabel[subjects.length];
        subjectTextFields = new JTextField[subjects.length];

        gradesLabel = new JLabel("Grades");

        for (int i = 0; i < subjects.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            subjectLabels[i] = new JLabel(subjects[i]);
            subjectLabels[i].setFont(new Font(Font.SERIF, Font.BOLD, 12));
            panel.add(subjectLabels[i], gbc);
            panel.add(gradesLabel);

            if (i != 0) {
                gbc.gridx = 1;
                gbc.anchor = GridBagConstraints.CENTER;
                subjectTextFields[i] = new JTextField();
                subjectTextFields[i].setPreferredSize(new Dimension(50, 20));
                subjectTextFields[i].setHorizontalAlignment(JTextField.CENTER);
                panel.add(subjectTextFields[i], gbc);
            }
        }
        nextButton = new JButton("Next");
        gbc.gridx = 0;
        gbc.gridy = subjects.length + 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(nextButton, gbc);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalGrades = 0;
                int count = 0;

                for (int i = 1; i < subjects.length; i++) {
                    try {
                        double grade = Double.parseDouble(subjectTextFields[i].getText().trim());
                        totalGrades += grade;
                        count++;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter valid numeric grades.");
                        return;
                    }
                }

                if (count > 0) {
                    firstSemAvgGrade = totalGrades / count;
                    JOptionPane.showMessageDialog(frame, "First Semester Average Grade: " + String.format("%.1f", firstSemAvgGrade));
                }

                frame.getContentPane().removeAll();
                frame.add(secondSem(frame, username));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public JPanel secondSem(JFrame frame, String username) {
        frame.setTitle("Welcome " + username + "!");

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] subjects = {
            "Second Semester Subjects",
            "CC-PRACT40 | Practicum",
            "IT-EL_____ | IT Elective 4",
        };

        subjectLabels = new JLabel[subjects.length];
        subjectTextFields = new JTextField[subjects.length];

        gradesLabel = new JLabel("Grades");

        for (int i = 0; i < subjects.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            subjectLabels[i] = new JLabel(subjects[i]);
            subjectLabels[i].setFont(new Font(Font.SERIF, Font.BOLD, 12));
            panel.add(subjectLabels[i], gbc);
            panel.add(gradesLabel);

            if (i != 0) {
                gbc.gridx = 1;
                gbc.anchor = GridBagConstraints.CENTER;
                subjectTextFields[i] = new JTextField();
                subjectTextFields[i].setPreferredSize(new Dimension(50, 20));
                subjectTextFields[i].setHorizontalAlignment(JTextField.CENTER);
                panel.add(subjectTextFields[i], gbc);
            }
        }
        nextButton = new JButton("Continue");
        gbc.gridx = 0;
        gbc.gridy = subjects.length + 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(nextButton, gbc);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalGrades = 0;
                int count = 0;

                for (int i = 1; i < subjects.length; i++) {
                    try {
                        double grade = Double.parseDouble(subjectTextFields[i].getText().trim());
                        totalGrades += grade;
                        count++;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter valid numeric grades.");
                        return;
                    }
                }

                if (count > 0) {
                    secondSemAvgGrade = totalGrades / count;
                    totalAvgGrade = (firstSemAvgGrade + secondSemAvgGrade) / 2;
                    JOptionPane.showMessageDialog(frame, "Second Semester Average Grade: " + String.format("%.1f", secondSemAvgGrade));
                }

                frame.getContentPane().removeAll();
                Suggest suggest = new Suggest(frame, firstSemAvgGrade, secondSemAvgGrade, totalAvgGrade);
                frame.add(suggest.getPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        return panel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public double getFirstSemAvgGrade() {
        return firstSemAvgGrade;
    }

    public double getSecondSemAvgGrade() {
        return secondSemAvgGrade;
    }

    public double getTotalAvgGrade() {
        return totalAvgGrade;
    }
}