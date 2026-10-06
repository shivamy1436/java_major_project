package gui;

import model.ScholarshipApplication;
import service.ScholarshipService;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    private ScholarshipService service;
    private JTextField searchField;
    private JTextArea resultArea;

    public SearchPanel(
            ScholarshipService service) {

        this.service = service;

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 247, 251));

        JLabel title =
                new JLabel("Search Application");

        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                26));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 10, 30));

        add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        20,
                        20));

        searchPanel.setBackground(Color.WHITE);

        searchPanel.add(
                new JLabel("Application ID:"));

        searchField = new JTextField(15);

        searchPanel.add(searchField);

        JButton searchButton =
                new JButton("Search");

        searchButton.addActionListener(
                e -> searchApplication());

        searchPanel.add(searchButton);

        add(
                searchPanel,
                BorderLayout.CENTER);

        resultArea =
                new JTextArea();

        resultArea.setFont(new Font(
                "Monospaced",
                Font.PLAIN,
                14));

        resultArea.setEditable(false);

        add(
                new JScrollPane(resultArea),
                BorderLayout.SOUTH);
    }

    private void searchApplication() {

        String id =
                searchField.getText().trim();

        ScholarshipApplication application =
                service.searchApplication(id);

        if (application == null) {

            resultArea.setText(
                    "Application not found.");

            return;
        }

        resultArea.setText(
                "APPLICATION DETAILS\n"
                + "============================\n"
                + "Application ID: "
                + application.getApplicationId()
                + "\n"
                + "Student ID: "
                + application.getStudent().getId()
                + "\n"
                + "Student Name: "
                + application.getStudent().getName()
                + "\n"
                + "Course: "
                + application.getStudent().getCourse()
                + "\n"
                + "CGPA: "
                + application.getStudent().getCgpa()
                + "\n"
                + "Family Income: ₹"
                + application.getStudent().getFamilyIncome()
                + "\n"
                + "Scheme: "
                + application.getScheme().getSchemeName()
                + "\n"
                + "Status: "
                + application.getStatus()
                + "\n"
                + "Current Stage: "
                + application.getCurrentStage()
                + "\n"
                + "Award: ₹"
                + application.getAwardAmount());
    }
}