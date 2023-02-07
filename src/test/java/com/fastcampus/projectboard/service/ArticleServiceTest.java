package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;

    @Mock private ArticleRepository articleRepository;



//    홈 버튼 -> 게시판 페이지로 리다이렉션
//    정렬

    // 검색 테스트
    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameter_whenSearchingArticles_ThenReturnsArticleList()
    {
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");// 제목, 본문, ID, 닉네임 ,해시태그

        // Then
        assertThat(articles).isNotNull();

    }


    // 각 게시글 페이지로 이동
    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenId_whenSearchingArticle_ThenReturnsArticle()
    {
        // Given

        // When
        ArticleDto articles = sut.searchArticle(1L);

        // Then
        assertThat(articles).isNotNull();

    }

    @DisplayName("게시글의 ID와 수정 정보 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle()
    {

        // Given
        BDDMockito.given(articleRepository.save(any(Article.class))).willReturn(null);
        // when
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "minsang","title","content","hashtag"));

        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글의 ID와 수정 정보 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenArticleIdAndModifiedInfo_whenSavingArticle_thenUpdatesArticle()
    {

        // Given
        BDDMockito.given(articleRepository.save(any(Article.class))).willReturn(null);
        // when
        sut.updateArticle(1L, ArticleUpdateDto.of("title","content","hashtag"));

        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글의 ID를 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle()
    {

        // Given
        BDDMockito.willDoNothing().given(articleRepository).delete(any(Article.class));
        // when
        sut.deleteArticle(1L);

        // Then
        then(articleRepository).should().delete(any(Article.class));

    }

}