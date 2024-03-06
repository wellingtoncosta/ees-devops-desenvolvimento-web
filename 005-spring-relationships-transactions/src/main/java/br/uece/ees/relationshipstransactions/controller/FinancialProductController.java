package br.uece.ees.relationshipstransactions.controller;

import br.uece.ees.relationshipstransactions.application.model.FinancialProductDto;
import br.uece.ees.relationshipstransactions.application.service.FinancialProductApplicationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/financial-products")
public class FinancialProductController {

    private final FinancialProductApplicationService service;

    public FinancialProductController(FinancialProductApplicationService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FinancialProductDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
