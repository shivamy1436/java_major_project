package service;

import interfaces.EligibilityChecker;
import model.Student;
import model.ScholarshipScheme;

public class EligibilityService implements EligibilityChecker {

    @Override
    public boolean checkEligibility(
            Student student,
            ScholarshipScheme scheme) {

        return student.getCgpa() >= scheme.getMinimumCgpa()
                && student.getFamilyIncome() <= scheme.getMaximumIncome();
    }
}