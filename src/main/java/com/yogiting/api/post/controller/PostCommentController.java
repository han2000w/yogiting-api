package com.yogiting.api.post.controller;

import com.yogiting.api.post.dto.PostCommentReqDto;
import com.yogiting.api.post.dto.PostCommentResDto;
import com.yogiting.api.post.dto.PostLikeReqDto;
import com.yogiting.api.post.service.PostCommentService;
import com.yogiting.api.post.service.PostLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/post/comment")
public class PostCommentController {

    private final PostCommentService postCommentService;

    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }

    @GetMapping
    public ResponseEntity<List<PostCommentResDto>> getComment(@RequestParam Long postId) {
        List<PostCommentResDto> comments = postCommentService.getComment(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostCommentResDto> addComment(@RequestBody PostCommentReqDto postCommentReqDto) {
        PostCommentResDto comment = postCommentService.addComment(postCommentReqDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestBody Map<String, Long> body) {
        postCommentService.deleteComment(body.get("id"));
        return ResponseEntity.noContent().build();
    }
}

