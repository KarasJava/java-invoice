package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product can`t be null");
        }

        if (!products.containsKey(product)) {
            products.put(product, 1);
        } else {
            products.put(product, products.get(product) + 1);
        }
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException();
        }

        products.put(product, quantity);

    }

    public BigDecimal getNetTotal() {

        BigDecimal subtotal = BigDecimal.ZERO;

        for (Product product : products.keySet()) {

            BigDecimal price = product.getPrice();
            BigDecimal quantity = BigDecimal.valueOf(products.get(product));

            subtotal = subtotal.add(price.multiply(quantity));
        }

        return subtotal;

    }

    public BigDecimal getTaxTotal() {

        BigDecimal taxTotal = BigDecimal.ZERO;

        for (Product product : products.keySet()) {

            BigDecimal tax = product.getTax();
            BigDecimal quantity = BigDecimal.valueOf(products.get(product));

            taxTotal = taxTotal.add(tax.multiply(quantity));
        }

        return taxTotal;
    }

    public BigDecimal getGrossTotal() {

        return getNetTotal().add(getTaxTotal());

    }
}