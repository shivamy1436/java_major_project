import gui.DashboardFrame;
import service.ScholarshipService;
import model.Student;
import model.Document;
import model.ScholarshipScheme;
import model.ScholarshipApplication;
import exception.InvalidApplicationException;
import exception.EligibilityException;

import javax.swing.SwingUtilities;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // CREATE MAIN SERVICE
        // ==========================================

        ScholarshipService service =
                new ScholarshipService();

        // ==========================================
        // START GUI
        // ==========================================

        SwingUtilities.invokeLater(() -> {

            DashboardFrame dashboard =
                    new DashboardFrame(service);

            dashboard.setVisible(true);
        });

        // ==========================================
        // START TERMINAL
        // ==========================================

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("==============================================");
        System.out.println("   STUDENT SCHOLARSHIP PROCESSING SYSTEM");
        System.out.println("==============================================");
        System.out.println();
        System.out.println("GUI started successfully.");
        System.out.println("Terminal control panel is also ready.");
        System.out.println();

        boolean running = true;

        while (running) {

            showMenu();

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            System.out.println();

            switch (choice) {

                case "1":
                    showDashboard(service);
                    break;

                case "2":
                    addApplication(service, scanner);
                    break;

                case "3":
                    showScholarshipSchemes(service);
                    break;

                case "4":
                    searchApplication(service, scanner);
                    break;

                case "5":
                    approvalMenu(service, scanner);
                    break;

                case "6":
                    showReports(service);
                    break;

                case "7":
                    showAllApplications(service);
                    break;

                case "8":
                    System.out.println();
                    System.out.println("Opening GUI...");
                    System.out.println(
                            "GUI is already running."
                    );
                    break;

                case "0":
                    running = false;

                    System.out.println();
                    System.out.println(
                            "Thank you for using "
                            + "Student Scholarship System."
                    );

                    break;

                default:
                    System.out.println();
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }

            System.out.println();
        }

        scanner.close();
    }


    // ==================================================
    // TERMINAL MENU
    // ==================================================

    private static void showMenu() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("              MAIN MENU");
        System.out.println("----------------------------------------------");

        System.out.println("1. Dashboard");
        System.out.println("2. New Scholarship Application");
        System.out.println("3. Scholarship Schemes");
        System.out.println("4. Search Application");
        System.out.println("5. Approvals");
        System.out.println("6. Reports");
        System.out.println("7. View All Applications");
        System.out.println("8. Open / Check GUI");
        System.out.println("0. Exit");

        System.out.println("----------------------------------------------");
    }


    // ==================================================
    // 1. DASHBOARD
    // ==================================================

    private static void showDashboard(
            ScholarshipService service) {

        int total = service.getApplications().size();

        int approved = 0;
        int pending = 0;
        int rejected = 0;

        double totalAward = 0;

        for (ScholarshipApplication application :
                service.getApplications()) {

            String status = application.getStatus();

            if (status.equalsIgnoreCase("Approved")) {

                approved++;

                totalAward +=
                        application.getAwardAmount();

            } else if (status.equalsIgnoreCase("Pending")) {

                pending++;

            } else if (status.equalsIgnoreCase("Rejected")) {

                rejected++;
            }
        }

        System.out.println();
        System.out.println("==============================================");
        System.out.println("                 DASHBOARD");
        System.out.println("==============================================");

        System.out.println(
                "Total Applications : " + total
        );

        System.out.println(
                "Approved            : " + approved
        );

        System.out.println(
                "Pending             : " + pending
        );

        System.out.println(
                "Rejected            : " + rejected
        );

        System.out.println(
                "Total Award Amount  : ₹" + totalAward
        );

        System.out.println("==============================================");
    }


    // ==================================================
    // 2. NEW APPLICATION
    // ==================================================

    private static void addApplication(
            ScholarshipService service,
            Scanner scanner) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("        NEW SCHOLARSHIP APPLICATION");
        System.out.println("==============================================");

        System.out.print("Application ID: ");
        String applicationId = scanner.nextLine();

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Student Name: ");
        String studentName = scanner.nextLine();

        System.out.print("Course: ");
        String course = scanner.nextLine();

        System.out.print("Semester: ");
        int semester = readInt(scanner);

        System.out.print("CGPA: ");
        double cgpa = readDouble(scanner);

        System.out.print("Family Income: ");
        double familyIncome = readDouble(scanner);

        System.out.println();
        System.out.println("Available Scholarship Schemes:");

        showScholarshipSchemes(service);

        System.out.print(
                "Enter Scholarship Scheme ID: "
        );

        String schemeId = scanner.nextLine();

        ScholarshipScheme selectedScheme = null;

        for (ScholarshipScheme scheme :
                service.getSchemes()) {

            if (scheme.getSchemeId()
                    .equalsIgnoreCase(schemeId)) {

                selectedScheme = scheme;
                break;
            }
        }

        if (selectedScheme == null) {

            System.out.println(
                    "Invalid Scholarship Scheme ID."
            );

            return;
        }

        System.out.println();
        System.out.println("Document Verification");

        System.out.print(
                "Aadhaar available? (yes/no): "
        );

        boolean aadhaar =
                scanner.nextLine()
                        .equalsIgnoreCase("yes");

        System.out.print(
                "Income Certificate available? (yes/no): "
        );

        boolean incomeCertificate =
                scanner.nextLine()
                        .equalsIgnoreCase("yes");

        System.out.print(
                "Marksheet available? (yes/no): "
        );

        boolean marksheet =
                scanner.nextLine()
                        .equalsIgnoreCase("yes");

        System.out.print(
                "Bank Details available? (yes/no): "
        );

        boolean bankDetails =
                scanner.nextLine()
                        .equalsIgnoreCase("yes");


        Student student =
                new Student(
                        studentId,
                        studentName,
                        course,
                        semester,
                        cgpa,
                        familyIncome
                );


        Document document =
                new Document(
                        aadhaar,
                        incomeCertificate,
                        marksheet,
                        bankDetails
                );


        ScholarshipApplication application =
                new ScholarshipApplication(
                        applicationId,
                        student,
                        selectedScheme,
                        document
                );


        try {

            service.validateApplication(
                    application
            );

            service.addApplication(
                    application
            );

            System.out.println();
            System.out.println(
                    "Application added successfully!"
            );

            System.out.println(
                    "Application ID: "
                    + applicationId
            );

            System.out.println(
                    "Status: "
                    + application.getStatus()
            );

        } catch (InvalidApplicationException e) {

            System.out.println();
            System.out.println(
                    "Application Error: "
                    + e.getMessage()
            );
        }
    }


    // ==================================================
    // 3. SCHOLARSHIP SCHEMES
    // ==================================================

    private static void showScholarshipSchemes(
            ScholarshipService service) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("          SCHOLARSHIP SCHEMES");
        System.out.println("==============================================");

        for (ScholarshipScheme scheme :
                service.getSchemes()) {

            System.out.println();

            System.out.println(
                    "Scheme ID       : "
                    + scheme.getSchemeId()
            );

            System.out.println(
                    "Scheme Name     : "
                    + scheme.getSchemeName()
            );

            System.out.println(
                    "Minimum CGPA    : "
                    + scheme.getMinimumCgpa()
            );

            System.out.println(
                    "Maximum Income  : ₹"
                    + scheme.getMaximumIncome()
            );

            System.out.println(
                    "Award Amount    : ₹"
                    + scheme.getAwardAmount()
            );

            System.out.println(
                    "----------------------------------------------"
            );
        }
    }


    // ==================================================
    // 4. SEARCH APPLICATION
    // ==================================================

    private static void searchApplication(
            ScholarshipService service,
            Scanner scanner) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("           SEARCH APPLICATION");
        System.out.println("==============================================");

        System.out.print(
                "Enter Application ID: "
        );

        String id = scanner.nextLine();

        ScholarshipApplication application =
                service.searchApplication(id);

        if (application == null) {

            System.out.println();
            System.out.println(
                    "Application not found."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "Application Found!"
        );

        System.out.println(
                "Application ID : "
                + application.getApplicationId()
        );

        System.out.println(
                "Student Name   : "
                + application.getStudent().getName()
        );

        System.out.println(
                "Course         : "
                + application.getStudent().getCourse()
        );

        System.out.println(
                "CGPA           : "
                + application.getStudent().getCgpa()
        );

        System.out.println(
                "Scheme         : "
                + application
                        .getScheme()
                        .getSchemeName()
        );

        System.out.println(
                "Status         : "
                + application.getStatus()
        );

        System.out.println(
                "Current Stage  : "
                + application.getCurrentStage()
        );

        System.out.println(
                "Eligible       : "
                + application.isEligible()
        );

        System.out.println(
                "Award Amount   : ₹"
                + application.getAwardAmount()
        );
    }


    // ==================================================
    // 5. APPROVAL MENU
    // ==================================================

    private static void approvalMenu(
            ScholarshipService service,
            Scanner scanner) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("                 APPROVALS");
        System.out.println("==============================================");

        System.out.println("1. Approve Application");
        System.out.println("2. Reject Application");
        System.out.println("3. View Pending Applications");
        System.out.println("0. Back");

        System.out.print("Enter choice: ");

        String choice = scanner.nextLine();

        switch (choice) {

            case "1":

                System.out.print(
                        "Enter Application ID: "
                );

                String approveId =
                        scanner.nextLine();

                try {

                    service.approveApplication(
                            approveId
                    );

                    System.out.println();
                    System.out.println(
                            "Application approved successfully!"
                    );

                } catch (
                        InvalidApplicationException |
                        EligibilityException e) {

                    System.out.println();
                    System.out.println(
                            "Approval Error: "
                            + e.getMessage()
                    );
                }

                break;


            case "2":

                System.out.print(
                        "Enter Application ID: "
                );

                String rejectId =
                        scanner.nextLine();

                System.out.print(
                        "Enter rejection reason: "
                );

                String reason =
                        scanner.nextLine();

                try {

                    service.rejectApplication(
                            rejectId,
                            reason
                    );

                    System.out.println();
                    System.out.println(
                            "Application rejected."
                    );

                } catch (
                        InvalidApplicationException e) {

                    System.out.println();
                    System.out.println(
                            "Rejection Error: "
                            + e.getMessage()
                    );
                }

                break;


            case "3":

                showPendingApplications(service);

                break;


            case "0":

                break;


            default:

                System.out.println(
                        "Invalid choice."
                );
        }
    }


    // ==================================================
    // PENDING APPLICATIONS
    // ==================================================

    private static void showPendingApplications(
            ScholarshipService service) {

        System.out.println();
        System.out.println(
                "========== PENDING APPLICATIONS =========="
        );

        boolean found = false;

        for (ScholarshipApplication application :
                service.getApplications()) {

            if (application
                    .getStatus()
                    .equalsIgnoreCase("Pending")) {

                found = true;

                System.out.println(
                        "ID: "
                        + application.getApplicationId()
                        + " | Student: "
                        + application
                                .getStudent()
                                .getName()
                );
            }
        }

        if (!found) {

            System.out.println(
                    "No pending applications."
            );
        }
    }


    // ==================================================
    // 6. REPORTS
    // ==================================================

    private static void showReports(
            ScholarshipService service) {

        int approved = 0;
        int pending = 0;
        int rejected = 0;

        double totalAward = 0;

        for (ScholarshipApplication application :
                service.getApplications()) {

            String status =
                    application.getStatus();

            if (status.equalsIgnoreCase("Approved")) {

                approved++;

                totalAward +=
                        application.getAwardAmount();

            } else if (
                    status.equalsIgnoreCase("Pending")) {

                pending++;

            } else if (
                    status.equalsIgnoreCase("Rejected")) {

                rejected++;
            }
        }

        System.out.println();
        System.out.println("==============================================");
        System.out.println("                   REPORTS");
        System.out.println("==============================================");

        System.out.println(
                "Total Applications : "
                + service.getApplications().size()
        );

        System.out.println(
                "Approved           : "
                + approved
        );

        System.out.println(
                "Pending            : "
                + pending
        );

        System.out.println(
                "Rejected           : "
                + rejected
        );

        System.out.println(
                "Total Award Given  : ₹"
                + totalAward
        );

        System.out.println("==============================================");
    }


    // ==================================================
    // 7. VIEW ALL APPLICATIONS
    // ==================================================

    private static void showAllApplications(
            ScholarshipService service) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("            ALL APPLICATIONS");
        System.out.println("==============================================");

        if (service.getApplications().isEmpty()) {

            System.out.println(
                    "No applications available."
            );

            return;
        }

        for (ScholarshipApplication application :
                service.getApplications()) {

            System.out.println();

            System.out.println(
                    "Application ID : "
                    + application.getApplicationId()
            );

            System.out.println(
                    "Student        : "
                    + application
                            .getStudent()
                            .getName()
            );

            System.out.println(
                    "Scheme         : "
                    + application
                            .getScheme()
                            .getSchemeName()
            );

            System.out.println(
                    "Status         : "
                    + application.getStatus()
            );

            System.out.println(
                    "Stage          : "
                    + application.getCurrentStage()
            );

            System.out.println(
                    "Award          : ₹"
                    + application.getAwardAmount()
            );

            System.out.println(
                    "----------------------------------------------"
            );
        }
    }


    // ==================================================
    // INTEGER INPUT
    // ==================================================

    private static int readInt(Scanner scanner) {

        while (true) {

            try {

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Please enter a valid number: "
                );
            }
        }
    }


    // ==================================================
    // DOUBLE INPUT
    // ==================================================

    private static double readDouble(
            Scanner scanner) {

        while (true) {

            try {

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Please enter a valid number: "
                );
            }
        }
    }
}