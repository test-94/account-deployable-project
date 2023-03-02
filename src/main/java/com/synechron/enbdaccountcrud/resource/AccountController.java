package com.synechron.enbdaccountcrud.resource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synechron.enbdaccountcrud.common.UserNotFoundException;
import com.synechron.enbdaccountcrud.model.Account;
import com.synechron.enbdaccountcrud.model.AccountsList;
import com.synechron.enbdaccountcrud.repository.AccountRepository;
import com.synechron.enbdaccountcrud.service.AccountService;

@RestController
@RefreshScope
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	@PostMapping("/accounts")
	public ResponseEntity<Object> createAccount(@RequestBody Account account) throws IOException {
		return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.OK);
	}

	@PutMapping("/accounts/{id}")
	@CachePut(cacheNames = "accounts", key = "#id")
	public ResponseEntity<Object> updateAccount(@PathVariable int id, @RequestBody Account accountWithNewDetails) throws IOException {
		return new ResponseEntity<>(accountService.updateAccount(id, accountWithNewDetails), HttpStatus.OK);
	}

	@GetMapping("/accounts")
	public AccountsList findAllAccounts() throws IOException {
		
		AccountsList accoutsList = new AccountsList();
		accoutsList.setAccountsList(accountService.getAccountsList().getAccountsList());
		
		return accoutsList;
	}

	
	@GetMapping("/accounts/{id}")
	@Cacheable(cacheNames = "accounts", key = "#id")
	public ResponseEntity<Object> getAccountById(@PathVariable int id) throws IOException {
		return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
	}

	@DeleteMapping("accounts/{id}")
	@CacheEvict(cacheNames = "accounts", key = "#id")
	public ResponseEntity<Object> deleteAccoutById(@PathVariable int id) {
		return new ResponseEntity<>(accountService.deleteAccountById(id), HttpStatus.OK);
	}
	
	
	@GetMapping("/demo")
	public String getDemo() {
		return "Feign From Account";
	}

}
