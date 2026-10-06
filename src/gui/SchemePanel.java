package gui;

import model.ScholarshipScheme;
import service.ScholarshipService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SchemePanel extends JPanel {

    public SchemePanel(ScholarshipService service) {

        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 247, 251));

        JLabel title =
                new JLabel("Scholarship Schemes");

        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                26));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 10, 30));

        add(title, BorderLayout.NORTH);

        String[] columns = {
                "Scheme ID",
                "Scheme Name",
                "Minimum CGPA",
                "Maximum Income",
                "Award Amount"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        for (ScholarshipScheme scheme :
                service.getSchemes()) {

            model.addRow(new Object[]{
                    scheme.getSchemeId(),
                    scheme.getSchemeName(),
                    scheme.getMinimumCgpa(),
                    "₹ " + scheme.getMaximumIncome(),
                    "₹ " + scheme.getAwardAmount()
            });
        }

        JTable table =
                new JTable(model);

        table.setRowHeight(35);
        table.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                14));

        table.getTableHeader()
                .setFont(new Font(
                        "SansSerif",
                        Font.BOLD,
                        14));

        add(
                new JScrollPane(table),
                BorderLayout.CENTER);
    }
}