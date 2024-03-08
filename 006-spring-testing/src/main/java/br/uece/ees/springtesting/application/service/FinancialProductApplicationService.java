package br.uece.ees.springtesting.application.service;

import br.uece.ees.springtesting.application.model.FinancialProductDto;
import br.uece.ees.springtesting.domain.repository.FinancialProductRepository;
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
