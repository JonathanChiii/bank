package com.banking.model;

import com.banking.model.ModelUtility.Status;
import com.banking.model.ModelUtility.StringPrefixedSequenceIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Table Per Concrete Class Inheritance Mapping
public class Staff extends Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(
            name = "staff_seq",
            strategy = "org.thoughts.on.java.generators.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ST"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager")
    private Manager manager;
    private Status status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approvedBy")
    private Set<Account> accountsApproved  = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approvedBy")
    private Set<Beneficiary> beneficiaries = new HashSet<>();
}
