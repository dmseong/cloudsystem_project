package com.moodtrack.main.service;

import com.moodtrack.main.ai.EmotionAiClient;
import com.moodtrack.main.dto.DiaryItemResponse;
import com.moodtrack.main.dto.DiaryStatsResponse;
import com.moodtrack.main.dto.DiarySubmitResponse;
import com.moodtrack.main.entity.Diary;
import com.moodtrack.main.entity.User;
import com.moodtrack.main.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final EmotionAiClient emotionAiClient;
    private final DiaryRepository diaryRepository;

    private static final Map<String, String> EMOTION_MAP = Map.of(
            "0", "공포",
            "1", "놀람",
            "2", "분노",
            "3", "슬픔",
            "4", "중립",
            "5", "행복",
            "6", "혐오"
    );

    // 내 일기 전체 조회
    @Transactional(readOnly = true)
    public Page<DiaryItemResponse> getMyDiaries(User user, Pageable pageable) {
        var diaryPage = diaryRepository.findByUserOrderByCreatedAtDesc(user, pageable);

        return diaryPage.map(diary -> DiaryItemResponse.builder()
                .id(diary.getId())
                .content(diary.getContent())
                .label(diary.getLabel())
                .score(diary.getScore())
                .createdAt(diary.getCreatedAt())
                .build());
    }

    // 최근 7일간 감정 통계 조회
    @Transactional(readOnly = true)
    public DiaryStatsResponse getWeeklyStats(User user) {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusDays(6); // 최근 7일

        var diaries = diaryRepository.findDiariesBetween(user, start.withHour(0).withMinute(0), end);

        return buildStatsResponse(start, end, diaries);
    }

    // 최근 1달간 감정 통계 조회
    @Transactional(readOnly = true)
    public DiaryStatsResponse getMonthlyStats(User user) {
        LocalDateTime end = LocalDateTime.now();

        // 이번 달 1일부터 오늘까지
        LocalDateTime start = end.withDayOfMonth(1).withHour(0).withMinute(0);

        var diaries = diaryRepository.findDiariesBetween(user, start, end);

        return buildStatsResponse(start, end, diaries);
    }

    // 공통 통계 계산 메서드
    private DiaryStatsResponse buildStatsResponse(LocalDateTime start, LocalDateTime end, List<Diary> diaries) {

        long total = diaries.size();

        Map<String, Long> emotionCount =
                diaries.stream()
                        .collect(Collectors.groupingBy(Diary::getLabel, Collectors.counting()));

        String dominantEmotion = emotionCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("없음");

        return DiaryStatsResponse.builder()
                .range(start.toLocalDate() + " ~ " + end.toLocalDate())
                .total(total)
                .emotionCount(emotionCount)
                .dominantEmotion(dominantEmotion)
                .build();
    }

    // 감정 분석 및 일기 제출
    @Transactional
    public DiarySubmitResponse submitDiary(User user, String content) {

        // 1) 파이썬 감정 분석 API 호출
        var result = emotionAiClient.analyze(content);
        // 감정 라벨 한글로 변환
        String emotionLabel = EMOTION_MAP.getOrDefault(result.getLabel(), "정보 없음");

        // 2) 일기 DB에 저장
        Diary diary = Diary.builder()
                .user(user)
                .content(content)
                .label(emotionLabel)
                .score(result.getScore())
                .createdAt(LocalDateTime.now())
                .build();

        diaryRepository.save(diary);

        // 3) 클라이언트에 분석 결과 반환
        return DiarySubmitResponse.builder()
                .diaryId(diary.getId())
                .label(emotionLabel)
                .score(result.getScore())
                .build();
    }
}

