import javax.swing.*; // Import Java Swing library for GUI components
import java.awt.*; // Import AWT for layout and colors
import java.awt.event.ActionEvent; // Import for handling button click events
import java.awt.event.ActionListener; // Import for defining button click behavior

public class ThirdYear {
    // Declare main panel to hold all components
    JPanel panel;
    
    // Array of labels to display subject names
    JLabel[] subjectLabels;
    
    // Declare panels for text fields and labels (not used in this code but declared)
    JPanel textFieldPanel, labelPanel;
    
    // Array of text fields for entering grades
    JTextField[] subjectTextFields;
    
    // Label for the "Grades" column
    JLabel gradesLabel;
    
    // Button to proceed to the next step
    JButton nextButton;
    
    // Variables to store average grades
    private double firstSemAvgGrade;
    private double secondSemAvgGrade;
    private double totalAvgGrade;

    // Constructor that sets up the GUI for Third Year subjects
    public ThirdYear(JFrame frame, String username) {
        frame.setTitle("Welcome " + username + "!"); // Set window title with username

        panel = new JPanel(new GridBagLayout()); // Create panel with GridBagLayout for flexible positioning
        panel.setBackground(Color.decode("#2494cd")); // Set background color
        GridBagConstraints gbc = new GridBagConstraints(); // Object to define layout constraints
        gbc.insets = new Insets(5, 5, 5, 5); // Set padding between components

        // Array of subject names for the first semester
        String[] subjects = {
            "First Semester Subjects",
            "IT-IMDBSYS31 | Information Management (DB Sys. 1)",
            "IT-NETWORK31 | Computer Networks",
            "IT-TESTQUA31 | Testing & Quality Assurance",
            "CC-HCI31 | Human Computer Interaction",
            "CC-RESCOM31 | Methods of Research in Computing",
            "IT-EL_____ | IT Elective 1",
            "IT-FRE_____ | Free Elective 1",
            "IT-FRE_____ | Free Elective 2",
        };

        subjectLabels = new JLabel[subjects.length]; // Initialize label array
        subjectTextFields = new JTextField[subjects.length]; // Initialize text field array

        gradesLabel = new JLabel("Grades"); // Create label for grades

        for (int i = 0; i < subjects.length; i++) {
            gbc.gridx = 0; // Set column position to first column
            gbc.gridy = i; // Set row position based on loop index
            gbc.anchor = GridBagConstraints.WEST; // Align labels to the left
            
            subjectLabels[i] = new JLabel(subjects[i]); // Create label with subject name
            subjectLabels[i].setFont(new Font(Font.SERIF, Font.BOLD, 12)); // Set font style
            panel.add(subjectLabels[i], gbc); // Add label to panel
            
            panel.add(gradesLabel); // Add grades label to panel

            if (i != 0) { // Exclude the header row from having a text field
                gbc.gridx = 1; // Set column position to second column
                gbc.anchor = GridBagConstraints.CENTER; // Center-align text fields
                
                subjectTextFields[i] = new JTextField(); // Create a text field
                subjectTextFields[i].setPreferredSize(new Dimension(50, 20)); // Set size of text field
                subjectTextFields[i].setHorizontalAlignment(JTextField.CENTER); // Center text inside field
                panel.add(subjectTextFields[i], gbc); // Add text field to panel
            }
        }

        nextButton = new JButton("Next"); // Create "Next" button
        gbc.gridx = 0; // Position button in first column
        gbc.gridy = subjects.length + 1; // Position button below the last subject
        gbc.gridwidth = 2; // Span button across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center-align button
        panel.add(nextButton, gbc); // Add button to panel

        // Define what happens when the "Next" button is clicked
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalGrades = 0; // Variable to sum grades
                int count = 0; // Counter for valid grades
                StringBuilder failedSubjects = new StringBuilder(); // String to store failed subjects
        
                for (int i = 1; i < subjects.length; i++) { // Start from 1 to exclude header row
                    try {
                        double grade = Double.parseDouble(subjectTextFields[i].getText().trim()); // Get and convert grade
                        
                        if (grade >= 3.1 && grade <= 5.0) { // Check if grade is failing
                            failedSubjects.append(subjectLabels[i].getText()).append("\n"); // Add to failed list
                        }
                        
                        totalGrades += grade; // Add grade to total
                        count++; // Increase valid grade count
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter valid numeric grades."); // Show error for invalid input
                        return;
                    }
                }
        
                if (count > 0) {
                    firstSemAvgGrade = totalGrades / count; // Compute average grade
                    JOptionPane.showMessageDialog(frame, "First Semester Average Grade: " + String.format("%.1f", firstSemAvgGrade)); // Display average grade
                }
        
                if (failedSubjects.length() > 0) { // If any failed subjects exist
                    JOptionPane.showMessageDialog(frame, "Warning: You have failed subjects.\nYou cannot proceed to the second semester.");
                    frame.getContentPane().removeAll(); // Clear window
                    Suggest suggest = new Suggest(frame, username, firstSemAvgGrade, secondSemAvgGrade, 0, failedSubjects.toString()); // Suggest actions
                    frame.add(suggest.getPanel()); // Show suggestion panel
                    frame.revalidate();
                    frame.repaint();
                    return;
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

    // Function to return the panel
    public JPanel getPanel() {
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
}