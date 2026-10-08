package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String text) {
        return storageService.getAllSearchable().stream()
                .filter(pr -> pr.getSearchTerm().contains(text))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}