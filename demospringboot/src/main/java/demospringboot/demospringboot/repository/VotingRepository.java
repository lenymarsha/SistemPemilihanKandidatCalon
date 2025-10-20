package demospringboot.demospringboot.repository;

import demospringboot.demospringboot.entity.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {
    
    // Metode untuk menghitung suara berdasarkan pilihan
    long countByPilihan(String pilihan);

    // Metode untuk memeriksa apakah sudah ada suara berdasarkan username atau nim
    boolean existsByUsernameAndNim(String username, int nim);

    // untuk menghapus data berdasarkan pilihan
    void deleteByPilihan(String pilihan);

}