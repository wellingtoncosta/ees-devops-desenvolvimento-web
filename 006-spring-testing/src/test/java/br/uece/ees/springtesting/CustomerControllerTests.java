package br.uece.ees.springtesting;

import br.uece.ees.springtesting.application.model.NewCustomerDto;
import br.uece.ees.springtesting.domain.model.Account;
import br.uece.ees.springtesting.domain.model.Customer;
import br.uece.ees.springtesting.domain.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private CustomerRepository repository;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Nested
    class FindAll {
        @Test
        void shouldGetOkStatusAndEmptyResponse() throws Exception {
            mockMvc.perform(get("/customers"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(0)));
        }

        @Test
        void shouldGetOkStatusWithResponse() throws Exception {
            var customer = new Customer();
            customer.setName("Fake Customer");
            customer.setEmail("fake@company.co");
            customer.setPhone("1234567890");

            var account = new Account();
            account.setNumber(1000L);
            account.setOwner(customer);
            customer.setAccount(account);

            customer = repository.save(customer);

            mockMvc.perform(get("/customers"))
                    .andDo(print())
                    .andExpect(status().isOk()) // Assert
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].id").value(customer.getId()))
                    .andExpect(jsonPath("$[0].name").value(customer.getName()))
                    .andExpect(jsonPath("$[0].email").value(customer.getEmail()))
                    .andExpect(jsonPath("$[0].phone").value(customer.getPhone()))
                    .andExpect(jsonPath("$[0].account.number").value(account.getNumber()));
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldGetOkStatusWithResponse() throws Exception {
            var customer = new Customer();
            customer.setName("Fake Customer");
            customer.setEmail("fake@company.co");
            customer.setPhone("1234567890");

            var account = new Account();
            account.setOwner(customer);
            customer.setAccount(account);

            customer = repository.save(customer);

            mockMvc.perform(get("/customers/" + customer.getId()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(customer.getId()))
                    .andExpect(jsonPath("$.name").value(customer.getName()))
                    .andExpect(jsonPath("$.email").value(customer.getEmail()))
                    .andExpect(jsonPath("$.phone").value(customer.getPhone()))
                    .andExpect(jsonPath("$.account.number").value(account.getNumber()));
        }

        @Test
        void shouldGetNotFoundStatus() throws Exception {
            mockMvc.perform(get("/customers/1000"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class Save {
        @Test
        void shouldGetCreatedStatusAndSaveNewCustomer() throws Exception {
            var newCustomerDto = new NewCustomerDto(
                    "Fake Customer",
                    "fake@company.co",
                    "1234567890",
                    null
            );

            var request = post("/customers")
                    .content(mapper.writeValueAsString(newCustomerDto))
                    .contentType(MediaType.APPLICATION_JSON);

            mockMvc.perform(request)
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(notNullValue()))
                    .andExpect(jsonPath("$.name").value(newCustomerDto.name()))
                    .andExpect(jsonPath("$.email").value(newCustomerDto.email()))
                    .andExpect(jsonPath("$.phone").value(newCustomerDto.phone()))
                    .andExpect(jsonPath("$.account.id").value(notNullValue()))
                    .andExpect(jsonPath("$.account.number").value(notNullValue()));
        }

        @Test
        void shouldGetConflictStatusDueToDuplicatedEmail() throws Exception {
            var customer = new Customer();
            customer.setName("Fake Customer");
            customer.setEmail("fake@company.co");
            customer.setPhone("1234567890");

            var account = new Account();
            account.setOwner(customer);
            customer.setAccount(account);

            repository.save(customer);

            var newCustomerDto = new NewCustomerDto(
                    "Fake Customer",
                    "fake@company.co",
                    "1234567890",
                    null
            );

            var request = post("/customers")
                    .content(mapper.writeValueAsString(newCustomerDto))
                    .contentType(MediaType.APPLICATION_JSON);

            mockMvc.perform(request).andExpect(status().isConflict());
        }
    }
}
