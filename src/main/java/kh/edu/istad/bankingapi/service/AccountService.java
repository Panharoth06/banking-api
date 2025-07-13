package kh.edu.istad.bankingapi.service;

import kh.edu.istad.bankingapi.domain.Account;
import kh.edu.istad.bankingapi.domain.Customer;
import kh.edu.istad.bankingapi.dto.account.request.CreateAccountRequest;
import kh.edu.istad.bankingapi.dto.account.request.UpdateAccountRequest;
import kh.edu.istad.bankingapi.dto.account.response.AccountResponse;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest createAccountRequest);

    List<AccountResponse> findAllAccounts();

    Account findAccountByActNo(String actNo);

    List<AccountResponse> findAllAccountsByCustomer(Customer customer);

    void deleteAccountByActNo(String actNo);

    AccountResponse updateAccount(UpdateAccountRequest updateAccountRequest);

    void disableAccountByActNo(String actNo);

}
