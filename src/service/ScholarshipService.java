package service;

import exception.EligibilityException;
import exception.InvalidApplicationException;
import interfaces.ApplicationManager;
import model.*;

import java.util.ArrayList;

public class ScholarshipService implements ApplicationManager {

    private ArrayList<ScholarshipApplication> applications;
    private ArrayList<ScholarshipScheme> schemes;

    private EligibilityService eligibilityService;

    public ScholarshipService() {

        applications = new ArrayList<>();
        schemes = new ArrayList<>();
        eligibilityService = new EligibilityService();

        loadSchemes();
        loadSampleApplications();
    }

    private void loadSchemes() {

        schemes.add(new ScholarshipScheme(
                "SCH001",
                "Merit Scholarship",
                8.0,
                500000,
                50000));

        schemes.add(new ScholarshipScheme(
                "SCH002",
                "Need Based Scholarship",
                6.5,
                300000,
                30000));

        schemes.add(new ScholarshipScheme(
                "SCH003",
                "Academic Excellence",
                9.0,
                600000,
                75000));
    }

    private void loadSampleApplications() {

        Student s1 = new Student(
                "STU001",
                "Rahul Sharma",
                "B.Tech CSE",
                2,
                8.7,
                420000);

        Student s2 = new Student(
                "STU002",
                "Priya Singh",
                "B.Tech IT",
                3,
                9.2,
                450000);

        Document d1 = new Document(true, true, true, true);
        Document d2 = new Document(true, true, true, true);

        ScholarshipApplication a1 =
                new ScholarshipApplication(
                        "APP001",
                        s1,
                        schemes.get(0),
                        d1);

        ScholarshipApplication a2 =
                new ScholarshipApplication(
                        "APP002",
                        s2,
                        schemes.get(2),
                        d2);

        applications.add(a1);
        applications.add(a2);
    }

    @Override
    public void addApplication(
            ScholarshipApplication application) {

        applications.add(application);
    }

    @Override
    public ScholarshipApplication searchApplication(
            String applicationId) {

        for (ScholarshipApplication application : applications) {

            if (application.getApplicationId()
                    .equalsIgnoreCase(applicationId)) {

                return application;
            }
        }

        return null;
    }

    public ArrayList<ScholarshipApplication> getApplications() {
        return applications;
    }

    public ArrayList<ScholarshipScheme> getSchemes() {
        return schemes;
    }

    public void validateApplication(
            ScholarshipApplication application)
            throws InvalidApplicationException {

        if (application == null) {
            throw new InvalidApplicationException(
                    "Application cannot be empty.");
        }

        if (application.getApplicationId() == null
                || application.getApplicationId().trim().isEmpty()) {

            throw new InvalidApplicationException(
                    "Application ID is required.");
        }

        if (application.getStudent() == null) {

            throw new InvalidApplicationException(
                    "Student details are required.");
        }

        if (application.getDocument() == null
                || !application.getDocument().isComplete()) {

            throw new InvalidApplicationException(
                    "All required documents must be submitted.");
        }
    }

    public void approveApplication(
            String applicationId)
            throws InvalidApplicationException,
            EligibilityException {

        ScholarshipApplication application =
                searchApplication(applicationId);

        if (application == null) {

            throw new InvalidApplicationException(
                    "Application not found.");
        }

        validateApplication(application);

        boolean eligible =
                eligibilityService.checkEligibility(
                        application.getStudent(),
                        application.getScheme());

        application.setEligible(eligible);

        if (!eligible) {

            throw new EligibilityException(
                    "Student is not eligible for this scholarship.");
        }

        application.setStatus("Approved");
        application.setCurrentStage("Award Generated");
        application.setAwardAmount(
                application.getScheme().getAwardAmount());
    }

    public void rejectApplication(
            String applicationId,
            String reason)
            throws InvalidApplicationException {

        ScholarshipApplication application =
                searchApplication(applicationId);

        if (application == null) {

            throw new InvalidApplicationException(
                    "Application not found.");
        }

        application.setStatus("Rejected");
        application.setCurrentStage("Completed");
        application.setRejectionReason(reason);
        application.setAwardAmount(0);
    }
}