package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class AlcoholProduct extends ExciseProduct {
    protected AlcoholProduct(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.23"));
    }
}