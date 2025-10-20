package demospringboot.demospringboot.service;

import demospringboot.demospringboot.entity.Voting;
import demospringboot.demospringboot.repository.VotingRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingService {
    @Autowired
    private VotingRepository votingRepository;

    public void save(Voting voting) {
        votingRepository.save(voting);
    }

    public List<Voting> getAllVotings() {
        return votingRepository.findAll();
    }

    // Method untuk menghitung total suara
    public long getTotalVotes() {
        return votingRepository.count();
    }

    // Method untuk menghitung suara per kandidat
    public long getVoteCountForKandidat(String namaKandidat) {
        return votingRepository.countByPilihan(namaKandidat);
    }

    // Method untuk cek apakah sudah pernah vote
    public boolean voteCheck(Voting voting) {
        // Cek apakah sudah pernah vote di database
        if(votingRepository.existsByUsernameAndNim(voting.getUsername(), voting.getNim())){
            return false; // Sudah pernah vote
        }
        
        // Simpan voting
        votingRepository.save(voting);
        return true;
    }

    // Method untuk save data voting
    public void saveVote(Voting voting) {
        votingRepository.save(voting);
    }


}
