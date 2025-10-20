package demospringboot.demospringboot.repository;

import demospringboot.demospringboot.entity.Pemilih;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface LoginRepository extends JpaRepository<Pemilih, Integer> {
    // Method untuk mengecek apakah ada pengguna dengan username dan NIM tertentu
    boolean existsByUsernameAndNim(String username, int nim);

    // Method untuk mencari pengguna berdasarkan username, NIM, dan password
    Pemilih findByUsernameAndNimAndPassword(String username, int nim, String password);
}