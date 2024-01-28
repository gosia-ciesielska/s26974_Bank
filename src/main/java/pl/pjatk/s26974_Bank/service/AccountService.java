package pl.pjatk.s26974_Bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pjatk.s26974_Bank.model.Account;
import pl.pjatk.s26974_Bank.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account create(Account account) throws IllegalArgumentException {
        if (!validate(account)) {
            throw new IllegalArgumentException("Invalid account data");
        }
        return accountRepository.create(account);
    }

    public Account getAccountData(int id) throws NoSuchElementException {
        Optional<Account> accountOptional = accountRepository.getById(id);
        if (accountOptional.isEmpty()) {
            throw new NoSuchElementException("Account not found");
        }
        return accountOptional.get();
    }

    public List<Account> getAccountsWithMinBalance(double minBalance) {
        List<Account> allAccounts = accountRepository.getAll();
        return allAccounts
                .stream()
                .filter(account -> account.getBalance() >= minBalance)
                .collect(Collectors.toList());
    }

    private boolean validate(Account account) {
        return account.getBalance() >= 0 && validatePesel(account.getPesel());
    }

    private boolean validatePesel(String pesel) {
        Pattern pattern = Pattern.compile("^[0-9]{11}$");
        Matcher matcher = pattern.matcher(pesel);
        return matcher.matches();
    }
}
