package pl.coderslab.entity;

public class User {
    private int id;
    private String email, userName, password;

    public User () {
    }

    public User(int id, String email, String userName, String password) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User");
        sb.append(" id = ").append(Color.BLUE).append(id).append(Color.RESET);
        sb.append(" | email = '").append(Color.BLUE).append(email).append(Color.RESET).append('\'');
        sb.append(" | userName = '").append(Color.BLUE).append(userName).append(Color.RESET).append('\'');
        sb.append(" | password = '").append(Color.BLUE).append(password).append(Color.RESET).append('\'');
        return sb.toString();
    }

}
