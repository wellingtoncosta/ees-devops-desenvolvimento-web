package br.uece.ees.springtesting.controller;

import br.uece.ees.springtesting.application.model.NewTransactionDto;
import br.uece.ees.springtesting.application.model.TransactionDto;
import br.uece.ees.springtesting.application.service.TransactionApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/{id}/transactions")
public class TransactionController {

    private final TransactionApplicationService service;

    public TransactionController(TransactionApplicationService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>> findAll(@PathVariable("id") Long customerId) {
        return ResponseEntity.ok(service.findAll(customerId));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TransactionDto> save(
            @PathVariable("id") Long customerId,
            @RequestBody NewTransactionDto newTransaction
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(customerId, newTransaction));
    }
}
