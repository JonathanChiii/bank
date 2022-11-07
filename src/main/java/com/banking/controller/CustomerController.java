package com.banking.controller;

import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.model.ModelUtility.AccountType;
import com.banking.model.ModelUtility.Question;
import com.banking.model.ModelUtility.Status;
import com.banking.model.SecurityQuestion;
import com.banking.model.AccountTransaction;
import com.banking.service.AccountService;
import com.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;

//    @PostMapping("/register")
//    public Customer register(@RequestBody @Valid CustomerValidation customerValidation) {
//        return customerService.save(customerValidation);
//        //ToDO: need to return JWT
//    }

    //@GetMapping("/authenticate")

//    @PostMapping("/{id}/account")
//    public Account openAccount(@RequestBody @Valid AccountValidation accountValidation){
//        return accountService.save(accountValidation);
//        //ToDO
//    }

    @GetMapping("/test1")
    public Customer test1() {
        // Creating customer
        Customer customer1 = new Customer("", "Bank Of Jonathan", "Jonathan Chi", "0000", null, null, null, "Aadhaar1", null, "PAN1", null);

        // Creating a set of accounts
        Account account1 = new Account("", AccountType.CA, Float.valueOf("99.99"), Status.Enabled, false, customer1, null, null, null, null, null);
        Account account2 = new Account("", AccountType.SB, Float.valueOf("100"), Status.Enabled, false, customer1, null, null, null, null, null);
        Set<Account> accounts = Set.of(account1, account2);

        // Creating a set of SecurityQuestions
        SecurityQuestion securityQuestion1 = new SecurityQuestion("", Question.Q3, "Coldplay", customer1);
        SecurityQuestion securityQuestion2 = new SecurityQuestion("", Question.Q2, "Wang", customer1);
        Set<SecurityQuestion> securityQuestions = Set.of(securityQuestion1, securityQuestion2);

        // Creating transactions
        AccountTransaction accountTransaction1 = new AccountTransaction("", account1, account2, Float.valueOf("100"), "no reason", null);

        // Set referencing attributes
        customer1.setAccounts(accounts);
        account1.setTransferOut(Set.of(accountTransaction1));
        customer1.setSecurityQuestions(securityQuestions);



        return customerService.save(customer1);
    }

    @PostMapping("/customer")
    public Customer register(@RequestBody Customer customer){
        //ToDo
        return customerService.save(customer);
    }

    @GetMapping("/getall")
    public List<Customer> getAllCustomer(){
        //ToDo
        return customerService.getAllCustomers();
    }

}
