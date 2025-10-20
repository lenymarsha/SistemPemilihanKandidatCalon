package demospringboot.demospringboot.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends Login {

    // Variables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    
    // Constructor

    public Admin() {
        super("", "");
    }

    public Admin(String username, String password) {
        super(username, password);
        this.username = username;
        this.password = password;
    }


    // Setters and Getters

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
        super.setUsername(username);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
        super.setPassword(password);
    }


    // Abstract Method

    @Override
    public String loginSuccessMessage() {
        return "Login successful! Welcome back admin" + this.username;
    }

    @Override
    public String loginFailureMessage() {
        return "User not found. Please check again your username or password.";
    }
}