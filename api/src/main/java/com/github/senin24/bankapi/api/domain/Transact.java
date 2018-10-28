package com.github.senin24.bankapi.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String transactionName;
    private BigDecimal amount;
    private Date startDate;
    private Date finishDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne
    @JoinColumn(name="debit_account_id", nullable = false)
    private Account debitAccount;

    @ManyToOne
    @JoinColumn(name="credit_account_id", nullable = false)
    private Account creditAccount;

    public Transact(String transactionName, BigDecimal amount, Currency currency, Account debitAccount, Account creditAccount) {
        this.transactionName = transactionName;
        this.amount = amount;
        this.startDate = new Date();
        this.status = Status.getDefault();
        this.currency = currency;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
    }
}
