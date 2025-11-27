package com.moodtrack.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryItemResponse {
    private Long id;
    private String content;
    private String label;
    private Double score;
    private Integer intensity;
    private String summary;
    private List<String> keywords;
    private LocalDateTime createdAt;
}
