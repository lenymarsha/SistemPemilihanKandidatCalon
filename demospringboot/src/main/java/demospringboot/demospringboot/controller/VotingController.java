package demospringboot.demospringboot.controller;

import demospringboot.demospringboot.entity.Voting;
// import demospringboot.demospringboot.entity.Pemilih;
import demospringboot.demospringboot.service.KandidatService;
import demospringboot.demospringboot.service.VotingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class VotingController {

    @Autowired
    private KandidatService kandidatService;

    @Autowired
    private VotingService votingService;

    /*
    Menampilkan form voting.
    Mengambil data username dan NIM dari session, menambahkan ke model,
    serta memuat daftar kandidat dan objek Voting baru.
    */
    @GetMapping("/voting-form")
    public String showVotingForm(Model model, HttpSession session) {
        // Ambil data dari session
        String username = (String) session.getAttribute("loggedInUsername");
        Integer nim = (Integer) session.getAttribute("loggedInNim");

        // Tambahkan ke model
        model.addAttribute("username", username);
        model.addAttribute("nim", nim);
        model.addAttribute("kandidats", kandidatService.getAllKandidats());

        // Tambahkan objek Voting baru ke model
        model.addAttribute("votingInfo", new Voting());
        return "voting-form";
    }

    // Mengirimkan data voting yang diisi oleh pengguna
    // Data username dan NIM diambil dari session, lalu voting disimpan melalui VotingService
    @PostMapping("/voting-form")
    public String submitVote(@ModelAttribute Voting voting, HttpSession session) {
        // Pastikan username dan nim diisi dari session
        String username = (String) session.getAttribute("loggedInUsername");
        Integer nim = (Integer) session.getAttribute("loggedInNim");
        
        voting.setUsername(username);
        voting.setNim(nim);
        
        votingService.saveVote(voting);
        return "redirect:/voting-success";
    }
    
    // Menampilkan halaman sukses voting
    @GetMapping("/voting-success")
    public String showVotingSuccess() {
        return "voting-success"; // A separate page for voting success
    }

}