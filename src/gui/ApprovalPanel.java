package gui;

import model.ScholarshipApplication;
import service.ScholarshipService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ApprovalPanel extends JPanel {

    private ScholarshipService service;
    private DefaultTableModel model;

    public ApprovalPanel(
            ScholarshipService service) {

        this.service = service;

        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 247, 251));

        JLabel title =
                new JLabel("Application Approvals");

        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                26));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 10, 30));

        add(title, BorderLayout.NORTH);

        String[] columns = {
                "Application ID",
                "Student",
                "Scheme",
                "Status",
                "Stage",
                "Award"
        };

        model =
                new DefaultTableModel(
                        columns,
                        0);

        JTable table =
                new JTable(model);

        table.setRowHeight(32);

        add(
                new JScrollPane(table),
                BorderLayout.CENTER);

        JPanel buttons = new JPanel();

        buttons.setBackground(
                new Color(245, 247, 251));

        JButton approve =
                new JButton("Approve");

        JButton reject =
                new JButton("Reject");

        approve.addActionListener(
                e -> approveApplication());

        reject.addActionListener(
                e -> rejectApplication());

        buttons.add(approve);
        buttons.add(reject);

        add(
                buttons,
                BorderLayout.SOUTH);

        refreshTable();
    }

    private void refreshTable() {

        model.setRowCount(0);

        for (ScholarshipApplication app :
                service.getApplications()) {

            model.addRow(new Object[]{
                    app.getApplicationId(),
                    app.getStudent().getName(),
                    app.getScheme().getSchemeName(),
                    app.getStatus(),
                    app.getCurrentStage(),
                    "₹ " + app.getAwardAmount()
            });
        }
    }

    private String getSelectedId() {

        JTable table =
                (JTable) ((JScrollPane)
                        getComponent(1)).getViewport()
                        .getView();

        int row =
                table.getSelectedRow();

        if (row == -1) {
            return null;
        }

        return model.getValueAt(
                row,
                0).toString();
    }

    private void approveApplication() {

        String id = getSelectedId();

        if (id == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an application.");

            return;
        }

        try {

            service.approveApplication(id);

            JOptionPane.showMessageDialog(
                    this,
                    "Application approved successfully!");

            refreshTable();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Approval Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rejectApplication() {

        String id = getSelectedId();

        if (id == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an application.");

            return;
        }

        String reason =
                JOptionPane.showInputDialog(
                        this,
                        "Enter rejection reason:");

        if (reason == null
                || reason.trim().isEmpty()) {

            return;
        }

        try {

            service.rejectApplication(
                    id,
                    reason);

            JOptionPane.showMessageDialog(
                    this,
                    "Application rejected.");

            refreshTable();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}