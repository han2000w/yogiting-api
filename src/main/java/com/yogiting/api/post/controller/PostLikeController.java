package com.yogiting.api.post.controller;

import com.yogiting.api.post.dto.PostLikeReqDto;
import com.yogiting.api.post.service.PostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/post/like")
public class PostLikeController {

    private final PostLikeService postLikeService;

    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping
    public ResponseEntity<?> addLikePost(@RequestBody PostLikeReqDto postLikeReqDto) {
        postLikeService.addLikeCount(postLikeReqDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLikePost(@RequestBody PostLikeReqDto postLikeReqDto) {
        postLikeService.deleteLikeCount(postLikeReqDto);
        return ResponseEntity.ok().build();
    }

}

