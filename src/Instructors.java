public class Instructors extends Users{
    private final String experience;
    private final String topicPreferences;
    public Instructors(String user_id, String fullName, String username, String password, String email, String experience, String topicPreferences, String securityQuestion) {
        super(user_id, fullName, username, password, email, securityQuestion);
        this.experience = experience;
        this.topicPreferences = topicPreferences;
    }

    public String getExperience() {
        return this.experience;
    }
    public String getTopicPreferences() {
        return this.topicPreferences;
    }
}
