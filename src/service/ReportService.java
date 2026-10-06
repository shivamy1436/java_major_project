package service;

import model.ScholarshipApplication;

import java.util.ArrayList;

public class ReportService {

    private ScholarshipService scholarshipService;

    public ReportService(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    public ArrayList<ScholarshipApplication> getApprovedApplications() {

        ArrayList<ScholarshipApplication> result =
                new ArrayList<>();

        for (ScholarshipApplication application :
                scholarshipService.getApplications()) {

            if (application.getStatus().equals("Approved")) {
                result.add(application);
            }
        }

        return result;
    }

    public ArrayList<ScholarshipApplication> getPendingApplications() {

        ArrayList<ScholarshipApplication> result =
                new ArrayList<>();

        for (ScholarshipApplication application :
                scholarshipService.getApplications()) {

            if (application.getStatus().equals("Pending")) {
                result.add(application);
            }
        }

        return result;
    }

    public double getTotalAwardAmount() {

        double total = 0;

        for (ScholarshipApplication application :
                scholarshipService.getApplications()) {

            total += application.getAwardAmount();
        }

        return total;
    }
}