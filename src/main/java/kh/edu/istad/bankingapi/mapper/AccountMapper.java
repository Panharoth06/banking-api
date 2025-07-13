package kh.edu.istad.bankingapi.mapper;

import kh.edu.istad.bankingapi.domain.Account;
import kh.edu.istad.bankingapi.dto.account.request.CreateAccountRequest;
import kh.edu.istad.bankingapi.dto.account.request.UpdateAccountRequest;
import kh.edu.istad.bankingapi.dto.account.response.AccountResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "accountType", ignore = true)
    @Mapping(target = "actName", source = "accountName")  // map accountName → actName
    Account fromCreateToAccount(CreateAccountRequest createAccountRequest);

    @Mapping(target = "accountType", source = "account.accountType.type")
    AccountResponse fromAccountToAccountResponse(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAccountPartially(UpdateAccountRequest updateAccountRequest, @MappingTarget Account account);
}

