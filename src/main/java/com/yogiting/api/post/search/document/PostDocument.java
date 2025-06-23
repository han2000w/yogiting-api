package com.yogiting.api.post.search.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "posts-cdc")
public class PostDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "nori", searchAnalyzer = "nori")
    private String title;

    @Field(type = FieldType.Text, analyzer = "nori", searchAnalyzer = "nori")
    private String content;

    @Field(type = FieldType.Keyword)
    private String nickname;
}
