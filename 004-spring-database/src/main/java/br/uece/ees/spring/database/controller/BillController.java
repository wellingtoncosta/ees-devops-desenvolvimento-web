package br.uece.ees.spring.database.controller;

import br.uece.ees.spring.database.application.model.BillDto;
import br.uece.ees.spring.database.application.model.NewBillDto;
import br.uece.ees.spring.database.application.service.BillApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillApplicationService applicationService;

    public BillController(BillApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BillDto>> getAll() {
        return ResponseEntity.ok(applicationService.findAll());
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BillDto> save(@RequestBody NewBillDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(applicationService.save(dto));
    }
}
