package kr.pe.acet.controller.service;

import kr.pe.acet.controller.web.dto.PostsResponseDto;
import kr.pe.acet.controller.web.dto.PostsSaveRequestDto;
import kr.pe.acet.controller.web.dto.PostsUpdateRequestDto;
import kr.pe.acet.domain.posts.Posts;
import kr.pe.acet.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
 private final PostsRepository postsRepository;

 @Transactional
 public Long save(PostsSaveRequestDto requestDto){
     return postsRepository.save(requestDto.toEntity()).getId();
 }

 @Transactional
 public Long update(Long id, PostsUpdateRequestDto requestDto) {
     Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
     posts.update(requestDto.getTitle(), requestDto.getContent());
     return id;
 }

 public PostsResponseDto findById(Long id) {
     Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
     return new PostsResponseDto(entity);
 }
}

