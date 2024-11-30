public class Students extends Users {
    private String legality;
    private String reason;

    public Students(String user_id, String fullName, String username, String password, String email,  String legality, String reason, String securityQuestion) {
        super(user_id, fullName, username, password, email, securityQuestion);
        this.legality = legality;
        this.reason = reason;
    }
    public Students(String user_id, String fullName, String username, String password, String email, String securityQuestion) {
        super(user_id, fullName, username, password, email ,securityQuestion);
    }

    public Students() {

    }
    String getLegality() {
        return legality;
    }
    String getReason() {
        return reason;
    }
}