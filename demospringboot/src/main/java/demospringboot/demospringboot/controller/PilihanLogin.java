package demospringboot.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PilihanLogin {

    // Menangani permintaan HTTP GET pada URL root ("/")
    // dan mengarahkan ke halaman "pilihan_login".
    @GetMapping("/")
    public String showLoginSelection() {
        return "pilihan_login";
    }
}
