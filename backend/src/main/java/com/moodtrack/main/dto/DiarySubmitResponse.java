package com.moodtrack.main.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DiarySubmitResponse {
    private Long diaryId;
    private String label;
    private Double score;

    public DiarySubmitResponse(Long diaryId, String label, Double score) {
        this.diaryId = diaryId;
        this.label = label;
        this.score = score;
    }
}
