package org.lld.inventory_management_system.models.products.contrete_product;

import org.lld.inventory_management_system.enums.ProductCategory;
import org.lld.inventory_management_system.models.products.Product;

public class UtilityProduct extends Product {
    // TODO:  add special product parameters here

    public UtilityProduct(String sku, ProductCategory productCategory, String name, int quantity, double price, int threshold) {
        super(sku, productCategory, name, quantity, price, threshold);
    }
}
