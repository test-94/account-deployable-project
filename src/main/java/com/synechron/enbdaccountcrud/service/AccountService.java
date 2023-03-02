package com.synechron.enbdaccountcrud.service;

import java.io.IOException;
import java.util.Optional;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synechron.enbdaccountcrud.common.UserNotFoundException;
import com.synechron.enbdaccountcrud.model.Account;
import com.synechron.enbdaccountcrud.repository.AccountRepository;
import com.synechron.enbdaccountcrud.model.AccountsList;

@Service
public class AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	public AccountsList getAccountsList() throws IOException {
		AccountsList accoutsList = new AccountsList();
		accoutsList.setAccountsList(accountRepository.findAll());
		LOGGER.info(String.format("AccountService: getAccountsList request call {%s}", new ObjectMapper().writeValueAsString(accoutsList)));
		return accoutsList;
	}

	public Account createAccount(Account account) throws IOException {
		
		// set auto increment id
		account.setId(sequenceGeneratorService.getSequenctNumber(Account.SEQUENCE_NAME));
		LOGGER.info(String.format("AccountService: createAccount request call {%s}", new ObjectMapper().writeValueAsString(account)));
		return accountRepository.save(account);
	}

	public Account updateAccount(int id, Account accountWithNewDetails) throws IOException {
		Optional<Account> updatedAccount = accountRepository.findById(id);

		if (updatedAccount.isEmpty()) {
			throw new UserNotFoundException("Account ID: (" + id + ") is not found!");
		}
		updatedAccount.get().setId(id);
		updatedAccount.get().setName(accountWithNewDetails.getName());
		updatedAccount.get().setTotalBalance(accountWithNewDetails.getTotalBalance());
		LOGGER.info(String.format("AccountService: updateAccount request call {%s}", new ObjectMapper().writeValueAsString(updatedAccount)));
		return accountRepository.save(updatedAccount.get());
	}

	public Account getAccountById(int id) throws IOException {
		Optional<Account> account = accountRepository.findById(id);

		if (account.isEmpty())
			throw new UserNotFoundException("Account ID: (" + id + ") is not found!");
		LOGGER.info(String.format("AccountService: getAccountById request call {%s}", new ObjectMapper().writeValueAsString(account.get())));
		return account.get();
	}
	
	public String deleteAccountById(int id) {
		Optional<Account> account = accountRepository.findById(id);
		if(account.isEmpty()) {
			throw new UserNotFoundException("Account ID: (" + id + ") is not found!");
		}
		
		accountRepository.deleteById(id);
		LOGGER.info(String.format("AccountService: deleteAccountById request call"));
		return "Account ID: (" + id + ") has been deleted successfuly!";
	}

}
