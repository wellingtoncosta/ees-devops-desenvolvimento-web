package br.uece.ees.springtesting.controller;

import br.uece.ees.springtesting.application.model.AddFinancialProductToCustomerDto;
import br.uece.ees.springtesting.application.model.CustomerDto;
import br.uece.ees.springtesting.application.model.NewCustomerDto;
import br.uece.ees.springtesting.application.service.CustomerApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerApplicationService service;

    public CustomerController(CustomerApplicationService service) {
        this.service = service;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomerDto> save(@RequestBody NewCustomerDto newCustomer) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(newCustomer));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(
            value = "/{id}/financial-products",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomerDto> addFinancialProduct(
            @PathVariable Long id,
            @RequestBody AddFinancialProductToCustomerDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addFinancialProduct(id, dto));
    }
}
