package demospringboot.demospringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "voting")
public class Voting {

    // Variables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "nim", nullable = false)
    private int nim;

    @Column(name = "pilihan", nullable = false)
    private String pilihan;


    // Constructor
    
    public Voting() {
    }

    public Voting(String username, int nim, String pilihan) {
        this.username = username;
        this.nim = nim;
        this.pilihan = pilihan;
    }


    // Setters and Getters

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public int getNim() {
        return nim;
    }
    
    public void setPilihan(String pilihan) {
        this.pilihan = pilihan;
    }

    public String getPilihan() {
        return pilihan;
    }

}