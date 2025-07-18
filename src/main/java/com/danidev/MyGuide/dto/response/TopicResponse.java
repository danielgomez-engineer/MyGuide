package com.danidev.MyGuide.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponse {

    private Long id;
    private String name;
    private LocalDateTime date;

}
