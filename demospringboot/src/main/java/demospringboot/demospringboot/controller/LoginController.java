package demospringboot.demospringboot.controller;

import demospringboot.demospringboot.entity.Pemilih;
import demospringboot.demospringboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    // Menampilkan halaman login dengan form login yang terkait dengan objek Pemilih
    @GetMapping("/login")
    public String loginPage(Model model) {
        // Menambahkan objek 'loginInfo' dari kelas Pemilih ke model untuk form login
        model.addAttribute("loginInfo", new Pemilih());
        return "login"; // Mengembalikan view "login"
    }

    // Memproses login pengguna berdasarkan username, nim, dan password
    @PostMapping(value = "/login/submit")
    public String loginSubmit(Model model, @ModelAttribute("loginInfo") Pemilih loginInfo,
                              RedirectAttributes redirectAttributes, HttpSession session) {
        // Mencari user berdasarkan username dan password yang dimasukkan
        Pemilih user = loginService.findUser(loginInfo.getUsername(), loginInfo.getNim(), loginInfo.getPassword());

        // Jika user ditemukan, arahkan ke halaman kandidat
        if (user != null) {

            // Simpan data user ke session
            session.setAttribute("loggedInUsername", user.getUsername());
            session.setAttribute("loggedInNim", user.getNim());
            
            redirectAttributes.addFlashAttribute("successMessage", user.loginSuccessMessage());
            return "redirect:/kandidat";
        } else {
            // Jika login gagal, tambahkan pesan error dan tetap di halaman login
            model.addAttribute("error", loginInfo.loginFailureMessage());
            return "login";
        }
    }

    // Menampilkan halaman signup dengan form signup yang terkait dengan objek Pemilih
    @GetMapping("/signup")
    public String signUpPage(Model model) {
        // Menambahkan objek 'signUpInfo' dari kelas Pemilih ke model untuk form signup
        model.addAttribute("signUpInfo", new Pemilih());
        return "signup"; // Mengembalikan view "signup"
    }

    // Memproses pendaftaran pengguna baru
    @PostMapping(value = "/signup/submit")
    public String signUpSubmit(Model model, @ModelAttribute("signUpInfo") Pemilih signUpInfo) {
        // Memproses pendaftaran pengguna baru melalui loginService
        if (loginService.signUp(signUpInfo)) {
            return "redirect:/login";
        }
        // Jika username sudah ada, tambahkan pesan error dan tetap di halaman signup
        model.addAttribute("error", "Username already exists. Please choose another.");
        return "signup";
    }
    
}