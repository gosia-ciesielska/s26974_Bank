package pl.pjatk.s26974_Bank;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.pjatk.s26974_Bank.model.Account;
import pl.pjatk.s26974_Bank.model.Currency;
import pl.pjatk.s26974_Bank.repository.AccountRepository;
import pl.pjatk.s26974_Bank.service.AccountService;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    private static AccountService accountService;
    private static AccountRepository accountRepository;

    @BeforeAll
    public static void setUp() {
        accountRepository = new AccountRepository();
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void shouldCreateAccount() {
        Account account = new Account("12345678912", 13290, Currency.PLN, "Malgorzata Ciesielska");
        Account created = accountService.create(account);
        Account found = accountService.getAccountData(created.getId());
        assertEquals(found, created);
    }

    @Test
    public void shouldThrowExceptionOnBadPesel() {
        Account account = new Account("1234567", 13290, Currency.PLN, "Malgorzata Ciesielska");
        assertThrows(IllegalArgumentException.class, () -> accountService.create(account));
    }

    @Test
    public void shouldThrowExceptionOnBadBalance() {
        Account account = new Account("12345678912", -12, Currency.PLN, "Malgorzata Ciesielska");
        assertThrows(IllegalArgumentException.class, () -> accountService.create(account));
    }

    @Test
    public void shouldThrowExceptionWhenAccountNotFound() {
        assertThrows(NoSuchElementException.class, () -> accountService.getAccountData(123456));
    }

    @Test
    public void shouldReturnOnlyAccountsWithBalanceOver2000() {
        Account account1 = new Account("12345678912", 13290, Currency.PLN, "Malgorzata Ciesielska");
        Account created1 = accountService.create(account1);
        Account account2 = new Account("12345678912", 2001, Currency.PLN, "Jan Kowalski");
        Account created2 = accountService.create(account2);
        Account account3 = new Account("12345678912", 1500, Currency.EUR, "Adam Nowak");
        Account created3 = accountService.create(account3);

        List<Account> found = accountService.getAccountsWithMinBalance(2000);
        assertTrue(found.contains(created1));
        assertTrue(found.contains(created2));
        assertFalse(found.contains(created3));
    }
}
