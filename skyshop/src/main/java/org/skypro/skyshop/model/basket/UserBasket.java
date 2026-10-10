package org.skypro.skyshop.model.basket;

import java.util.*;

public class UserBasket {
    private final List<BasketItem> basketItemList;
    private final int total;

    public UserBasket(List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
        this.total = basketItemList.stream()
                .mapToInt(value -> value.getProduct().getPrice() * value.getQuantity())
                .sum();
    }

    public int getTotal() {
        return total;
    }

    public List<BasketItem> getBasketItemList() {
        return basketItemList;
    }
}