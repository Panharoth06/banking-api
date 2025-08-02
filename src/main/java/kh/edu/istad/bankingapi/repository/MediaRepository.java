package kh.edu.istad.bankingapi.repository;

import kh.edu.istad.bankingapi.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    Optional<Media> findByName(String name);

}
