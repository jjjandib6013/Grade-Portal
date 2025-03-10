import javax.swing.*;
import java.awt.*;

public class FirstYear {
    JLabel usernameLabel;
    JLabel firstSem;
    JLabel secondSem;
    JPanel panel;
    JLabel[] firstSemSubjectLabels;
    JLabel[] secondSemSubjectLabels;

    public FirstYear(JFrame frame, String username) {
        panel = new JPanel(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        String[] firstSemSubjects = {
            "ENGL100 | Communication Arts", // 0 
            "SOCIO102 | Gender and Society", // 1
            "MATH100 | College Mathematics", // 2
            "PSYCH101 | Understanding the Self", // 3
            "CC-INTCOM11 | Introduction to Computing", // 4
            "CC-COMPROG11 | Computer Programming 1", // 5
            "IT-WEBDEV11 | Web Design & Development", // 6
            "PE101 | Movement Competency Training", // 7
            "NSTP101 | National Service Training Program 1", // 8
        };
        
        String[] secondSemSubjects = {
            "ENGL101 | Purposive Communication", // 0
            "ENTREP101 | The Entreprenurial Mind", // 1
            "MATH101 | Mathematics in the Modern World", // 2
            "HIST101 | Readings in Philippine History", // 3
            "HUM101 | Art Appreciation", // 4
            "CC-COMPROG12 | Computer Programming 2", // 5
            "CC-DISCRET12 | Discrete Structures", // 6
            "PE102 | Exercise-based Fitness Activities", // 7
            "NSTP102 | National Service Training Program 2", // 8
        };

        firstSemSubjectLabels = new JLabel[firstSemSubjects.length];
        secondSemSubjectLabels = new JLabel[secondSemSubjects.length];

        usernameLabel = new JLabel("Welcome, " + username + "!");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(30, 170, 0, 0));
        panel.add(usernameLabel, BorderLayout.NORTH);

        int topPaddingIncrement = 5;

        for (int i = 0; i < firstSemSubjects.length; i++) {
            firstSemSubjectLabels[i] = new JLabel(firstSemSubjects[i]);
            firstSemSubjectLabels[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));

            int topPadding = 10 + (topPaddingIncrement);
            firstSemSubjectLabels[i].setBorder(BorderFactory.createEmptyBorder(topPadding, 20, 0, 0));

            panel.add(firstSemSubjectLabels[i]);
        }

        for (int i = 0; i < secondSemSubjects.length; i++) {
            secondSemSubjectLabels[i] = new JLabel(secondSemSubjects[i]);
            secondSemSubjectLabels[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));

            int topPadding = 10 + (topPaddingIncrement);
            secondSemSubjectLabels[i].setBorder(BorderFactory.createEmptyBorder(topPadding, 20, 0, 0));

            panel.add(secondSemSubjectLabels[i]);
        }


    }

    public JPanel getPanel() {
        return panel;
    }

}