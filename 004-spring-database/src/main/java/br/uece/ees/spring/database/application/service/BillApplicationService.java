package br.uece.ees.spring.database.application.service;

import br.uece.ees.spring.database.application.model.BillDto;
import br.uece.ees.spring.database.domain.BillRepository;
import br.uece.ees.spring.database.application.model.NewBillDto;
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
