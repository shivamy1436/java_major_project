package gui;

import model.*;
import service.ScholarshipService;

import javax.swing.*;
import java.awt.*;

public class ApplicationPanel extends JPanel {

    private ScholarshipService service;

    private JTextField appIdField;
    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField courseField;
    private JTextField semesterField;
    private JTextField cgpaField;
    private JTextField incomeField;

    private JComboBox<ScholarshipScheme> schemeBox;

    public ApplicationPanel(
            ScholarshipService service) {

        this.service = service;

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 251));

        createUI();
    }

    private void createUI() {

        JLabel title = new JLabel(
                "New Scholarship Application");

        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                26));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 15, 30));

        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(
                new GridLayout(0, 2, 15, 15));

        form.setBackground(Color.WHITE);

        form.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 40, 30, 40));

        appIdField = addField(form, "Application ID");
        studentIdField = addField(form, "Student ID");
        nameField = addField(form, "Student Name");
        courseField = addField(form, "Course");
        semesterField = addField(form, "Semester");
        cgpaField = addField(form, "CGPA");
        incomeField = addField(form, "Family Income");

        form.add(new JLabel("Scholarship Scheme"));

        schemeBox = new JComboBox<>();

        for (ScholarshipScheme scheme :
                service.getSchemes()) {

            schemeBox.addItem(scheme);
        }

        form.add(schemeBox);

        JCheckBox aadhaar =
                new JCheckBox("Aadhaar Submitted", true);

        JCheckBox incomeCertificate =
                new JCheckBox("Income Certificate", true);

        JCheckBox marksheet =
                new JCheckBox("Marksheet", true);

        JCheckBox bank =
                new JCheckBox("Bank Details", true);

        JPanel documents = new JPanel(
                new GridLayout(2, 2));

        documents.setBackground(Color.WHITE);

        documents.add(aadhaar);
        documents.add(incomeCertificate);
        documents.add(marksheet);
        documents.add(bank);

        form.add(new JLabel("Required Documents"));
        form.add(documents);

        JButton submit = new JButton(
                "Submit Application");

        submit.setBackground(
                new Color(79, 70, 229));

        submit.setForeground(Color.WHITE);

        submit.setFocusPainted(false);

        submit.addActionListener(e -> {

            try {

                Student student =
                        new Student(
                                studentIdField.getText(),
                                nameField.getText(),
                                courseField.getText(),
                                Integer.parseInt(
                                        semesterField.getText()),
                                Double.parseDouble(
                                        cgpaField.getText()),
                                Double.parseDouble(
                                        incomeField.getText()));

                Document document =
                        new Document(
                                aadhaar.isSelected(),
                                incomeCertificate.isSelected(),
                                marksheet.isSelected(),
                                bank.isSelected());

                ScholarshipApplication application =
                        new ScholarshipApplication(
                                appIdField.getText(),
                                student,
                                (ScholarshipScheme)
                                        schemeBox.getSelectedItem(),
                                document);

                service.validateApplication(
                        application);

                service.addApplication(application);

                JOptionPane.showMessageDialog(
                        this,
                        "Application submitted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                clearFields();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Invalid Application",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        form.add(new JLabel(""));
        form.add(submit);

        add(
                form,
                BorderLayout.CENTER);
    }

    private JTextField addField(
            JPanel panel,
            String label) {

        panel.add(new JLabel(label));

        JTextField field =
                new JTextField();

        panel.add(field);

        return field;
    }

    private void clearFields() {

        appIdField.setText("");
        studentIdField.setText("");
        nameField.setText("");
        courseField.setText("");
        semesterField.setText("");
        cgpaField.setText("");
        incomeField.setText("");
    }
}