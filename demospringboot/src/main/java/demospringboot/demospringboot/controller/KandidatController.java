package demospringboot.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import demospringboot.demospringboot.entity.Kandidat;
import demospringboot.demospringboot.entity.Voting;
import demospringboot.demospringboot.service.HasilVotingService;
import demospringboot.demospringboot.service.KandidatService;
import demospringboot.demospringboot.service.VotingService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SuppressWarnings("unused")
@Controller
public class KandidatController {

    @Autowired
    private KandidatService kandidatService; // Menginject KandidatService untuk mengelola data kandidat.

    @Autowired
    private VotingService votingService; // Menginject VotingService untuk memproses data terkait voting.

    @Autowired
    private HasilVotingService hasilVotingService; // Menginject HasilVotingService untuk memperbarui hasil voting.

    
    private static final String UPLOAD_DIR = "src/main/resources/static/images/"; // Lokasi penyimpanan file gambar kandidat.

    // Menampilkan semua kandidat di halaman kandidat.
    @GetMapping("/kandidat")
    public String showKandidats(Model model) {
        List<Kandidat> kandidats = kandidatService.getAllKandidats(); // Mengambil semua kandidat
        model.addAttribute("kandidats", kandidats); // Menambahkan data kandidat ke model
        return "kandidat"; // Halaman untuk menampilkan kandidat
    }

    // Menampilkan detail kandidat berdasarkan ID
    @GetMapping("/kandidat/{id}")
    public String showKandidatDetail(@PathVariable("id") long id, Model model) {
        Kandidat kandidat = kandidatService.getKandidatById(id); // Mengambil kandidat berdasarkan ID
        if (kandidat == null) {
            return "redirect:/kandidat"; // Redirect jika kandidat tidak ditemukan
        }

        model.addAttribute("kandidat", kandidat); // Menambahkan data kandidat ke model untuk ditampilkan.

        switch ((int) id) { // Menentukan halaman berdasarkan ID kandidat
            case 1:
                return "kandidat1";
            case 2:
                return "kandidat2";
            case 3:
                return "kandidat3";
            default:
                return "kandidat-default";
        }
    }

    // Memproses pengajuan vote oleh pengguna
    @PostMapping("/vote")
    public String submitVote(@ModelAttribute Voting voting, Model model,
    @RequestParam("username") String username,
    @RequestParam("pilihan") String pilihan) { 
        if (!votingService.voteCheck(voting)) { // Memeriksa apakah pengguna sudah pernah vote
            return "voting-sudah"; // Halaman jika sudah voting
        } else {
            // Buat objek Voting baru
            Voting vote = new Voting();
            voting.setUsername(username); // Menambahkan username ke objek Voting
            voting.setPilihan(pilihan); // Menambahkan pilihan kandidat
            // Simpan voting
            votingService.save(voting);

            // Perbarui hasil voting
            hasilVotingService.updateHasilVoting();
            return "voting-success"; // Halaman jika voting berhasil
        }
    }
}