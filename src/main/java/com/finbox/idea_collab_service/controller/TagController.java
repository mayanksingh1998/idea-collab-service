package com.finbox.idea_collab_service.controller;

import com.finbox.idea_collab_service.dto.reponse.IdeaColabSvcResponse;
import com.finbox.idea_collab_service.dto.request.CreateIdeaRequest;
import com.finbox.idea_collab_service.dto.request.CreateTagRequestDto;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.Tag;
import com.finbox.idea_collab_service.service.TagService;
import com.finbox.idea_collab_service.service.impl.TagServiceImpl;
import com.finbox.idea_collab_service.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<Tag>> createTag(
            @RequestBody CreateTagRequestDto createTagRequestDto) {
        return ResponseBuilder.build(
                tagService.createTag(createTagRequestDto.getTagName()), HttpStatus.OK);
    }
}
