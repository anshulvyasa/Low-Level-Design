package org.lld.atm_machine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Card {
    private final  Account account;
    private final  int pin;
    private final  int cardNumber;
}
