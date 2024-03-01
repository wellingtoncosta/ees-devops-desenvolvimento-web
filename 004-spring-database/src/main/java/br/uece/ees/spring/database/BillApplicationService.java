package br.uece.ees.spring.database;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillApplicationService {

    private final BillRepository repository;

    public BillApplicationService(BillRepository repository) {
        this.repository = repository;
    }

    public List<BillDto> findAll() {
        return repository.findAll()
                .stream()
                .map(BillDto::from)
                .collect(Collectors.toList());
    }

    public BillDto save(NewBillDto dto) {
        var created = repository.save(dto.toEntity());
        return BillDto.from(created);
    }
}
