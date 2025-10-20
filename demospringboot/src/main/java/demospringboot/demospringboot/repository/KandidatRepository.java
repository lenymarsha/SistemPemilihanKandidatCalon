package demospringboot.demospringboot.repository;

import demospringboot.demospringboot.entity.Kandidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KandidatRepository extends JpaRepository<Kandidat, Long> {
}