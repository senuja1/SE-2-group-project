package com.borneobank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRecord {
    private long txId;
    private long accountId;
    private String type;
    private BigDecimal amount;
    private Long relatedAccount;
    private LocalDateTime timestamp;
    private String description;
    // getters & setters
}
