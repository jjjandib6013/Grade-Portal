import javax.swing.*;
import java.awt.*;

public class FirstYear {
    JLabel usernameLabel;
    JPanel panel;
    JLabel[] subjectLabels;
    JPanel textFieldPanel;
    JTextField[] subjectTextFields;

    public FirstYear(JFrame frame, String username) {
        panel = new JPanel();
        panel.setLayout(null);

        // Initialize Panels


        String[] subjects = {
            "ENGL100 | Communication Arts",
            "SOCIO102 | Gender and Society",
            "MATH100 | College Mathematics",
            "PSYCH101 | Understanding the Self",
            "CC-INTCOM11 | Introduction to Computing",
            "CC-COMPROG11 | Computer Programming 1",
            "IT-WEBDEV11 | Web Design & Development",
            "PE101 | Movement Competency Training",
            "NSTP101 | National Service Training Program 1",
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

        usernameLabel = new JLabel("Welcome, " + username + "!");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setBounds(30, 170, 50, 50);
        panel.add(usernameLabel);

        int yLabels = 15;

        for (int i = 0; i < subjects.length; i++) {
            subjectLabels[i] = new JLabel(subjects[i]);
            subjectLabels[i].setFont(new Font(Font.SERIF, Font.BOLD, 12));
            subjectLabels[i].setBounds(30,yLabels * (i + i),300,50);

            panel.add(subjectLabels[i]);

            // Initialize and add JTextFields
            subjectTextFields[i] = new JTextField(10);
            subjectTextFields[i].setFont(new Font(Font.SERIF, Font.PLAIN, 10));

            panel.add(subjectTextFields[i]);
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
