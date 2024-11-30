public class Users {
    private String user_id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String securityQuestion;

    public Users(String user_id, String fullName, String username, String password, String email, String securityQuestion) {
        this.user_id = user_id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.securityQuestion = securityQuestion;
    }

    public Users() {

    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getSecurityQuestion() {
        return this.securityQuestion;
    }
}
