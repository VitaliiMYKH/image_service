package com.voxloud.imageservice.dto;

import com.voxloud.imageservice.model.Image;
import java.util.List;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NonNull;

@Data
public class TagRequestDto {
    private String tagName;
   /* private List<Long> imagesId;*/
}
