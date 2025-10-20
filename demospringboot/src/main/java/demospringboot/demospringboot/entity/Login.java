package demospringboot.demospringboot.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Login {
    
    // Variables

    private String username;
    private String password;

    // Constructor

    public Login(){

    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // Abstract methods untuk pesan sukses login 
    public abstract String loginSuccessMessage();

    // Abstract methods untuk pesan gagal login 
    public abstract String loginFailureMessage();
    
}