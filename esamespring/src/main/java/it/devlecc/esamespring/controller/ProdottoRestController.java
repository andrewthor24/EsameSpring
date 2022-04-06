package it.devlecc.esamespring.controller;

import it.devlecc.esamespring.persistenza.ProdottoRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdottoRestController {
    private final ProdottoRepository repository;

    ProdottoRestController(ProdottoRepository repository){
        this.repository=repository;
    }

}
