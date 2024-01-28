package pl.pjatk.s26974_Bank.repository;

import org.springframework.stereotype.Repository;
import pl.pjatk.s26974_Bank.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepository {
    private static int howManyAccounts = 0;
    List<Account> accounts = new ArrayList<>();

    public Account create(Account account) {
        account.setId(howManyAccounts++);
        accounts.add(account);
        return account;
    }

    public Optional<Account> getById(int id) {
        return accounts
                .stream()
                .filter(account -> account.getId() == id)
                .findFirst();
    }

    public List<Account> getAll() {
        return accounts;
    }
}
