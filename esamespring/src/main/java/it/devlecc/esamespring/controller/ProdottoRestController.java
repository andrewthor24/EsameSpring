package it.devlecc.esamespring.controller;

import it.devlecc.esamespring.avviso.ProdottoNonTrovato;
import it.devlecc.esamespring.model.Prodotto;
import it.devlecc.esamespring.persistenza.ProdottoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.List;

@RestController
public class ProdottoRestController {
    private final ProdottoRepository repository;

    ProdottoRestController(ProdottoRepository repository){
        this.repository=repository;
    }

    @GetMapping("/prodotti")
    List<Prodotto> tutti() {
        return repository.findAll();
    }

    @PostMapping("/prodotto")
    public Prodotto inserisciUnNuovoProdotto(@RequestBody Prodotto nuovoProdotto){
        return repository.save(nuovoProdotto);
    }

    @GetMapping("/prodotti/{id}")
    public Prodotto trovaProdottoConId(@PathVariable Long id){
        return repository.findById(id).orElseThrow( ()-> new ProdottoNonTrovato(id));
    }

    @PutMapping("/prodotti")
    public Prodotto aggiornaDatiProdotto(@RequestBody Prodotto utente) {
        return repository.save(utente);
    }

    @DeleteMapping("/prodotto/{id}")
    void eliminaProdotto(@PathVariable Long id){
        repository.deleteById(id);
    }

    @GetMapping("/prodotto/ricercatradate")
    public List<Prodotto> ricercaProdottoTraDate(
            @RequestParam(name = "datada") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date datada,
            @RequestParam(name = "dataa") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date dataa
    ){
        return repository.findByDataAcquistoBetween(datada,dataa);
    }

    @GetMapping("/prodotto/ricercaprezzo")
    public List<Prodotto> ricercaProdottoPrezzo(
            @RequestParam(name = "min") float min,
            @RequestParam(name = "max") float max
    ){
        return repository.findByPrezzoBetween(min,max);
    }

    @GetMapping("/prodotto/ricercaprezzomin")
    public List<Prodotto> ricercaUtenteRankingMin(
            @RequestParam(name = "max") float max
    ){
        return repository.findByPrezzoLessThan(max);
    }

    @PostMapping("/caricafile")
    public ResponseEntity<String> caricaFile(@RequestParam(name = "file") MultipartFile file){

        Logger logger = LoggerFactory.getLogger(ProdottoRestController.class);

        Reader in = null;
        try {
            in = new InputStreamReader(file.getInputStream());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder().build().parse(in);
            for (CSVRecord record : records) {
                String nome = record.get(0);
                logger.info("Nome: " + nome);
                String prezzo = record.get(1);
                logger.warn("Prezzo: " + prezzo);
                String data = record.get(2);
                logger.warn("Data: " + data);
            }
        } catch (IOException e) {
            logger.error("Si è verificato un errore", e);
        }
        return ResponseEntity.ok("CSV");
    }


}

