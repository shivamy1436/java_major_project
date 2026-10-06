package model;

public class ScholarshipApplication {

    private String applicationId;
    private Student student;
    private ScholarshipScheme scheme;
    private Document document;

    private String status;
    private String currentStage;

    private boolean eligible;
    private double awardAmount;
    private String rejectionReason;

    public ScholarshipApplication(
            String applicationId,
            Student student,
            ScholarshipScheme scheme,
            Document document) {

        this.applicationId = applicationId;
        this.student = student;
        this.scheme = scheme;
        this.document = document;

        this.status = "Pending";
        this.currentStage = "Submitted";
        this.eligible = false;
        this.awardAmount = 0;
        this.rejectionReason = "";
    }

    public String getApplicationId() {
        return applicationId;
    }

    public Student getStudent() {
        return student;
    }

    public ScholarshipScheme getScheme() {
        return scheme;
    }

    public Document getDocument() {
        return document;
    }

    public String getStatus() {
        return status;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public boolean isEligible() {
        return eligible;
    }

    public double getAwardAmount() {
        return awardAmount;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public void setAwardAmount(double awardAmount) {
        this.awardAmount = awardAmount;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}