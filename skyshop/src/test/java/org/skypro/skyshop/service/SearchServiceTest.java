package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    void whenStorageIsEmpty_thenSearchReturnsEmptyCollection() {
        Mockito.when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());
        Collection<SearchResult> results = searchService.search("name");
        assertThat(results).isEmpty();
    }

    @Test
    void whenStorageIsNotEmpty_thenSearchReturnsEmptyCollection() {
        Product product = new SimpleProduct(UUID.randomUUID(), "...", 1);
        Mockito.when(storageService.getAllSearchable()).thenReturn(List.of(product));
        Collection<SearchResult> results = searchService.search("name");
        assertThat(results).isEmpty();
    }

    @Test
    void whenStorageIsNotEmpty_thenSearchReturnResult() {
        Product product = new SimpleProduct(UUID.randomUUID(), "TestProduct", 1);
        Mockito.when(storageService.getAllSearchable()).thenReturn(List.of(product));
        Collection<SearchResult> results = searchService.search("Test");
        assertThat(results).hasSize(1);
    }
}