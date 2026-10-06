package gui;

import service.ScholarshipService;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private ScholarshipService service;
    private JPanel contentPanel;

    private final Color SIDEBAR_COLOR = new Color(30, 35, 60);
    private final Color PRIMARY_COLOR = new Color(79, 70, 229);
    private final Color BACKGROUND_COLOR = new Color(245, 247, 251);
    private final Color TEXT_COLOR = new Color(30, 35, 60);

    public DashboardFrame(ScholarshipService service) {

        this.service = service;

        setTitle("Student Scholarship Processing System");
        setSize(1200, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createInterface();
    }

    private void createInterface() {

        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        // Main Content
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);

        add(contentPanel, BorderLayout.CENTER);

        showDashboard();
    }

    // ================= SIDEBAR =================

    private JPanel createSidebar() {

        JPanel sidebar = new JPanel();

        sidebar.setPreferredSize(new Dimension(230, 720));
        sidebar.setBackground(SIDEBAR_COLOR);

        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // Logo
        JLabel logo = new JLabel(
                "<html><b>Scholarship</b><br>Management</html>"
        );

        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 22));

        logo.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 25, 30, 10
                )
        );

        sidebar.add(logo);

        // Menu buttons
        addMenuButton(
                sidebar,
                "Dashboard",
                e -> showDashboard()
        );

        addMenuButton(
                sidebar,
                "New Application",
                e -> showPanel(new ApplicationPanel(service))
        );

        addMenuButton(
                sidebar,
                "Scholarship Schemes",
                e -> showPanel(new SchemePanel(service))
        );

        addMenuButton(
                sidebar,
                "Search Application",
                e -> showPanel(new SearchPanel(service))
        );

        addMenuButton(
                sidebar,
                "Approvals",
                e -> showPanel(new ApprovalPanel(service))
        );

        addMenuButton(
                sidebar,
                "Reports",
                e -> showPanel(new ReportPanel(service))
        );

        return sidebar;
    }

    // ================= MENU BUTTON =================

    private void addMenuButton(
            JPanel sidebar,
            String text,
            java.awt.event.ActionListener action
    ) {

        JButton button = new JButton(text);

        button.setMaximumSize(
                new Dimension(210, 48)
        );

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setForeground(Color.WHITE);
        button.setBackground(SIDEBAR_COLOR);

        button.setFont(
                new Font("SansSerif", Font.PLAIN, 15)
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addActionListener(action);

        sidebar.add(button);

        sidebar.add(
                Box.createVerticalStrut(8)
        );
    }

    // ================= DASHBOARD =================

    private void showDashboard() {

        JPanel dashboard =
                new JPanel(new BorderLayout(20, 20));

        dashboard.setBackground(BACKGROUND_COLOR);

        dashboard.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 30, 30, 30
                )
        );

        // Title
        JLabel title =
                new JLabel(
                        "Student Scholarship Dashboard"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(TEXT_COLOR);

        dashboard.add(
                title,
                BorderLayout.NORTH
        );

        // Center
        JPanel center =
                new JPanel(
                        new BorderLayout(20, 20)
                );

        center.setOpaque(false);

        // Cards
        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                20
                        )
                );

        cards.setOpaque(false);

        int total =
                service.getApplications().size();

        int approved = 0;
        int pending = 0;
        int rejected = 0;

        for (var application :
                service.getApplications()) {

            if (application
                    .getStatus()
                    .equals("Approved")) {

                approved++;

            } else if (application
                    .getStatus()
                    .equals("Pending")) {

                pending++;

            } else if (application
                    .getStatus()
                    .equals("Rejected")) {

                rejected++;
            }
        }

        createCard(
                cards,
                "Total Applications",
                String.valueOf(total)
        );

        createCard(
                cards,
                "Approved",
                String.valueOf(approved)
        );

        createCard(
                cards,
                "Pending",
                String.valueOf(pending)
        );

        center.add(
                cards,
                BorderLayout.NORTH
        );

        // Welcome section
        JPanel welcomePanel =
                new JPanel(
                        new BorderLayout()
                );

        welcomePanel.setBackground(Color.WHITE);

        welcomePanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 228, 238)
                        ),
                        BorderFactory.createEmptyBorder(
                                30, 30, 30, 30
                        )
                )
        );

        JLabel welcome =
                new JLabel(
                        "<html>" +
                        "<h2>Welcome to Scholarship Management</h2>" +
                        "<p>" +
                        "Manage student applications, " +
                        "check eligibility, approve scholarships " +
                        "and generate useful reports." +
                        "</p>" +
                        "</html>"
                );

        welcome.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        16
                )
        );

        welcomePanel.add(
                welcome,
                BorderLayout.CENTER
        );

        center.add(
                welcomePanel,
                BorderLayout.CENTER
        );

        dashboard.add(
                center,
                BorderLayout.CENTER
        );

        contentPanel.removeAll();

        contentPanel.add(
                dashboard,
                BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // ================= CARD =================

    private void createCard(
            JPanel parent,
            String title,
            String value
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 228, 238)
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 20, 20, 20
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        32
                )
        );

        valueLabel.setForeground(
                PRIMARY_COLOR
        );

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        parent.add(card);
    }

    // ================= CHANGE PANEL =================

    private void showPanel(JPanel panel) {

        contentPanel.removeAll();

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}