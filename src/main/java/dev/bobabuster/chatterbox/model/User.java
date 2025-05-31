package dev.bobabuster.chatterbox.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;


@Entity
public class User implements Identifiable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private long id;

    private String username;
    private String password;
    private String realName;
    private LocalDate dob;

    protected User() {}

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
    public Long getId() { return id; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) {
            return false;
        }

        User other = (User) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }



    public <T extends Likable> void like(T target) {
        target.addLike();
    }
    public <T extends Likable> void unlike(T target) {
        target.removeLike();
    }


}
