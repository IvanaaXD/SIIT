package rs.ac.uns.ftn.wines.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.wines.domain.Wine;

import java.util.List;
import java.util.Optional;

@Repository
public interface WineRepository extends CrudRepository<Wine, Long> {

    List<Wine> findAll();

    Optional<Wine> findById(Long id);
}
