package com.mikemybytes.junit5.pricing;

import java.math.BigDecimal;

record Price(String name, Amount amount) {

    static Price minimal() {
        return new Price("Min non-free price", new Amount(new BigDecimal("0.01")));
    }

}
