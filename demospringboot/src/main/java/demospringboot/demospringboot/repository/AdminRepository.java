package demospringboot.demospringboot.repository;

import demospringboot.demospringboot.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository untuk entittas Admin
// Bertanggung jawab mengelola akses data ke database untuk entitas Admin
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    // Method ini akan mencari Admin yang memiliki username dan password yang cocok
    Admin findByUsernameAndPassword(String username, String password);
}