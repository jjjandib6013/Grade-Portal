import javax.swing.*; // Importing the Swing library for GUI components
import java.awt.*; // Importing the AWT library for layout and color settings
import java.io.FileWriter; // Importing FileWriter for writing to a file
import java.io.IOException; // Importing IOException to handle file writing errors

public class Suggest {
    JPanel panel; // A panel to hold all GUI components
    JLabel firstSemLabel, secondSemLabel, totalAvgLabel, suggestionLabel, failedSubjectsLabel;
    JLabel suggestionsLabel; // Label to display suggestions

    // Constructor to display suggestions based on grades
    public Suggest(JFrame frame, String username, double firstSemAvg, double secondSemAvg, double totalAvg, String failedSubjects) {
        frame.setTitle("Suggestions"); // Set window title

        panel = new JPanel(new GridBagLayout()); // Creating a panel with GridBagLayout for flexible positioning
        panel.setBackground(Color.decode("#2494cd")); // Setting panel background color
        GridBagConstraints gbc = new GridBagConstraints(); // Creating layout constraints
        gbc.insets = new Insets(10, 10, 10, 10); // Adding padding between elements
        gbc.gridx = 0; // Setting column index to 0
        gbc.gridy = 0; // Setting row index to 0
        gbc.anchor = GridBagConstraints.WEST; // Aligning elements to the left

        // Label for First Semester Average Grade
        firstSemLabel = new JLabel("First Semester Average: " + String.format("%.2f", firstSemAvg));
        panel.add(firstSemLabel, gbc); // Adding label to panel

        // Label for Second Semester Average Grade
        gbc.gridy++; // Move to the next row
        secondSemLabel = new JLabel("Second Semester Average: " + String.format("%.2f", secondSemAvg));
        panel.add(secondSemLabel, gbc);

        // Label for Total Average Grade
        gbc.gridy++; // Move to the next row
        totalAvgLabel = new JLabel("Total Average Grade: " + String.format("%.2f", totalAvg));
        panel.add(totalAvgLabel, gbc);

        // Determining suggestions based on total average grade
        String suggestionText = determineSuggestion(totalAvg);
        suggestionLabel = new JLabel("Suggestion: " + suggestionText); // Label to display suggestion
        suggestionLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14)); // Setting font style and size
        suggestionLabel.setForeground(Color.BLUE); // Changing text color to blue

        gbc.gridy++; // Move to the next row
        panel.add(suggestionLabel, gbc); // Adding suggestion label to panel

        // If the student has failed subjects, display them
        if (!failedSubjects.isEmpty()) {
            gbc.gridy++; // Move to the next row
            failedSubjectsLabel = new JLabel("<html><b>Failed Subjects:</b><br>" + failedSubjects.replace("\n", "<br>") + "</html>");
            failedSubjectsLabel.setForeground(Color.RED); // Changing text color to red
            panel.add(failedSubjectsLabel, gbc); // Adding failed subjects label to panel
        }

        // Save the suggestions to a text file
        saveSuggestionsToFile(firstSemAvg, username, secondSemAvg, totalAvg, suggestionText, failedSubjects);
    }
    
    // Overloaded constructor for displaying saved suggestions
    public Suggest(JFrame frame, String username, String suggestions) {
        frame.setTitle("Suggestions for " + username); // Set window title with username

        panel = new JPanel(new GridBagLayout()); // Creating a panel with GridBagLayout
        panel.setBackground(Color.decode("#2494cd")); // Setting panel background color
        GridBagConstraints gbc = new GridBagConstraints(); // Creating layout constraints
        gbc.insets = new Insets(10, 10, 10, 10); // Adding padding between elements
        gbc.gridx = 0; // Setting column index to 0
        gbc.gridy = 0; // Setting row index to 0
        gbc.anchor = GridBagConstraints.WEST; // Aligning elements to the left

        // Displaying the suggestions from the saved file
        suggestionsLabel = new JLabel("<html>" + username + "'s Information:\n" + suggestions.replace("\n", "<br>") + "</html>");
        suggestionsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14)); // Setting font style and size
        suggestionsLabel.setForeground(Color.BLACK); // Changing text color to black

        panel.add(suggestionsLabel, gbc); // Adding suggestions label to panel
    }

    // Method to determine suggestions based on the total average grade
    private String determineSuggestion(double avgGrade) {
        if (avgGrade >= 1.00 && avgGrade <= 1.59) {
            return "Advanced IT Subjects";
        } else if (avgGrade >= 1.60 && avgGrade <= 2.59) {
            return "Next-Level IT Subjects";
        } else if (avgGrade >= 2.60 && avgGrade <= 2.99) {
            return "Remedial IT Subjects";
        } else if (avgGrade >= 3.00 && avgGrade <= 5.00) {
            return "Tutorials & Retake Recommended";
        } else {
            return "Tutorials & Retake Recommended";
        }
    }
    
    // Method to save suggestions to a text file
    private void saveSuggestionsToFile(double firstSemAvg, String username, double secondSemAvg, double totalAvg, String suggestion, String failedSubjects) {
        try (FileWriter writer = new FileWriter(username + "Suggestions.txt")) { // Create a file with the username
            writer.write("First Semester Average: " + String.format("%.2f", firstSemAvg) + "\n"); // Write first sem grade
            writer.write("Second Semester Average: " + String.format("%.2f", secondSemAvg) + "\n"); // Write second sem grade
            writer.write("Total Average Grade: " + String.format("%.2f", totalAvg) + "\n"); // Write total grade
            writer.write("Suggestion: " + suggestion + "\n"); // Write suggestion
            
            if (!failedSubjects.isEmpty()) { // If there are failed subjects, write them in the file
                writer.write("Failed Subjects: \n" + failedSubjects + "\n");
            }
            
            System.out.println("Suggestions saved to " + username + "Suggestions.txt"); // Print confirmation message
        } catch (IOException e) { // Catch and handle errors if writing fails
            e.printStackTrace(); // Print error message
        }
    }

    // Method to return the panel containing all the components
    public JPanel getPanel() {
        return panel;
    }
}
