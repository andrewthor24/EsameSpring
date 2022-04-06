package it.devlecc.esamespring.persistenza;

import it.devlecc.esamespring.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findByDataAcquistoBetween(Date datada, Date dataa);
    List<Prodotto> findByPrezzoBetween(float minimo, float max);
    List<Prodotto> findByPrezzoLessThan(float max);
}
