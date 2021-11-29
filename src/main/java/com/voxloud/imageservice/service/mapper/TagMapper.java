package com.voxloud.imageservice.service.mapper;

import com.voxloud.imageservice.dto.TagRequestDto;
import com.voxloud.imageservice.dto.TagResponseDto;
import com.voxloud.imageservice.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public TagResponseDto mapToDto(Tag tag) {
        TagResponseDto tagResponseDto = new TagResponseDto();
        tagResponseDto.setId(tag.getId());
        tagResponseDto.setTagName(tag.getTagName());
        return tagResponseDto;
    }

    public Tag mapToModel(TagRequestDto tagRequestDto) {
        Tag tag = new Tag();
        tag.setTagName(tagRequestDto.getTagName());
        return tag;
    }
}
