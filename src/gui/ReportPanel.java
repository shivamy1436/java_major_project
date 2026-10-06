package gui;

import model.ScholarshipApplication;
import service.ReportService;
import service.ScholarshipService;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {

    public ReportPanel(
            ScholarshipService service) {

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 247, 251));

        JLabel title =
                new JLabel("Scholarship Reports");

        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                26));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 10, 30));

        add(title, BorderLayout.NORTH);

        ReportService reportService =
                new ReportService(service);

        JTextArea report =
                new JTextArea();

        report.setFont(new Font(
                "Monospaced",
                Font.PLAIN,
                14));

        report.setEditable(false);

        StringBuilder text =
                new StringBuilder();

        text.append(
                "SCHOLARSHIP PROCESSING REPORT\n");

        text.append(
                "====================================\n\n");

        text.append(
                "Total Applications: ")
                .append(
                        service.getApplications().size())
                .append("\n\n");

        text.append(
                "APPROVED APPLICATIONS\n");

        text.append(
                "------------------------------------\n");

        for (ScholarshipApplication app :
                reportService
                        .getApprovedApplications()) {

            text.append(
                    app.getApplicationId())
                    .append(" | ")
                    .append(
                            app.getStudent().getName())
                    .append(" | ₹")
                    .append(
                            app.getAwardAmount())
                    .append("\n");
        }

        text.append("\n");

        text.append(
                "PENDING APPLICATIONS\n");

        text.append(
                "------------------------------------\n");

        for (ScholarshipApplication app :
                reportService
                        .getPendingApplications()) {

            text.append(
                    app.getApplicationId())
                    .append(" | ")
                    .append(
                            app.getStudent().getName())
                    .append("\n");
        }

        text.append("\n");

        text.append(
                "TOTAL AWARD AMOUNT: ₹")
                .append(
                        reportService
                                .getTotalAwardAmount());

        report.setText(
                text.toString());

        add(
                new JScrollPane(report),
                BorderLayout.CENTER);
    }
}