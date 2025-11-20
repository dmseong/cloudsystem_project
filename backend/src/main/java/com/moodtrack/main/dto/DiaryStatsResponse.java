package com.moodtrack.main.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class DiaryStatsResponse {
    private String range;
    private long total;
    private Map<String, Long> emotionCount;
    private String dominantEmotion;
}
