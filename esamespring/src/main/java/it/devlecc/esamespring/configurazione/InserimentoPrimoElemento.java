package it.devlecc.esamespring.configurazione;


import it.devlecc.esamespring.model.Prodotto;
import it.devlecc.esamespring.persistenza.ProdottoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InserimentoPrimoElemento {

    private static final Logger logger = LoggerFactory.getLogger(InserimentoPrimoElemento.class);

    @Bean
    CommandLineRunner initDatabase(ProdottoRepository repository) {
        return args -> {
            logger.info("Preloading " + repository.save(new Prodotto("computer",799.99f)));
        };
    }
}
