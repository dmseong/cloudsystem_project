package com.moodtrack.main.dto;

import com.moodtrack.main.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryItemResponse {
    private Long id;
    private String content;
    private String label;
    private Double score;
    private LocalDateTime createdAt;

    public static DiaryItemResponse from(Diary diary) {
        DiaryItemResponse res = new DiaryItemResponse();
        res.setId(diary.getId());
        res.setContent(diary.getContent());
        res.setLabel(diary.getLabel());
        res.setScore(diary.getScore());
        res.setCreatedAt(diary.getCreatedAt());
        return res;
    }
}
