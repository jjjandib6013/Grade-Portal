import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfo {
    JLabel titleLabel, nameLabel, yearLeveLabel;
    JTextField nameTextField;
    JButton continueButton;
    JComboBox<String> yearLevelCombobox;
    JPanel panel;

    public UserInfo(JFrame frame) {
        FirstYear first = new FirstYear();
        SecondYear second = new SecondYear();
        ThirdYear third = new ThirdYear();
        FourthYear fourth = new FourthYear();

        frame.setTitle("Information Page");
        panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel("Enter your information", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        nameLabel = new JLabel("Enter your name: ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);

        nameTextField = new JTextField(15);
        nameTextField.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameTextField, gbc);

        continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 14));
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = nameTextField.getText();
                String yearLevel = yearLevelCombobox.getSelectedItem();

                if (yearLevel == "1ST YEAR") {
                    frame.add(first.getPanel());
                } else if (yearLevel == "2ND YEAR") {
                    frame.add(second.getPanel());
                } else if (yearLevel == "3RD YEAR") {
                    frame.add(third.getPanel());
                } else if (yearLevel == "4TH YEAR") {
                    frame.add(fourth.getPanel());
                }
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(continueButton, gbc);

        yearLeveLabel = new JLabel("Year Level: ");
        yearLeveLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(yearLeveLabel, gbc);

        yearLevelCombobox = new JComboBox<>(new String[] {
            "1ST YEAR", "2ND YEAR", "3RD YEAR", "4TH YEAR" 
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(yearLevelCombobox, gbc);
    }
    
    public JPanel getPanel() {
        return panel;
    }
}