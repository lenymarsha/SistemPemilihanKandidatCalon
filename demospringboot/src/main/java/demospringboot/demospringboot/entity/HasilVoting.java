package demospringboot.demospringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hasil_voting")
public class HasilVoting implements Persentase {

    // Variables 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nama_kandidat")
    private String namaKandidat;

    @Column(name = "jumlah_suara")
    private Long jumlahSuara;

    @Column(name = "persentase")
    private Double persentase;

    // Constructor

    public HasilVoting() {
    }
    
    public HasilVoting(String namaKandidat, Long jumlahSuara, Double persentase) {
        this.namaKandidat = namaKandidat;
        this.jumlahSuara = jumlahSuara;
        this.persentase = persentase;
    }

    // Setters and Getters

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
    
    public void setJumlahSuara(long jumlahSuara) {
        this.jumlahSuara = jumlahSuara;
    }

    public long getJumlahSuara() {
        return jumlahSuara;
    }
    
    public void setPersentase(Double persentase) {
        this.persentase = persentase;
    }

    public Double getPersentase() {
        return persentase;
    }

    @Override
    public Double hitungPersentase(long totalVotes) {
        return totalVotes > 0 ? (jumlahSuara * 100.0) / totalVotes : 0.0;
    }

}