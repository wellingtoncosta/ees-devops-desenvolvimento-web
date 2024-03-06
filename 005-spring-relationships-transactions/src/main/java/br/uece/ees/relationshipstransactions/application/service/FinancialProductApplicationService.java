package br.uece.ees.relationshipstransactions.application.service;

import br.uece.ees.relationshipstransactions.application.model.FinancialProductDto;
import br.uece.ees.relationshipstransactions.domain.repository.FinancialProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialProductApplicationService {

    private final FinancialProductRepository repository;

    public FinancialProductApplicationService(FinancialProductRepository repository) {
        this.repository = repository;
    }

    public List<FinancialProductDto> findAll() {
        return FinancialProductDto.from(repository.findAll());
    }
}
