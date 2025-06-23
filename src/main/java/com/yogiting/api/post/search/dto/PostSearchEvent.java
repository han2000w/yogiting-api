package com.yogiting.api.post.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSearchEvent {

    private Long id;
    private String title;
    private String content;
    private String nickname;
}
