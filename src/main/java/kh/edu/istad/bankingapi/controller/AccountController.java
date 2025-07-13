package kh.edu.istad.bankingapi.controller;

import jakarta.validation.Valid;
import kh.edu.istad.bankingapi.dto.account.request.CreateAccountRequest;
import kh.edu.istad.bankingapi.dto.account.request.UpdateAccountRequest;
import kh.edu.istad.bankingapi.dto.account.response.AccountResponse;
import kh.edu.istad.bankingapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }

    @GetMapping
    public List<AccountResponse> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping("/{actNo}")
    public AccountResponse findAccountByActNo(@PathVariable String actNo) {
        return accountService.findAccountByActNo(actNo);
    }

    @GetMapping("/{phoneNumber}")
    public List<AccountResponse>  findAccountsByCustomerPhoneNumber(@PathVariable String phoneNumber) {
        return accountService.findAllAccountsByCustomerPhoneNumber(phoneNumber);
    }

    @DeleteMapping("/{actNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountByActNo(@PathVariable String actNo) {
        accountService.deleteAccountByActNo(actNo);
    }

    @PatchMapping("/{actNo}")
    public AccountResponse updateAccountByActNo(@PathVariable String actNo, @RequestBody UpdateAccountRequest updateAccountRequest) {
        return accountService.updateAccount(actNo, updateAccountRequest);
    }


}
