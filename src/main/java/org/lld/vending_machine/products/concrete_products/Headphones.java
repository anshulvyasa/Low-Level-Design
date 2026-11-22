package org.lld.vending_machine.products.concrete_products;

import org.lld.inventory_management_system.enums.ProductCategory;
import org.lld.vending_machine.products.Product;

public class Headphones extends Product {
    private final long warrenty;

    public Headphones(ProductCategory productCategory, String name, double price,long warrenty) {
        super(productCategory, name, price);
        this.warrenty=warrenty;
    }
}
