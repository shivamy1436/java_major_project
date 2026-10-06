package interfaces;

import model.Student;
import model.ScholarshipScheme;

public interface EligibilityChecker {

    boolean checkEligibility(
            Student student,
            ScholarshipScheme scheme);
}