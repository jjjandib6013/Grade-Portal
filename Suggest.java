import javax.swing.*;
import java.awt.*;

public class Suggest {
    JPanel panel;
    JLabel firstSemLabel, secondSemLabel, totalAvgLabel, suggestionLabel;

    public Suggest(JFrame frame, double firstSemAvg, double secondSemAvg, double totalAvg) {
        frame.setTitle("Suggestions");

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Labels for grades
        firstSemLabel = new JLabel("First Semester Average: " + String.format("%.2f", firstSemAvg));
        panel.add(firstSemLabel, gbc);

        gbc.gridy++;
        secondSemLabel = new JLabel("Second Semester Average: " + String.format("%.2f", secondSemAvg));
        panel.add(secondSemLabel, gbc);

        gbc.gridy++;
        totalAvgLabel = new JLabel("Total Average Grade: " + String.format("%.2f", totalAvg));
        panel.add(totalAvgLabel, gbc);

        // Determine suggestion based on total average
        String suggestionText = determineSuggestion(totalAvg);
        suggestionLabel = new JLabel("Suggestion: " + suggestionText);
        suggestionLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        suggestionLabel.setForeground(Color.BLUE);

        gbc.gridy++;
        panel.add(suggestionLabel, gbc);
    }

    // Method to determine suggestions based on total average grade
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
            return "Invalid Grade Range";
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
