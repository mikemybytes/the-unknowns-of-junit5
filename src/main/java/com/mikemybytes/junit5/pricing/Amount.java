package com.mikemybytes.junit5.pricing;

import java.math.BigDecimal;

record Amount(BigDecimal amount) { // let's assume there's only one currency

    static Amount of(String amount) {
        return new Amount(new BigDecimal(amount));
    }

    boolean isLessThan(Amount other) {
        return this.amount.compareTo(other.amount) < 0;
    }

    Amount subtract(Amount other) {
        return new Amount(this.amount.subtract(other.amount));
    }

}
