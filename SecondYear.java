import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondYear {
    JPanel panel;
    JLabel[] subjectLabels;
    JPanel textFieldPanel, labelPanel;
    JTextField[] subjectTextFields;
    JLabel gradesLabel;
    JButton nextButton;
    private double firstSemAvgGrade;
    private double secondSemAvgGrade;
    private double totalAvgGrade;

    public SecondYear(JFrame frame, String username) {
        frame.setTitle("Welcome " + username + "!");

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        String[] subjects = {
            "First Semester Subjects",
            "SOCIO101 | The Contemporary World",
            "RIZAL101 | Life, Works & Writings of Dr. Jose Rizal",
            "CC-DIGILOG21 | Digital Logic Design",
            "IT-OOPROG21 | Object Oriented Programming",
            "IT-SAD21 | System Analysis & Design",
            "CC-ACCTG21 | Accounting for IT",
            "CC-TWRITE21 | Techinical Writing & Presentation Skills in IT",
            "PE 103 | Sports and Dance (PATHFit 3)",
        };

        subjectLabels = new JLabel[subjects.length];
        subjectTextFields = new JTextField[subjects.length];

        gradesLabel = new JLabel("Grades");

        for (int i = 0; i < subjects.length; i++) {

            // Label Constraints
            gbc.gridx = 0; // Column 0
            gbc.gridy = i; // Row increases
            gbc.anchor = GridBagConstraints.WEST; // Align left
            subjectLabels[i] = new JLabel(subjects[i]);
            subjectLabels[i].setFont(new Font(Font.SERIF, Font.BOLD, 12));
            panel.add(subjectLabels[i], gbc);

                panel.add(gradesLabel);

            if (i != 0) {
                // Text Field Constraints
            gbc.gridx = 1; // Column 1 (Next to Label)
            gbc.anchor = GridBagConstraints.CENTER; // Center align text field
            subjectTextFields[i] = new JTextField();
            subjectTextFields[i].setPreferredSize(new Dimension(50, 20)); // Small box
            subjectTextFields[i].setHorizontalAlignment(JTextField.CENTER);
            panel.add(subjectTextFields[i], gbc);
            }
        }
        nextButton = new JButton("Next");
            gbc.gridx = 0;
            gbc.gridy = subjects.length + 1; // Place it after all fields
            gbc.gridwidth = 2; // Span across two columns
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(nextButton, gbc);

            // Button Click Action
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double totalGrades = 0;
                    int count = 0;
    
                    for (int i = 1; i < subjects.length; i++) { // Skip first element (title)
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
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        String[] subjects = {
            "Second Semester Subjects",
                "STS101 | Science Technology & Society",
                "PHILO101 | Ethics",
                "CC-QUAMETH22 | Quantitative Methods w/ Prob. Stat.",
                "IT-PLATECH22 | Platform Technologies w/ Op. Sys.",
                "CC-APPSDEV22 | Applications Dev't & Emerging Tech.",
                "CC-DASTRUC22 | Data Structures & Algorithms",
                "CC-DATACOM22 | Data Communications",
                "PE104 | Sports/Outdoor Adventure (PATHFit 4)",
            };

        subjectLabels = new JLabel[subjects.length];
        subjectTextFields = new JTextField[subjects.length];

        gradesLabel = new JLabel("Grades");

        for (int i = 0; i < subjects.length; i++) {

            // Label Constraints
            gbc.gridx = 0; // Column 0
            gbc.gridy = i; // Row increases
            gbc.anchor = GridBagConstraints.WEST; // Align left
            subjectLabels[i] = new JLabel(subjects[i]);
            subjectLabels[i].setFont(new Font(Font.SERIF, Font.BOLD, 12));
            panel.add(subjectLabels[i], gbc);

                panel.add(gradesLabel);

            if (i != 0) {
                // Text Field Constraints
            gbc.gridx = 1; // Column 1 (Next to Label)
            gbc.anchor = GridBagConstraints.CENTER; // Center align text field
            subjectTextFields[i] = new JTextField();
            subjectTextFields[i].setPreferredSize(new Dimension(50, 20)); // Small box
            subjectTextFields[i].setHorizontalAlignment(JTextField.CENTER);
            panel.add(subjectTextFields[i], gbc);
            }
        }
        nextButton = new JButton("Continue");
            gbc.gridx = 0;
            gbc.gridy = subjects.length + 1; // Place it after all fields
            gbc.gridwidth = 2; // Span across two columns
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(nextButton, gbc);

            // Button Click Action
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double totalGrades = 0;
                    int count = 0;
    
                    for (int i = 1; i < subjects.length; i++) { // Skip first element (title)
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
