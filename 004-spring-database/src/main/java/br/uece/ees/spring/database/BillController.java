package br.uece.ees.spring.database;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillRepository repository;

    public BillController(BillRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BillDto> getAll() {
        return repository.findAll()
                .stream()
                .map(BillDto::from)
                .collect(Collectors.toList());
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BillDto save(@RequestBody BillDto dto) {
        var created = repository.save(dto.toEntity());
        return BillDto.from(created);
    }
}
