package br.uece.eesdevops.introducaospringboot.domain.entity;

import br.uece.eesdevops.introducaospringboot.domain.exception.InvalidBookLendingStatusException;

public enum BookLendingStatus {

    LENT, RETURNED;

    public static BookLendingStatus fromString(String raw) {
        if (raw.equals(LENT.toString())) {
            return LENT;
        } else if (raw.equals(RETURNED.toString())) {
            return RETURNED;
        } else {
            throw new InvalidBookLendingStatusException(raw);
        }
    }

}
