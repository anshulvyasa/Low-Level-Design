package org.lld.atm_machine.repository;

import org.lld.atm_machine.enums.AtmStatus;
import org.lld.atm_machine.models.ATM;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ATMRepository {
    private final Map<Integer, ATM> atms = new HashMap<>();

    public void save(ATM atm) {
        atms.put(atm.getAtmId(), atm);
    }

    public Optional<ATM> getById(int id) {
        return Optional.ofNullable(atms.get(id));
    }

    public void updateATMStatusById(int id, AtmStatus newStatus) {
        atms.get(id).setAtmState(newStatus);
    }
}
