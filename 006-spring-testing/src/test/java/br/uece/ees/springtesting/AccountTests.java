package br.uece.ees.springtesting;

import br.uece.ees.springtesting.common.AccountTestBuilder;
import br.uece.ees.springtesting.common.TransactionTestBuilder;
import br.uece.ees.springtesting.domain.model.Account;
import br.uece.ees.springtesting.domain.model.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTests {
    @Nested
    @DisplayName("Given I have an account with 1,000 in balance")
    class AccountWithTransactions {
        private final Account account = new AccountTestBuilder()
                .withNumber(1000L)
                .addTransaction(
                        new TransactionTestBuilder()
                                .type(TransactionType.CREDIT)
                                .amount(new BigDecimal(1000L))
                                .build()
                )
                .build();

        @Nested
        @DisplayName("When I check if I have 999 in balance")
        class EnoughBalance {
            @Test
            @DisplayName("Then it should return true")
            public void test() {
                assertTrue(account.hasEnoughMoney(new BigDecimal(999)));
            }
        }

        @Nested
        @DisplayName("When I check if I have 2,000 in balance")
        class NoEnoughBalance {
            @Test
            @DisplayName("Then it should return false")
            public void test() {
                assertFalse(account.hasEnoughMoney(new BigDecimal(2000)));
            }
        }
    }
}
