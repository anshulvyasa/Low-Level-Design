package org.lld.vending_machine.products;

import org.lld.inventory_management_system.enums.ProductCategory;

public class ExpireBasedProduct extends  Product{
    protected final long expirationDate;

    public ExpireBasedProduct(ProductCategory productCategory, String name, double price,long expirationDate) {
        super(productCategory, name, price);
        this.expirationDate=expirationDate;
    }
}
