package com.heumsi.springboot.service.posts;

import com.heumsi.springboot.domain.posts.Posts;
import com.heumsi.springboot.domain.posts.PostsRepository;
import com.heumsi.springboot.web.dto.PostsResponseDto;
import com.heumsi.springboot.web.dto.PostsSaveRequestsDto;
import com.heumsi.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestsDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }
}
