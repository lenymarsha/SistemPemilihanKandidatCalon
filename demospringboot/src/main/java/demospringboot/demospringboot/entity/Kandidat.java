// Kandidat.java
package demospringboot.demospringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kandidat")
public class Kandidat {

    // Variables 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_kandidat")
    private String namaKandidat;

    @Column(name = "visi")
    private String visi;

    @Column(name = "misi", length = 512)
    private String misi;

    @Column(name = "slogan_kandidat")
    private String sloganKandidat;

    @Column(name = "foto_kandidat")
    private String fotoKandidat;

    // constructor

    public Kandidat() {
    }

    public Kandidat(String namaKandidat, String visi, String misi, String sloganKandidat, String fotoKandidat) {
        this.namaKandidat = namaKandidat;
        this.visi = visi;
        this.misi = misi;
        this.sloganKandidat = sloganKandidat;
        this.fotoKandidat = fotoKandidat;
    }

    // Getters and Setters
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNamaKandidat(String namaKandidat) {
        this.namaKandidat = namaKandidat;
    }

    public String getNamaKandidat() {
        return namaKandidat;
    }

    public void setVisi(String visi) {
        this.visi = visi;
    }

    public String getVisi() {
        return visi;
    }

    public void setMisi(String misi) {
        this.misi = misi;
    }

    public String getMisi() {
        return misi;
    }
    
    public void setSloganKandidat(String sloganKandidat) {
        this.sloganKandidat = sloganKandidat;
    }

    public String getSloganKandidat() {
        return sloganKandidat;
    }

    public void setFotoKandidat(String fotoKandidat) {
        this.fotoKandidat = fotoKandidat;
    }

    public String getFotoKandidat() {
        return fotoKandidat;
    }

}
