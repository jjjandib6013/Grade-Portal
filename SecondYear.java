import javax.swing.*; // Import Swing library for GUI components
import java.awt.*; // Import AWT library for layout and components
import java.awt.event.ActionEvent; // Import ActionEvent for handling button clicks
import java.awt.event.ActionListener; // Import ActionListener interface for event handling
import java.io.*; // Import IO library (not used in the current code but may be used for file handling later)

// Define a public class named SecondYear
public class SecondYear {
    JPanel panel; // Declare a JPanel to hold all UI components
    JLabel[] subjectLabels; // Declare an array of JLabels for subject names
    JPanel textFieldPanel, labelPanel; // Declare two panels (not used in the code but may be for layout organization)
    JTextField[] subjectTextFields; // Declare an array of JTextFields for user input (grades)
    JLabel gradesLabel; // Declare a JLabel to label the grade column
    JButton nextButton; // Declare a JButton for navigating to the next step
    private double firstSemAvgGrade; // Declare a variable to store first semester average grade
    private double secondSemAvgGrade; // Declare a variable to store second semester average grade
    private double totalAvgGrade; // Declare a variable to store the overall average grade

    // Constructor method for the SecondYear class
    public SecondYear(JFrame frame, String username) {
        frame.setTitle("Welcome " + username + "!"); // Set the title of the frame to include the username

        panel = new JPanel(new GridBagLayout()); // Initialize the panel with GridBagLayout for flexible layout
        panel.setBackground(Color.decode("#2494cd")); // Set background color of the panel
        GridBagConstraints gbc = new GridBagConstraints(); // Create a GridBagConstraints object for layout control
        gbc.insets = new Insets(5, 5, 5, 5); // Set padding between components

        // Define an array of subjects for the first semester
        String[] subjects = {
            "First Semester Subjects",
            "SOCIO101 | The Contemporary World",
            "RIZAL101 | Life, Works & Writings of Dr. Jose Rizal",
            "CC-DIGILOG21 | Digital Logic Design",
            "IT-OOPROG21 | Object Oriented Programming",
            "IT-SAD21 | System Analysis & Design",
            "CC-ACCTG21 | Accounting for IT",
            "CC-TWRITE21 | Technical Writing & Presentation Skills in IT",
            "PE 103 | Sports and Dance (PATHFit 3)",
        };

        subjectLabels = new JLabel[subjects.length]; // Initialize the subjectLabels array
        subjectTextFields = new JTextField[subjects.length]; // Initialize the subjectTextFields array

        gradesLabel = new JLabel("Grades"); // Create a JLabel for the grade section

        for (int i = 0; i < subjects.length; i++) { // Loop through each subject
            gbc.gridx = 0; // Set column position to 0
            gbc.gridy = i; // Set row position to i (incrementing each time)
            gbc.anchor = GridBagConstraints.WEST; // Align label text to the left
            subjectLabels[i] = new JLabel(subjects[i]); // Create a JLabel for each subject
            subjectLabels[i].setFont(new Font(Font.SERIF, Font.BOLD, 12)); // Set font style and size
            panel.add(subjectLabels[i], gbc); // Add the label to the panel

            panel.add(gradesLabel); // Add the "Grades" label to the panel

            if (i != 0) { // Skip the first entry as it's a header
                gbc.gridx = 1; // Move to the next column for text fields
                gbc.anchor = GridBagConstraints.CENTER; // Center the text field
                subjectTextFields[i] = new JTextField(); // Create a new text field for grade input
                subjectTextFields[i].setPreferredSize(new Dimension(50, 20)); // Set text field size
                subjectTextFields[i].setHorizontalAlignment(JTextField.CENTER); // Center the text inside the field
                panel.add(subjectTextFields[i], gbc); // Add text field to the panel
            }
        }

        nextButton = new JButton("Next"); // Create a button labeled "Next"
        gbc.gridx = 0; // Set column position to 0
        gbc.gridy = subjects.length + 1; // Set row position below the last subject
        gbc.gridwidth = 2; // Make button span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        panel.add(nextButton, gbc); // Add button to the panel

        nextButton.addActionListener(new ActionListener() { // Add an event listener to the button
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalGrades = 0; // Initialize total grade counter
                int count = 0; // Initialize subject count
                StringBuilder failedSubjects = new StringBuilder(); // Initialize a StringBuilder to track failed subjects
        
                for (int i = 1; i < subjects.length; i++) { // Loop through subjects (skip header)
                    try {
                        double grade = Double.parseDouble(subjectTextFields[i].getText().trim()); // Parse grade input
                        if (grade >= 3.1 && grade <= 5.0) { // Check if grade is failing
                            failedSubjects.append(subjectLabels[i].getText()).append("\n"); // Append failed subject
                        }
                        totalGrades += grade; // Add grade to total
                        count++; // Increment count
                    } catch (NumberFormatException ex) { // Catch invalid number input
                        JOptionPane.showMessageDialog(frame, "Please enter valid numeric grades."); // Show error message
                        return; // Stop execution
                    }
                }
        
                if (count > 0) { // Ensure at least one subject was entered
                    firstSemAvgGrade = totalGrades / count; // Calculate average grade
                    JOptionPane.showMessageDialog(frame, "First Semester Average Grade: " + String.format("%.1f", firstSemAvgGrade)); // Show result
                }
        
                if (failedSubjects.length() > 0) { // If there are failed subjects
                    JOptionPane.showMessageDialog(frame, "Warning: You have failed subjects.\nYou cannot proceed to the second semester."); // Show warning
                    frame.getContentPane().removeAll(); // Clear frame content
                    Suggest suggest = new Suggest(frame, username, firstSemAvgGrade, secondSemAvgGrade, 0, failedSubjects.toString()); // Create Suggest object
                    frame.add(suggest.getPanel()); // Add Suggest panel to frame
                    frame.revalidate(); // Refresh frame
                    frame.repaint(); // Repaint frame
                    return; // Stop execution
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
        return panel; // Return the panel for display
    }
}