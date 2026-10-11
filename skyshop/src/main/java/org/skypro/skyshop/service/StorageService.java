package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        setStorageService();
    }

    public Collection<Searchable> getAllSearchable() {
        ArrayList<Searchable> list = new ArrayList<>();
        list.addAll(productMap.values());
        list.addAll(articleMap.values());
        return list;
    }

    private void setStorageService() {
        UUID id1 = UUID.randomUUID();
        productMap.put(id1, new SimpleProduct(id1, "name1", 15));
        UUID id2 = UUID.randomUUID();
        productMap.put(id2, new DiscountedProduct(id2, "name2", 100, 20));
        UUID id3 = UUID.randomUUID();
        productMap.put(id3, new DiscountedProduct(id3, "name3", 200, 20));
        UUID id4 = UUID.randomUUID();
        productMap.put(id4, new FixPriceProduct(id4, "name4"));
        UUID id5 = UUID.randomUUID();
        productMap.put(id5, new FixPriceProduct(id5, "name5"));
        UUID id6 = UUID.randomUUID();
        productMap.put(id6, new SimpleProduct(id6, "name6", 15));
        UUID id7 = UUID.randomUUID();
        articleMap.put(id7, new Article(id7, "article1", "name1"));
        UUID id8 = UUID.randomUUID();
        articleMap.put(id8, new Article(id8, "article2", "name2"));
        UUID id9 = UUID.randomUUID();
        articleMap.put(id9, new Article(id9, "article3", "name3"));
    }

    public Collection<Product> getProducts() {
        return productMap.values();
    }

    public Collection<Article> getArticles() {
        return articleMap.values();
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }
}