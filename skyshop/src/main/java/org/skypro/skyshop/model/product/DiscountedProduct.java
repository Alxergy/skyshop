package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discount;

    public DiscountedProduct(UUID id, String name, int basePrice, int discountPercent) {
        super(id, name);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть строго больше 0");
        } else {
            this.basePrice = basePrice;
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Процент должен быть числом в диапазоне от 0 до 100 включительно");
        } else {
            this.discount = discountPercent;
        }
    }

    @Override
    public int getPrice() {
        return basePrice - (basePrice * discount / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + basePrice + " (" + discount + "%)";
    }
}