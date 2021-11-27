package com.voxloud.imageservice.dto;

import java.util.List;
import lombok.Data;

@Data
public class ImageRequestDto {
    private String name;
    private Long size;
    private String reference;
    private String contentType;
 //   private Long accountId;
  /*  private List<Long> tagId;*/
}
