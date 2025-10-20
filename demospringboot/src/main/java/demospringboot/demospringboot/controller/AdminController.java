package demospringboot.demospringboot.controller;

import demospringboot.demospringboot.entity.Admin;
import demospringboot.demospringboot.entity.Kandidat;
import demospringboot.demospringboot.entity.Persentase;
import demospringboot.demospringboot.service.AdminService;
import demospringboot.demospringboot.service.HasilVotingService;
import demospringboot.demospringboot.service.KandidatService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private KandidatService kandidatService;

    @Autowired
    private HasilVotingService hasilVotingService;

    // Menampilkan halaman login admin
    @GetMapping("/admin/login")
    public String adminLoginPage(Model model) {
        return "admin_login"; // Mengembalikan view "admin_login"
    }

    // Memproses form login admin dan mengarahkan ke dashboard jika berhasil
    @PostMapping(value = "/admin/login/submit")
    public String adminLoginSubmit(Model model, @ModelAttribute("adminLoginInfo") Admin adminLoginInfo,
            RedirectAttributes redirectAttributes) {
        Admin admin = adminService.findAdmin(adminLoginInfo.getUsername(), adminLoginInfo.getPassword());
        if (admin != null) {
            redirectAttributes.addFlashAttribute("successMessage", admin.loginSuccessMessage());
            return "redirect:/admin_dashboard"; // Arahkan ke halaman dashboard admin
        } else {
            model.addAttribute("error", adminLoginInfo.loginFailureMessage());
            return "admin_login"; // Kembali ke halaman login admin
        }
    }

    // Menampilkan halaman dashboard admin
    @GetMapping("/admin_dashboard")
    public String kandidatPage(@RequestParam(value = "id", required = false) Long id, Model model) {
        // untuk list kandidat
        model.addAttribute("kanList", kandidatService.getAllKandidats());

        // buat edit sama nambahin data baru
        Kandidat kandidat;
        if (id != null) {
            kandidat = kandidatService.getKandidatById(id);
            if (kandidat == null) {
                return "redirect:/admin_dashboard"; // Mengedit kandidat jika ID ada
            }
        } else {
            kandidat = new Kandidat(); // Membuat kandidat baru jika ID tidak ada
        }
        model.addAttribute("kanInfo", kandidat);

        // Menampilkan hasil voting dan total votes
        List<Persentase> hasilVoting = hasilVotingService.getAllHasilVoting();
        long totalVotes = hasilVotingService.getTotalVotes();
        model.addAttribute("kandidatList", kandidatService.getAllKandidats());
        model.addAttribute("hasilVoting", hasilVoting);
        model.addAttribute("totalVotes", totalVotes);

        return "admin_dashboard"; // Mengembalikan view dashboard admin
    }

    // Menambahkan kandidat baru
    @PostMapping(value = "/kandidat/submit", params = "add")
    public String addKandidat(@ModelAttribute("kanInfo") Kandidat kandidatInfo,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
        // Menyimpan foto kandidat
        if (!file.isEmpty()) {
            try {
                // Mendapatkan nama file
                String fileName = file.getOriginalFilename();
                // Menentukan lokasi untuk menyimpan file
                Path path = Paths.get("src/main/resources/static/image/" + fileName);
                // Menyimpan file ke lokasi
                Files.write(path, file.getBytes());
                // Mengatur nama file ke kandidatInfo
                kandidatInfo.setFotoKandidat(fileName);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Gagal mengupload foto: " + e.getMessage());
                return "redirect:/admin_dashboard";
            }
        }

        // Menambahkan kandidat ke database
        kandidatService.addKan(kandidatInfo);

        redirectAttributes.addFlashAttribute("successMessage", "Kandidat berhasil ditambahkan!");
        return "redirect:/admin_dashboard";
    }

    // Mengedit kandidat yang sudah ada
    @PostMapping(value = "/kandidat/submit", params = "edit")
    public String editKandidat(@ModelAttribute("kanInfo") Kandidat kandidatInfo,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
        // Ambil kandidat lama untuk membandingkan nama
        Kandidat kandidatLama = kandidatService.getKandidatById(kandidatInfo.getId());
        String oldNamaKandidat = kandidatLama.getNamaKandidat();
        String oldFotoKandidat = kandidatLama.getFotoKandidat(); // Ambil foto lama

        // Jika ada file baru yang diunggah, simpan foto baru
        if (!file.isEmpty()) {
            try {
                // Mendapatkan nama file
                String fileName = file.getOriginalFilename();
                // Menentukan lokasi untuk menyimpan file
                Path path = Paths.get("src/main/resources/static/image/" + fileName);
                // Menyimpan file ke lokasi
                Files.write(path, file.getBytes());
                // Mengatur nama file ke kandidatInfo
                kandidatInfo.setFotoKandidat(fileName);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Gagal mengupload foto: " + e.getMessage());
                return "redirect:/admin_dashboard";
            }
        } else {
            // Jika tidak ada file baru, gunakan foto lama
            kandidatInfo.setFotoKandidat(oldFotoKandidat);
        }

        // Update kandidat
        kandidatService.updateKan(kandidatInfo.getId(), kandidatInfo);

        // Jika nama kandidat berubah, update hasil voting
        if (!oldNamaKandidat.equals(kandidatInfo.getNamaKandidat())) {
            hasilVotingService.updateHasilVotingByKandidat(oldNamaKandidat, kandidatInfo.getNamaKandidat());
        }

        // Update hasil voting setelah kandidat diupdate
        hasilVotingService.updateHasilVoting();

        redirectAttributes.addFlashAttribute("successMessage", "Kandidat berhasil diedit!");
        return "redirect:/admin_dashboard";
    }

    // Menghapus kandidat dan data terkait
    @PostMapping(value = "/kandidat/submit", params = "delete")
    public String deleteKandidat(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            // Dapatkan kandidat sebelum dihapus untuk mendapatkan namanya
            Kandidat kandidat = kandidatService.getKandidatById(id);
            if (kandidat != null) {
                // Hapus semua data terkait dengan kandidat (voting dan hasil voting)
                hasilVotingService.deleteKandidatData(kandidat.getNamaKandidat());

                // Hapus data kandidat
                kandidatService.deleteKan(id);

                redirectAttributes.addFlashAttribute("successMessage",
                        "Kandidat dan semua data terkait berhasil dihapus!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Kandidat tidak ditemukan!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Gagal menghapus kandidat: " + e.getMessage());
        }
        return "redirect:/admin_dashboard";
    }

}