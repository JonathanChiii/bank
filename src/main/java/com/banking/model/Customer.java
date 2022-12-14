package com.banking.model;

import com.banking.model.ModelUtility.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED) // Table Per Subclass Inheritance Mapping
public class Customer extends BankUser {
    //@JsonManagedReference(value = "customer-account")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Account> accounts = new HashSet<>();

    //@JsonManagedReference(value = "customer-security_question")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<SecurityQuestion> securityQuestions = new HashSet<>();

    //@JsonManagedReference(value = "customer-beneficiary")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiarySource")
    private Set<Beneficiary> beneficiaries = new HashSet<>();

    private String phone;
    @Column(unique=true)
    private String aadhaar;
    private byte[] aadhaarPicture;
    @Column(unique=true)
    private String PAN;
    private byte[] PANPicture;

    public Customer(Long id, String username, String fullName, String password, Status status, Set<Transaction> transactions, Set<Role> roles, Set<Account> accounts, Set<SecurityQuestion> securityQuestions, Set<Beneficiary> beneficiaries, String aadhaar, byte[] aadhaarPicture, String PAN, byte[] PANPicture) {
        super(id, username, fullName, password, status, transactions, roles);
        this.accounts = accounts;
        this.securityQuestions = securityQuestions;
        this.beneficiaries = beneficiaries;
        this.aadhaar = aadhaar;
        this.aadhaarPicture = aadhaarPicture;
        this.PAN = PAN;
        this.PANPicture = PANPicture;
    }
    public Customer(String username, String fullName, String password, Set<Role> roles){
        super(username, fullName, password, roles);
    }
}
