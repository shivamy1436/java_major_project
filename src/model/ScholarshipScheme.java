package model;

public class ScholarshipScheme {

    private String schemeId;
    private String schemeName;
    private double minimumCgpa;
    private double maximumIncome;
    private double awardAmount;

    public ScholarshipScheme(
            String schemeId,
            String schemeName,
            double minimumCgpa,
            double maximumIncome,
            double awardAmount) {

        this.schemeId = schemeId;
        this.schemeName = schemeName;
        this.minimumCgpa = minimumCgpa;
        this.maximumIncome = maximumIncome;
        this.awardAmount = awardAmount;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public double getMinimumCgpa() {
        return minimumCgpa;
    }

    public double getMaximumIncome() {
        return maximumIncome;
    }

    public double getAwardAmount() {
        return awardAmount;
    }

    @Override
    public String toString() {
        return schemeName;
    }
}