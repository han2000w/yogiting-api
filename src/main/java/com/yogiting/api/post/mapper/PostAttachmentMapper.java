package com.yogiting.api.post.mapper;

import com.yogiting.api.post.domain.Post;
import com.yogiting.api.post.domain.PostAttachment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostAttachmentMapper {

    void createAttachment(List<PostAttachment> attachments);
    void deleteAttachment(List<Long> removeFileIds);
    List<String> getAttachment(Long postId);
}
