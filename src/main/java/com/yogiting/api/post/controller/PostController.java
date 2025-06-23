package com.yogiting.api.post.controller;

import com.yogiting.api.point.service.PointService;
import com.yogiting.api.post.dto.*;
import com.yogiting.api.post.search.service.PostSearchService;
import com.yogiting.api.post.service.PostLikeService;
import com.yogiting.api.post.service.PostService;
import com.yogiting.api.post.service.PostViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/post")
public class PostController {

    private final PostService postService;
    private final PostViewService postViewService;
    private final PointService pointService;
    private final PostLikeService postLikeService;
    private final PostSearchService postSearchService;

    public PostController(PostService postService, PostViewService postViewService, PointService pointService, PostLikeService postLikeService, PostSearchService postSearchService) {
        this.postService = postService;
        this.postViewService = postViewService;
        this.pointService = pointService;
        this.postLikeService = postLikeService;
        this.postSearchService = postSearchService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostCreateReqDto postCreateReqDto) {

        postService.createPost(postCreateReqDto);
        pointService.addPoint(postCreateReqDto.getMemberId(), 10L);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPostPreview() {

        List<PostPreviewResDto> postPreview = postService.getPostPreview();
        return ResponseEntity.ok(postPreview);
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId, @RequestParam(required = false) Long memberId) {

        if (memberId != null) {
            postViewService.addViewCount(postId, memberId);
        }

        PostDetailResDto post = postService.getPost(postId, memberId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/liked")
    public ResponseEntity<?> getPostLikes(@RequestParam Long memberId) {
        List<PostPreviewResDto> response = postLikeService.getMemberLikedPost(memberId);

        return ResponseEntity.ok(response);

    }

    @PutMapping("/edit/{postId}")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @RequestBody PostEditReqDto postEditReqDto) {
        postService.editPost(postId, postEditReqDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPost(@RequestParam String keyword, @RequestParam(defaultValue = "all") String type) {


        List<Long> postIds = postSearchService.searchPostIds(keyword, type);
        List<PostPreviewResDto> posts = new ArrayList<>();

        if (!postIds.isEmpty()) {
            posts = postService.getPostPreviewById(postIds);
        }

        System.out.println("=============");
        System.out.println(postIds);
        System.out.println("=============");

        return ResponseEntity.ok(posts);


    }
}
