package kh.edu.istad.bankingapi.service;

import kh.edu.istad.bankingapi.dto.account.request.CreateAccountRequest;
import kh.edu.istad.bankingapi.dto.account.request.UpdateAccountRequest;
import kh.edu.istad.bankingapi.dto.account.response.AccountResponse;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest createAccountRequest);

    List<AccountResponse> findAllAccounts();

    AccountResponse findAccountByActNo(String actNo);

    List<AccountResponse> findAllAccountsByCustomerPhoneNumber(String phoneNumber);

    void deleteAccountByActNo(String actNo);

    AccountResponse updateAccount(String actNo, UpdateAccountRequest updateAccountRequest);

    void disableAccountByActNo(String actNo);

}
