import javax.swing.*; // Importing the Swing library for GUI components
import java.awt.*; // Importing the AWT library for layouts and graphics
import java.awt.event.ActionEvent; // Importing ActionEvent for button actions
import java.awt.event.ActionListener; // Importing ActionListener to handle button clicks
import java.io.*; // Importing the I/O library (though not used in this class)

// Defining a class named FirstYear
public class FirstYear {
    JPanel panel; // Panel to hold all UI components
    JLabel[] subjectLabels; // Array to store subject labels
    JPanel textFieldPanel, labelPanel; // Panels for organizing labels and text fields
    JTextField[] subjectTextFields; // Array to store text fields for entering grades
    JLabel gradesLabel; // Label for "Grades" heading
    JButton nextButton; // Button to proceed to the next step
    private double firstSemAvgGrade; // Variable to store first semester average grade
    private double secondSemAvgGrade; // Variable to store second semester average grade
    private double totalAvgGrade; // Variable to store total average grade

    // Constructor for FirstYear class, accepting a JFrame and username
    public FirstYear(JFrame frame, String username) {
        frame.setTitle("Welcome " + username + "!"); // Setting the title of the frame with the username

        panel = new JPanel(new GridBagLayout()); // Creating a panel with GridBagLayout
        panel.setBackground(Color.decode("#2494cd")); // Setting the background color
        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for positioning elements
        gbc.insets = new Insets(5, 5, 5, 5); // Adding spacing between components

        // Array containing the names of subjects
        String[] subjects = {
            "First Semester Subjects",
            "ENGL100 | Communication Arts",
            "SOCIO102 | Gender and Society",
            "MATH100 | College Mathematics",
            "PSYCH101 | Understanding the Self",
            "CC-INTCOM11 | Introduction to Computing",
            "CC-COMPROG11 | Computer Programming 1",
            "IT-WEBDEV11 | Web Design & Development",
            "PE101 | Movement Competency Training",
            "NSTP101 | National Service Training Program 1",
        };

        subjectLabels = new JLabel[subjects.length]; // Initializing label array
        subjectTextFields = new JTextField[subjects.length]; // Initializing text field array

        gradesLabel = new JLabel("Grades"); // Creating "Grades" label

        // Looping through each subject to create labels and text fields
        for (int i = 0; i < subjects.length; i++) {
            gbc.gridx = 0; // Setting column position to 0 (left side)
            gbc.gridy = i; // Setting row position based on loop index
            gbc.anchor = GridBagConstraints.WEST; // Aligning text to the left
            subjectLabels[i] = new JLabel(subjects[i]); // Creating label with subject name
            subjectLabels[i].setFont(new Font(Font.SERIF, Font.BOLD, 12)); // Setting font style
            panel.add(subjectLabels[i], gbc); // Adding label to panel

            panel.add(gradesLabel); // Adding "Grades" label to panel

            if (i != 0) { // Skipping first label ("First Semester Subjects")
                gbc.gridx = 1; // Setting column position to 1 (right side)
                gbc.anchor = GridBagConstraints.CENTER; // Aligning to center
                subjectTextFields[i] = new JTextField(); // Creating a new text field
                subjectTextFields[i].setPreferredSize(new Dimension(50, 20)); // Setting size of text field
                subjectTextFields[i].setHorizontalAlignment(JTextField.CENTER); // Centering text in the field
                panel.add(subjectTextFields[i], gbc); // Adding text field to panel
            }
        }

        nextButton = new JButton("Next"); // Creating "Next" button
        gbc.gridx = 0; // Setting column position to 0
        gbc.gridy = subjects.length + 1; // Setting row position after subjects
        gbc.gridwidth = 2; // Making button span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Centering the button
        panel.add(nextButton, gbc); // Adding button to panel

        // Adding action listener to "Next" button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalGrades = 0; // Variable to store total grade sum
                int count = 0; // Counter for valid grades entered
                StringBuilder failedSubjects = new StringBuilder(); // String to store failed subjects

                for (int i = 1; i < subjects.length; i++) { // Looping through subjects (excluding title)
                    try {
                        double grade = Double.parseDouble(subjectTextFields[i].getText().trim()); // Converting text field input to double
                        if (grade >= 3.1 && grade <= 5.0) { // Checking if grade is failing
                            failedSubjects.append(subjectLabels[i].getText()).append("\n"); // Adding failed subject to list
                        }
                        totalGrades += grade; // Adding grade to total
                        count++; // Incrementing count
                    } catch (NumberFormatException ex) { // Handling invalid input
                        JOptionPane.showMessageDialog(frame, "Please enter valid numeric grades."); // Displaying error message
                        return; // Exiting method
                    }
                }

                if (count > 0) { // Checking if grades were entered
                    firstSemAvgGrade = totalGrades / count; // Calculating average grade
                    JOptionPane.showMessageDialog(frame, "First Semester Average Grade: " + String.format("%.1f", firstSemAvgGrade)); // Displaying average grade
                }

                if (failedSubjects.length() > 0) { // Checking if there are failed subjects
                    JOptionPane.showMessageDialog(frame, "Warning: You have failed subjects.\nYou cannot proceed to the second semester."); // Displaying warning
                    frame.getContentPane().removeAll(); // Clearing frame
                    Suggest suggest = new Suggest(frame, username, firstSemAvgGrade, secondSemAvgGrade, 0, failedSubjects.toString()); // Creating suggestion page
                    frame.add(suggest.getPanel()); // Adding suggestion panel
                    frame.revalidate(); // Refreshing frame
                    frame.repaint(); // Redrawing frame
                    return; // Exiting method
                }

                frame.getContentPane().removeAll(); // Clear the frame
                frame.add(secondSem(frame, username)); // Load second semester panel
                frame.revalidate(); // Refresh frame
                frame.repaint(); // Redraw frame
            }
        });
    }
    
    public JPanel secondSem(JFrame frame, String username) {
        frame.setTitle("Welcome " + username + "!");

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#2494cd"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] subjects = {
            "Second Semester Subjects",
                "ENGL101 | Purposive Communication",
                "ENTREP101 | The Entrepreneurial Mind",
                "MATH101 | Mathematics in the Modern World",
                "HIST101 | Readings in Philippine History",
                "HUM101 | Art Appreciation",
                "CC-COMPROG12 | Computer Programming 2",
                "CC-DISCRET12 | Discrete Structures",
                "PE102 | Exercise-based Fitness Activities",
                "NSTP102 | National Service Training Program 2"
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
                    StringBuilder failedSubjects = new StringBuilder();
            
                    for (int i = 1; i < subjects.length; i++) {
                        try {
                            double grade = Double.parseDouble(subjectTextFields[i].getText().trim());
                            if (grade >= 3.1 && grade <= 5.0) {
                                failedSubjects.append(subjectLabels[i].getText()).append("\n");
                            }
                            totalGrades += grade;
                            count++;
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Please enter valid numeric grades.");
                            return;
                        }
                    }
            
                    if (count > 0) {
                        secondSemAvgGrade = totalGrades / count;
                        JOptionPane.showMessageDialog(frame, "Second Semester Average Grade: " + String.format("%.1f", secondSemAvgGrade));
                    }
            
                    if (failedSubjects.length() > 0) {
                        double totalAvgGrade = (firstSemAvgGrade + secondSemAvgGrade) / 2;
                        JOptionPane.showMessageDialog(frame, "Warning: You have failed subjects.");
                        frame.getContentPane().removeAll();
                        Suggest suggest = new Suggest(frame, username, firstSemAvgGrade, secondSemAvgGrade, totalAvgGrade, failedSubjects.toString());
                        frame.add(suggest.getPanel());
                        frame.revalidate();
                        frame.repaint();
                        return;
                    }
            
                    double totalAvgGrade = (firstSemAvgGrade + secondSemAvgGrade) / 2;

                    frame.getContentPane().removeAll();
                    Suggest suggest = new Suggest(frame, username, firstSemAvgGrade, secondSemAvgGrade, totalAvgGrade, failedSubjects.toString());
                    frame.add(suggest.getPanel());
                    frame.revalidate();
                    frame.repaint();
                }
            });

            return panel;
    }
    
    // Getter for first semester average grade
    public double getFirstSemAvgGrade() {
        return firstSemAvgGrade;
    }

    // Getter for second semester average grade
    public double getSecondSemAvgGrade() {
        return secondSemAvgGrade;
    }

    // Getter for total average grade
    public double getTotalAvgGrade() {
        return totalAvgGrade;
    }

    public JPanel getPanel() {
        return panel; // Returning the main panel
    }
}