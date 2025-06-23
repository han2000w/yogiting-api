package com.yogiting.api.post.search.repository;

import com.yogiting.api.post.search.document.PostDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostSearchRepository extends ElasticsearchRepository<PostDocument, Long> {

    @Query("""
    {
      "multi_match": {
        "query": ":#{[0]}",
        "fields": ["title^2", "content", "nickname"],
        "type": "best_fields",
        "fuzziness": "AUTO"
      }
    }
    """)
    List<PostDocument> searchAll(String keyword);

    @Query("""
    {
      "match": {
        "title": {
          "query": ":#{[0]}",
          "fuzziness": "AUTO"
        }
      }
    }
    """)
    List<PostDocument> searchByTitle(String keyword);

    @Query("""
    {
      "match": {
        "content": {
          "query": ":#{[0]}",
          "fuzziness": "AUTO"
        }
      }
    }
    """)
    List<PostDocument> searchByContent(String keyword);

    @Query("""
    {
      "term": {
        "nickname": {
          "value": ":#{[0]}"
        }
      }
    }
    """)
    List<PostDocument> searchByNickname(String keyword);
}



    /*
    // 전체 검색
    List<PostDocument> findByTitleContainingOrContentContainingOrNicknameContaining(String title, String content, String nickname);

    // 제목 검색
    List<PostDocument> findByTitleContaining(String title);

    // 내용 검색
    List<PostDocument> findByContentContaining(String content);

    // 작성자 검색
    List<PostDocument> findByNicknameContaining(String nickname);
     */

