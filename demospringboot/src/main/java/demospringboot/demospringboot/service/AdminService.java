package demospringboot.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demospringboot.demospringboot.entity.Admin;
import demospringboot.demospringboot.repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    // Method untuk mencari admin berdasarkan username dan password.
    public Admin findAdmin(String username, String password) {
        // Memanggil query otomatis dari Spring Data JPA di AdminRepository.
        return adminRepository.findByUsernameAndPassword(username, password);
    }
}