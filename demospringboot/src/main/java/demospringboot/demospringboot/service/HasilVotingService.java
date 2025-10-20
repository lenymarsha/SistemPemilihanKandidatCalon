// HasilVotingService.java
package demospringboot.demospringboot.service;

import demospringboot.demospringboot.entity.Persentase;
import demospringboot.demospringboot.entity.HasilVoting;
import demospringboot.demospringboot.entity.Kandidat;
import demospringboot.demospringboot.repository.HasilVotingRepository;
import demospringboot.demospringboot.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class HasilVotingService {

    @Autowired
    private HasilVotingRepository hasilVotingRepository;

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private KandidatService kandidatService;

    // Method untuk memperbarui hasil voting berdasarkan kandidat
    public void updateHasilVoting() {
        long totalVotes = votingRepository.count(); // Menghitung total suara
        List<Kandidat> kandidats = kandidatService.getAllKandidats(); // Mendapatkan daftar semua kandidat
    
        // Loop untuk memperbarui hasil voting setiap kandidat
        for (Kandidat kandidat : kandidats) {
            HasilVoting hasil = hasilVotingRepository.findByNamaKandidat(kandidat.getNamaKandidat());
            if (hasil != null) {
                // Menghitung jumlah suara untuk kandidat ini
                long voteCount = votingRepository.countByPilihan(kandidat.getNamaKandidat());
                hasil.setJumlahSuara(voteCount); // Memperbarui jumlah suara
    
                // Menghitung persentase suara
                double percentage = (totalVotes > 0) ? (double) voteCount / totalVotes * 100 : 0;
                hasil.setPersentase(Math.round(percentage * 100.0) / 100.0); // Membulatkan persentase ke dua desimal
    
                hasilVotingRepository.save(hasil); // Menyimpan pembaruan hasil voting
            }
        }
    }
    

    // Method untuk menambah baris baru pada hasil voting jika nama kandidat tidak ada
    public boolean addHasilVoting(HasilVoting hasil) {
        if (hasilVotingRepository.existsByNamaKandidat(hasil.getNamaKandidat())) {
            return false; // Jika kandidat sudah ada, tidak bisa ditambahkan
        }
        hasilVotingRepository.save(hasil); // Menyimpan hasil voting baru
        return true; // Mengembalikan nilai true jika berhasil menambahkan
    }

    // Method untuk menghapus baris pada hasil voting jika nama kandidat tidak ada
    public void deleteHasilVoting(long id) {
        hasilVotingRepository.deleteById(id); // Menghapus hasil voting berdasarkan ID
    }

    // Method baru untuk menghapus hasil voting berdasarkan nama kandidat
    public void deleteHasilVotingByNamaKandidat(String namaKandidat) {
        // Mencari hasil voting berdasarkan nama kandidat
        HasilVoting hasil = hasilVotingRepository.findByNamaKandidat(namaKandidat); 
        if (hasil != null) {
            hasilVotingRepository.delete(hasil); // Menghapus hasil voting jika ditemukan
        }
    }

    // Method yang diperbarui untuk menghapus semua data terkait dengan kandidat
    @Transactional
    public void deleteKandidatData(String namaKandidat) {
        // Hapus data voting
        votingRepository.deleteByPilihan(namaKandidat);

        // Hapus hasil voting
        HasilVoting hasil = hasilVotingRepository.findByNamaKandidat(namaKandidat);
        if (hasil != null) {
            hasilVotingRepository.delete(hasil);
        }
    }

    // Method untuk mendapatkan semua hasil voting dalam bentuk daftar Persentase
    public List<Persentase> getAllHasilVoting() {
        return new ArrayList<>(hasilVotingRepository.findAll()); // Mengembalikan daftar hasil voting
    }

    // Method untuk mendapatkan total jumlah suara dari repository voting
    public long getTotalVotes() {
        return votingRepository.count(); // Mengembalikan total suara
    }

    // Method untuk memperbarui hasil voting berdasarkan perubahan nama kandidat
    public void updateHasilVotingByKandidat(String oldNamaKandidat, String newNamaKandidat) {
        // Hapus hasil voting lama
        deleteHasilVotingByNamaKandidat(oldNamaKandidat);

        // Buat hasil voting baru dengan nama kandidat yang baru
        HasilVoting hasil = new HasilVoting();
        hasil.setNamaKandidat(newNamaKandidat); // Mengatur nama kandidat yang baru
        hasil.setJumlahSuara(0L); // Atur jumlah suara awal
        hasil.setPersentase(0.0); // Atur persentase awal
        hasilVotingRepository.save(hasil); // Menyimpan hasil voting baru
    }
}