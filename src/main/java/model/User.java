package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class User {
    private String username;
    private String password;
    private String realName;
    private LocalDate dob;

    public User(String username, String password, String realName, String dob) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            this.dob = LocalDate.parse(dob, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date must be valid and in MM/DD/YYYY format.");
        }
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public String getdob() {
        return dob.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void changeRealName(String newRealName) {
        realName = newRealName;
    }

    public void changedob(String newdob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            this.dob = LocalDate.parse(newdob, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date must be valid and in MM/DD/YYYY format.");
        }
    }

    public <T extends Likable> void like(T target) {
        target.addLike();
    }
    public <T extends Likable> void unlike(T target) {
        target.removeLike();
    }


}
