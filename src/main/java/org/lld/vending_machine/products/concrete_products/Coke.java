package org.lld.vending_machine.products.concrete_products;

import org.lld.inventory_management_system.enums.ProductCategory;
import org.lld.vending_machine.products.ExpireBasedProduct;
import org.lld.vending_machine.products.Product;

public class Coke extends ExpireBasedProduct {

    public Coke(ProductCategory productCategory, String name, double price, long expirationDate) {
        super(productCategory, name, price, expirationDate);
    }
}
