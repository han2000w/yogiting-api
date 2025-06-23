package com.yogiting.api.post.search.service;

import com.yogiting.api.post.search.document.PostDocument;
import com.yogiting.api.post.search.repository.PostSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostSearchService {

    private final PostSearchRepository postSearchRepository;

    public PostSearchService(PostSearchRepository postSearchRepository) {
        this.postSearchRepository = postSearchRepository;
    }

    public void savePost(PostDocument postDocument) {
        postSearchRepository.save(postDocument);
    }

    public List<Long> searchPostIds(String keyword, String type) {
        List<PostDocument> results;

        switch (type) {
            case "title":
                results = postSearchRepository.searchByTitle(keyword);
                break;
            case "content":
                results = postSearchRepository.searchByContent(keyword);
                break;
            case "nickname":
                results = postSearchRepository.searchByNickname(keyword);
                break;
            case "all":
            default:
                results = postSearchRepository.searchAll(keyword);
                break;
        }

        return results.stream()
                .map(PostDocument::getId)
                .collect(Collectors.toList());
    }
}
