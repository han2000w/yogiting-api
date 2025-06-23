package com.yogiting.api.post.service;

import com.yogiting.api.post.mapper.PostViewMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostViewService {

    private final PostViewMapper postViewMapper;

    public PostViewService(PostViewMapper postViewMapper) {
        this.postViewMapper = postViewMapper;
    }

    public void addViewCount(Long postId, Long memberId) {
        postViewMapper.addViewCount(postId, memberId);
    }
}
