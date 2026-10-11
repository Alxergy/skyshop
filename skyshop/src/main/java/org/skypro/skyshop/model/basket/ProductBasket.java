package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> basket = new HashMap<>();

    public void addProduct(UUID id) {
        basket.computeIfAbsent(id, k -> 0);
        basket.put(id, basket.get(id) + 1);
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(basket);
    }
}