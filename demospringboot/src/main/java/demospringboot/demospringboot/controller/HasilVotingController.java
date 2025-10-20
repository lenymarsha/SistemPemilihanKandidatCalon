// HasilVotingController.java
package demospringboot.demospringboot.controller;

import demospringboot.demospringboot.entity.Persentase;
import demospringboot.demospringboot.service.HasilVotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HasilVotingController {

    @Autowired
    private HasilVotingService hasilVotingService;

    // method untuk menangani permintaan http get pada endpoint "/hasil-voting"
    // menampilkan hasil voting dan total suara ke dalam view "hasil-voting"
    @GetMapping("/hasil-voting")
    public String showHasilVoting(Model model) {
        // Mengambil daftar semua hasil voting dari service
        List<Persentase> hasilVoting = hasilVotingService.getAllHasilVoting();

        // Mengambil total jumlah suara yang sudah masuk dari service
        long totalVotes = hasilVotingService.getTotalVotes();

        // Menambahkan data hasilVoting dan totalVotes ke dalam model agar dapat
        // digunakan di view
        model.addAttribute("hasilVoting", hasilVoting); // Menambahkan daftar hasil voting
        model.addAttribute("totalVotes", totalVotes); // Menambhakan total suara

        // Manggil hasi-voting html untuk ditampilkan
        return "hasil-voting";
    }
}