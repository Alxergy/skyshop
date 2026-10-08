package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String article;
    private final String text;

    public Article(UUID id, String article, String text) {
        this.id = id;
        this.article = article;
        this.text = text;
    }

    @Override
    public String toString() {
        return article + "\n" + text;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    public String getName() {
        return article;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Article)) return false;
        return Objects.equals(article, ((Article) o).article);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(article);
    }

    @Override
    public UUID getId() {
        return id;
    }
}