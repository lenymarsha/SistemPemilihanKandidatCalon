package demospringboot.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demospringboot.demospringboot.entity.HasilVoting;
import demospringboot.demospringboot.entity.Kandidat;
import demospringboot.demospringboot.repository.HasilVotingRepository;
import demospringboot.demospringboot.repository.KandidatRepository;

import java.util.List;

@Service
// Menandai kelas ini sebagai service Spring untuk logika bisnis terkait
// kandidat.
public class KandidatService {

    @Autowired
    private KandidatRepository kandidatRepository;
    // Menginjeksi repository untuk akses data kandidat.

    @Autowired
    private HasilVotingRepository hasilVotingRepository;
    // Menginjeksi repository untuk akses data hasil voting.

    public KandidatService(KandidatRepository kandidatRepository) {
        this.kandidatRepository = kandidatRepository;
        // Constructor untuk inisialisasi KandidatRepository.
    }

    public Kandidat getKandidatById(Long id) {
        // Mendapatkan kandidat berdasarkan ID. Jika tidak ditemukan, return null.
        return kandidatRepository.findById(id).orElse(null);
    }

    public List<Kandidat> getAllKandidats() {
        // Mengambil semua kandidat dari database.
        return kandidatRepository.findAll();
    }

    public Kandidat addKan(Kandidat kan) {
        // Menambahkan kandidat baru ke database.
        Kandidat savedKandidat = kandidatRepository.save(kan);

        // Jika nama kandidat belum ada di tabel hasil voting, tambahkan dengan nilai
        // awal.
        if (!hasilVotingRepository.existsByNamaKandidat(kan.getNamaKandidat())) {
            HasilVoting hasilVoting = new HasilVoting();
            hasilVoting.setNamaKandidat(kan.getNamaKandidat()); // Set nama kandidat.
            hasilVoting.setJumlahSuara(0L); // Set jumlah suara awal = 0.
            hasilVoting.setPersentase(0.0); // Set persentase awal = 0.0.
            hasilVotingRepository.save(hasilVoting); // Simpan ke tabel hasil voting.
        }

        return savedKandidat; // Kembalikan kandidat yang sudah disimpan.
    }

    public Kandidat updateKan(Long id, Kandidat kan) {
        // Mengupdate data kandidat berdasarkan ID.
        kan.setId(id); // Set ID kandidat yang ingin diupdate.
        return kandidatRepository.save(kan); // Simpan perubahan ke database.
    }

    public void deleteKan(Long id) {
        // Menghapus kandidat dari database berdasarkan ID.
        kandidatRepository.deleteById(id);
    }

    // Optional: Method tambahan untuk menangani ID bertipe int.
    public Kandidat getKandidatById(int id) {
        return getKandidatById(Long.valueOf(id));
        // Mengonversi int ke Long lalu memanggil method `getKandidatById(Long)`.
    }
}
