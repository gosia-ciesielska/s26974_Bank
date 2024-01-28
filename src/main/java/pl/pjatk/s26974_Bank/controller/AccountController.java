package pl.pjatk.s26974_Bank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.s26974_Bank.model.Account;
import pl.pjatk.s26974_Bank.service.AccountService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/new")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.create(account);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(newAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id) {
        Account account = accountService.getAccountData(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/balance/{balance}")
    public ResponseEntity<List<Account>> accountsWithBalance(@PathVariable double balance) {
        List<Account> accounts = accountService.getAccountsWithMinBalance(balance);
        return ResponseEntity.ok(accounts);
    }
}
