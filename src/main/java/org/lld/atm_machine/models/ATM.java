package org.lld.atm_machine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.lld.atm_machine.enums.AtmStatus;

@AllArgsConstructor
@Getter
public class ATM {
    private final int atmId;

    @Setter  private AtmStatus atmState;
    @Setter private double totalAmount;

    @Setter private int twoThousandNoteCount;
    @Setter private int fiveHundredNoteCount;
    @Setter private int hundredNoteCount;
}
