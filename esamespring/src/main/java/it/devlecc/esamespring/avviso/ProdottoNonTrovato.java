package it.devlecc.esamespring.avviso;

public class ProdottoNonTrovato extends RuntimeException{
    public ProdottoNonTrovato(Long id) {
        super("Utente non trovato" + id);
    }
}
