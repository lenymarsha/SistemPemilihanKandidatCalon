package demospringboot.demospringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pemilih")
public class Pemilih extends Login {

    // Variables

    @Id
    @Column(name = "nim")
    private int nim;

    // Constructor

    public Pemilih() {
        super("", "");
    }

    public Pemilih(String username, int nim, String password) {
        super(username, password);
        this.nim = nim;
    }

    // Setter and Getter

    public void setNim(int nim) {
        this.nim = nim;
    }

    public int getNim() {
        return nim;
    }

    // Abstract Method

    @Override
    public String loginSuccessMessage() {
        return "Login successful! Welcome back " + getUsername();
    }

    @Override
    public String loginFailureMessage() {
        return "User not found. Please sign up first.";
    }

}