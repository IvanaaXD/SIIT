package rs.ac.uns.ftn.wines.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import rs.ac.uns.ftn.wines.domain.Wine;

import java.util.List;
import java.util.Optional;

public interface WineService {

    Page<Wine> getAll(Pageable page);

    Optional<Wine> getWine(String id);
    void add(Wine wine);

}
