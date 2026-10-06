package interfaces;

import model.ScholarshipApplication;

public interface ApplicationManager {

    void addApplication(ScholarshipApplication application);

    ScholarshipApplication searchApplication(String applicationId);
}