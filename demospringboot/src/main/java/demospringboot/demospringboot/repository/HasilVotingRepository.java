// HasilVotingRepository.java
package demospringboot.demospringboot.repository;

import demospringboot.demospringboot.entity.HasilVoting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HasilVotingRepository extends JpaRepository<HasilVoting, Long> {
    // untuk cek hasilvoting berdasarkan nama kandidat
    HasilVoting findByNamaKandidat(String namaKandidat); 

    // untuk cek apakah namanya udah ada atau belum di hasil voting
    boolean existsByNamaKandidat(String namaKandidat);
}