package model;

public class SaveChecker {
    boolean saveStatus;

    public SaveChecker() {
        this.saveStatus = true;
    }

    public boolean getSaveStats() {
        if (this.saveStatus == true) {
            return true;
        } else {
            return false;
        }
    }

    public void setSaveStatus(boolean status) {
        this.saveStatus = status;
    }
}
