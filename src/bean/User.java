package bean;

public class User {
    private String username;
    private String password;
    private int identity;

    public User(String username, String password, int identity) {
        this.username = username;
        this.password = password;
        this.identity = identity;
    }

    public int getIdentity() {
        return identity;
    }

    public User() {
        username = null;
        password = null;
        identity = 0;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)return true;
        if(obj == null || getClass() != obj.getClass())return false;
        User u = (User) obj;
        return username.equals(u.username) && password.equals(u.password);
    }

    @Override
    public String toString() {
        return username + "&" + password;
    }
}
