package br.uece.ees.relationshipstransactions.application.model;

import br.uece.ees.relationshipstransactions.domain.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public record CustomerDto(
        Long id,
        String name,
        String phone,
        AccountDto account,
        List<FinancialProductDto> products
) {

    public static CustomerDto from(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                AccountDto.from(customer.getAccount()),
                FinancialProductDto.from(customer.getProducts())
        );
    }

    public static List<CustomerDto> from(List<Customer> customers) {
        return customers.stream()
                .map(CustomerDto::from)
                .collect(Collectors.toList());
    }

    public Customer toCustomer() {
        var customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setPhone(phone);
        return customer;
    }

}
