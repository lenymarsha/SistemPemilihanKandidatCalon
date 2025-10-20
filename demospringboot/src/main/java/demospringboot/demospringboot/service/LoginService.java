package demospringboot.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demospringboot.demospringboot.entity.Pemilih;
import demospringboot.demospringboot.repository.LoginRepository;
import java.util.List;

@Service
// Menandai kelas ini sebagai service Spring untuk menangani logika bisnis
// login.
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;
    // Menginject LoginRepository untuk mengakses data dari database.

    public List<Pemilih> getAllLogins() {
        // Mengambil semua data pemilih dari tabel login.
        return loginRepository.findAll();
    }

    public Pemilih addLogin(Pemilih pemilih) {
        // Menambahkan data pemilih baru ke tabel login.
        return loginRepository.save(pemilih);
    }

    public boolean signUp(Pemilih pemilih) {
        // Mengecek apakah username dan NIM sudah ada di database.
        if (loginRepository.existsByUsernameAndNim(pemilih.getUsername(), pemilih.getNim())) {
            return false; // Gagal mendaftar karena username sudah terdaftar.
        }
        // Jika belum ada, simpan data pemilih baru ke database.
        loginRepository.save(pemilih);
        return true; // Berhasil mendaftar.
    }

    public Pemilih findUser(String username, int nim, String password) {
        // Mencari pemilih berdasarkan username, NIM, dan password.
        // Digunakan untuk proses autentikasi login.
        return loginRepository.findByUsernameAndNimAndPassword(username, nim, password);
    }
}
