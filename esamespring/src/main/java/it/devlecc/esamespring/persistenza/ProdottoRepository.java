package it.devlecc.esamespring.persistenza;

import it.devlecc.esamespring.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
}
