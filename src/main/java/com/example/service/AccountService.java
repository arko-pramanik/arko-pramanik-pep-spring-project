package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account){
        if (account.getUsername().isBlank()){
            return null;
        }
        if (account.getPassword().length() < 4){
            return null;
        }
        Account newAccount = accountRepository.findAccountByUsername(account.getUsername());
        if (newAccount != null){
            return account;
        }
        return accountRepository.save(account);
    }

    public Account getAccount(Account account){
        Account loggedinAccount = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (loggedinAccount == null){
            return null;
        }
        return loggedinAccount;
    }

}
