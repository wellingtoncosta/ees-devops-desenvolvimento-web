package br.uece.ees.relationshipstransactions.controller;

import br.uece.ees.relationshipstransactions.application.model.MoneyTransferDto;
import br.uece.ees.relationshipstransactions.application.service.TransactionApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class MoneyTransferController {

    private final TransactionApplicationService service;

    public MoneyTransferController(TransactionApplicationService service) {
        this.service = service;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> save(@RequestBody MoneyTransferDto transferMoney) {
        service.transfer(transferMoney);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
