package models;

public class Staff {

    private String staffFirstName;
    private String staffSurname;
    private String staffID;
    private String staffPassword;

    public Staff(String staffFirstName, String staffSurname, String staffID, String staffPassword) {
        this.staffFirstName = staffFirstName;
        this.staffSurname = staffSurname;
        this.staffID = staffID;
        this.staffPassword = staffPassword;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffSurname() {
        return staffSurname;
    }

    public void setStaffSurname(String staffSurname) {
        this.staffSurname = staffSurname;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }
}
