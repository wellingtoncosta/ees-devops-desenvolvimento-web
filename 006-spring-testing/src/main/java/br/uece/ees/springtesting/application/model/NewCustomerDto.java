package br.uece.ees.springtesting.application.model;

import br.uece.ees.springtesting.domain.model.Account;
import br.uece.ees.springtesting.domain.model.Customer;

public record NewCustomerDto(String name, String email, String phone, NewAccountDto newAccount) {
    public Customer toCustomer() {
        var customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        if (newAccount != null) {
            customer.setAccount(newAccount.toAccount());
        } else {
            customer.setAccount(new Account());
        }
        return customer;
    }
}
