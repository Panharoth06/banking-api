package kh.edu.istad.bankingapi.service.impl;

import kh.edu.istad.bankingapi.domain.Account;
import kh.edu.istad.bankingapi.domain.AccountType;
import kh.edu.istad.bankingapi.domain.Customer;
import kh.edu.istad.bankingapi.dto.account.request.CreateAccountRequest;
import kh.edu.istad.bankingapi.dto.account.request.UpdateAccountRequest;
import kh.edu.istad.bankingapi.dto.account.response.AccountResponse;
import kh.edu.istad.bankingapi.mapper.AccountMapper;
import kh.edu.istad.bankingapi.repository.AccountRepository;
import kh.edu.istad.bankingapi.repository.AccountTypeRepository;
import kh.edu.istad.bankingapi.repository.CustomerRepository;
import kh.edu.istad.bankingapi.service.AccountService;
import kh.edu.istad.bankingapi.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerRepository customerRepository;
    private final Utility utility;
    private final AccountTypeRepository accountTypeRepository;

    @Override
    public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {

        Customer customer = customerRepository.findCustomerByPhoneNumber(createAccountRequest.phoneNumber()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")
        );

        AccountType accountType = accountTypeRepository.findAccountTypesByTypeIgnoreCase(createAccountRequest.accountType()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Type not found")
        );

//        map
        Account account = accountMapper.fromCreateToAccount(createAccountRequest);

        account.setCustomer(customer);
        account.setAccountType(accountType);
        account.setIsDeleted(false);
        account.setBalance(BigDecimal.ZERO);
        utility.generateAccountNumber(account);

        account = accountRepository.save(account);
        return accountMapper.fromAccountToAccountResponse(account);
    }

    @Override
    public List<AccountResponse> findAllAccounts() {
        return List.of();
    }

    @Override
    public Account findAccountByActNo(String actNo) {
        return null;
    }

    @Override
    public List<AccountResponse> findAllAccountsByCustomer(Customer customer) {
        return List.of();
    }

    @Override
    public void deleteAccountByActNo(String actNo) {

    }

    @Override
    public AccountResponse updateAccount(UpdateAccountRequest updateAccountRequest) {
        return null;
    }

    @Override
    public void disableAccountByActNo(String actNo) {

    }

}
