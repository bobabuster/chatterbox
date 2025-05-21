public class User {
    private String username;
    private String password;
    private String realName;
    private String dob;

    public User(String username, String password, String realName, String dob) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public String getRealName() {
        return realName;
    }

    public String getdob() {
        return dob;
    } // date is formatted MM/DD/YYYY

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
        dob = newdob;
    }

    public <T extends Likable> void like(T target) {
        target.addLike();
    }


}
