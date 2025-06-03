package dev.bobabuster.chatterbox.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UpdateDobRequest {
    private String dob;

    public UpdateDobRequest() {}
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(dob, formatter);
            this.dob = parsedDate.format(formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date must be valid and in MM/DD/YYYY format.");
        }
    }
}
