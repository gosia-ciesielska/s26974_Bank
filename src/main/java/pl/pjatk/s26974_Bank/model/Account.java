package pl.pjatk.s26974_Bank.model;

import lombok.*;

@Data
public class Account {
    private int id;
    private String pesel;
    private double balance;
    private Currency currency;
    private String ownerName;

    public Account(String pesel, double balance, Currency currency, String ownerName) {
        this.pesel = pesel;
        this.balance = balance;
        this.currency = currency;
        this.ownerName = ownerName;
    }
}
