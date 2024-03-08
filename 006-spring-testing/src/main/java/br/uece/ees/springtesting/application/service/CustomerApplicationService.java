package br.uece.ees.springtesting.application.service;

import br.uece.ees.springtesting.application.model.AddFinancialProductToCustomerDto;
import br.uece.ees.springtesting.application.model.CustomerDto;
import br.uece.ees.springtesting.application.model.NewCustomerDto;
import br.uece.ees.springtesting.domain.model.CustomerAlreadyExistException;
import br.uece.ees.springtesting.domain.model.CustomerNotFoundException;
import br.uece.ees.springtesting.domain.model.FinancialProductNotFoundException;
import br.uece.ees.springtesting.domain.repository.CustomerRepository;
import br.uece.ees.springtesting.domain.repository.FinancialProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerApplicationService {

    private final CustomerRepository customerRepository;
    private final FinancialProductRepository financialProductRepository;

    public CustomerApplicationService(
            CustomerRepository customerRepository,
            FinancialProductRepository financialProductRepository
    ) {
        this.customerRepository = customerRepository;
        this.financialProductRepository = financialProductRepository;
    }

    public CustomerDto save(NewCustomerDto newCustomer) {
        if (customerRepository.existsByEmail(newCustomer.email())) {
            throw new CustomerAlreadyExistException(newCustomer.email());
        }

        var customerSaved = customerRepository.save(newCustomer.toCustomer());
        return CustomerDto.from(customerSaved);
    }

    public List<CustomerDto> findAll() {
        return CustomerDto.from(customerRepository.findAll());
    }

    public CustomerDto findById(Long id) {
        var customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }

        return CustomerDto.from(customer.get());
    }

    public CustomerDto addFinancialProduct(Long customerId, AddFinancialProductToCustomerDto dto) {
        var customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }

        var product = financialProductRepository.findById(dto.productId());
        if (product.isEmpty()) {
            throw new FinancialProductNotFoundException(dto.productId());
        }

        customer.get().getProducts().add(product.get());
        var customerUpdated = customerRepository.save(customer.get());
        return CustomerDto.from(customerUpdated);
    }
}
