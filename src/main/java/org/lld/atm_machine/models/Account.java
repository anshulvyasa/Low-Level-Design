package org.lld.atm_machine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Account {
    private final int accountNumber;
    @Setter private double balance;
}
