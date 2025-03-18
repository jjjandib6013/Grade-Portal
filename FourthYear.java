import javax.swing.*; // Importing the Swing package for creating GUI components
import java.awt.*; // Importing the AWT package for layout and color handling
import java.awt.event.ActionEvent; // Importing event handling for button clicks
import java.awt.event.ActionListener; // Importing ActionListener to handle button actions
import java.io.*; // Importing I/O package (not used in this code but can be useful for file operations)

// Defining the FourthYear class
public class FourthYear {
    JPanel panel; // Panel to hold all GUI components
    JLabel[] subjectLabels; // Array of labels for displaying subject names
    JTextField[] subjectTextFields; // Array of text fields for entering grades
    JLabel gradesLabel; // Label for the grades column
    JButton nextButton; // Button to move to the next step
    private double firstSemAvgGrade; // Variable to store first semester average grade
    private double secondSemAvgGrade; // Variable to store second semester average grade
    private double totalAvgGrade; // Variable to store total average grade

    // Constructor for the FourthYear class, accepting the main frame and username
    public FourthYear(JFrame frame, String username) {
        frame.setTitle("Welcome " + username + "!"); // Setting the window title with the user's name

        panel = new JPanel(new GridBagLayout()); // Creating a panel with GridBagLayout for flexible positioning
        panel.setBackground(Color.decode("#2494cd")); // Setting background color to a light blue shade
        GridBagConstraints gbc = new GridBagConstraints(); // Creating constraints for layout management
        gbc.insets = new Insets(5, 5, 5, 5); // Adding padding between components

        // Array of subject names for the first semester
        String[] subjects = {
            "First Semester Subjects",
            "SUMMER: IT-CPSTONE30 | Capstone Project 1",
            "SUMMER: CC-PROFIS10 | Professional Issues in Computing",
            "LIT11 | Literatures of the World",
            "IT-CPSTONE40 | Capstone Project 2",
            "IT-EL_____ | IT Elective 3",
            "IT-FRE_____ | Free Elective 4",
        };

        subjectLabels = new JLabel[subjects.length]; // Creating an array to store subject labels
        subjectTextFields = new JTextField[subjects.length]; // Creating an array to store text fields

        gradesLabel = new JLabel("Grades"); // Creating a label for the grades section

        // Looping through all subjects to create labels and text fields
        for (int i = 0; i < subjects.length; i++) {
            gbc.gridx = 0; // Positioning column at 0 (left side)
            gbc.gridy = i; // Setting row position
            gbc.anchor = GridBagConstraints.WEST; // Aligning text to the left
            subjectLabels[i] = new JLabel(subjects[i]); // Creating a new label for the subject
            subjectLabels[i].setFont(new Font(Font.SERIF, Font.BOLD, 12)); // Setting font style
            panel.add(subjectLabels[i], gbc); // Adding label to the panel

            if (i != 0) { // Skipping the first title label
                gbc.gridx = 1; // Placing text fields in the second column
                gbc.anchor = GridBagConstraints.CENTER; // Center-aligning the text field
                subjectTextFields[i] = new JTextField(); // Creating a text field
                subjectTextFields[i].setPreferredSize(new Dimension(50, 20)); // Setting fixed size
                subjectTextFields[i].setHorizontalAlignment(JTextField.CENTER); // Centering text inside the field
                panel.add(subjectTextFields[i], gbc); // Adding text field to the panel
            }
        }

        nextButton = new JButton("Next"); // Creating a button labeled "Next"
        gbc.gridx = 0;
        gbc.gridy = subjects.length + 1; // Positioning below the last subject
        gbc.gridwidth = 2; // Making the button span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Centering the button
        panel.add(nextButton, gbc); // Adding the button to the panel

        // Adding an action listener to the "Next" button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalGrades = 0; // Variable to store the sum of grades
                int count = 0; // Counter for the number of subjects
                StringBuilder failedSubjects = new StringBuilder(); // String to store failed subjects

                // Loop through text fields to get entered grades
                for (int i = 1; i < subjects.length; i++) {
                    try {
                        double grade = Double.parseDouble(subjectTextFields[i].getText().trim()); // Convert input to double
                        if (grade >= 3.1 && grade <= 5.0) { // Checking if the student failed
                            failedSubjects.append(subjectLabels[i].getText()).append("\n"); // Adding failed subject to the list
                        }
                        totalGrades += grade; // Adding grade to total sum
                        count++; // Increasing subject count
                    } catch (NumberFormatException ex) { // Handling invalid input
                        JOptionPane.showMessageDialog(frame, "Please enter valid numeric grades."); // Show error message
                        return;
                    }
                }

                if (count > 0) { // Checking if grades were entered
                    firstSemAvgGrade = totalGrades / count; // Calculating average grade
                    JOptionPane.showMessageDialog(frame, "First Semester Average Grade: " + String.format("%.1f", firstSemAvgGrade)); // Displaying the average
                }

                if (failedSubjects.length() > 0) { // Checking if there are failed subjects
                    JOptionPane.showMessageDialog(frame, "Warning: You have failed subjects.\nYou cannot proceed to the second semester."); // Show warning
                    return;
                }

                frame.getContentPane().removeAll(); // Clear the frame
                frame.add(secondSem(frame, username)); // Load second semester panel
                frame.revalidate(); // Refresh frame
                frame.repaint(); // Redraw frame
            }
        });
    }

    // Method to create the second semester panel
    public JPanel secondSem(JFrame frame, String username) {
        frame.setTitle("Welcome " + username + "!");

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#2494cd"));
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

    public JPanel getPanel() {
        return panel; // Returning the panel
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
}
