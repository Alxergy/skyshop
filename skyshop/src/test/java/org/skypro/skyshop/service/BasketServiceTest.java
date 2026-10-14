package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    void whenProductDoesNotExist_thenAddProductThrowsException() {
        UUID id = UUID.randomUUID();
        Mockito.when(storageService.getProductById(id)).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> basketService.addProductById(id));
    }

    @Test
    void whenProductExists_thenAddProductInBasket() {
        UUID id = UUID.randomUUID();
        Product product = new SimpleProduct(id, "...", 1);
        Mockito.when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        basketService.addProductById(id);
        verify(productBasket, times(1)).addProduct(id);
    }

    @Test
    void whenBasketIsEmpty_thenGetUserBasketReturnsEmptyBasket() {
        Mockito.when(productBasket.getProducts()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();
        assertThat(userBasket.getBasketItemList()).isEmpty();
        assertThat(userBasket.getTotal()).isZero();
    }

    @Test
    void whenBasketIsNotEmpty_thenGetUserBasketReturnsBasket() {
        UUID id = UUID.randomUUID();
        Product product = new SimpleProduct(id, "...", 1);
        Mockito.when(productBasket.getProducts()).thenReturn(Map.of(id, 3));
        Mockito.when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        UserBasket userBasket = basketService.getUserBasket();
        assertThat(userBasket.getBasketItemList()).hasSize(1);
        assertThat(userBasket.getTotal()).isEqualTo(3);
    }
}