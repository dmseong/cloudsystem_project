# 일기 감정 분석 프로젝트 (Moodtracker Project)

## 📅 프로젝트 개요

* **프로젝트 제목:** 일기 감정 분석 프로젝트
* **프로젝트 기간:** 2025년 11월 ~ 2025년 12월
* **주요 목표:** 사용자가 작성한 일기 내용을 분석하여 **감정**을 추출하고, 이를 시각화하여 제공하는 웹 서비스 개발.
    * **AI 분석 결과**를 통해 일기에서 **핵심 키워드**를 도출하고, 일기의 **대표 감정**을 분류합니다.
    * 분석된 감정을 기반으로 사용자에게 **음악을 추천**합니다.
    * 사용자의 감정 변화를 달력 및 통계 차트로 **시각화**하여 보여줍니다.

---

## 🛠️ 기술 스택 (Tech Stack)

| 영역 | 기술 스택 | 설명 |
| :--- | :--- | :--- |
| **Frontend** | **React** | 사용자 인터페이스 구축을 위한 자바스크립트 라이브러리 |
| **Backend** | **Spring** | 백엔드 로직 및 API 개발을 위한 자바 프레임워크 |
| **Database** | **PostgreSQL** | 데이터 저장을 위한 객체-관계형 데이터베이스 |
| **DevOps/Infra** | **GitHub, Docker, Kubernetes** | 버전 관리, 컨테이너화 및 컨테이너 오케스트레이션 |
| **AI Model** | **[jhgan/ko-sroberta-multitask](https://huggingface.co/jhgan/ko-sroberta-multitask), [dlckdfuf141/korean-emotion-kluebert-v2](https://huggingface.co/dlckdfuf141/korean-emotion-kluebert-v2)** | 감정 분석 및 텍스트 요약을 위한 사전 학습된 한글 AI 모델 |
| **API** | **Spotify** | 감정 기반 음악 추천 서비스 연동 |

## 🛠️ 시스템 아키텍처 (System Architecture)

<img width="600" height="480" alt="image" src="https://github.com/user-attachments/assets/ee3f9c0a-720f-4fd5-abeb-5c02b1413c3f" />


---

## 🧑‍💻 팀 구성 및 역할

| 이름 | 담당 역할 |
| :--- | :--- |
| **[권유진](https://github.com/Kwonyujin04)** | **프론트엔드 개발** |
| **[김성희](https://github.com/dmseong)** | **백엔드 개발**, **Kubernetes 설정** |
| **[한혜원](https://github.com/harwoon)** | **AI 모델 구현 및 학습** |

---

## 🚀 프로젝트 실행 방법 (Docker Hub 이미지 사용)

### ⚠️ 실행 전제 조건 

이 프로젝트를 실행하기 위해 로컬 환경에 다음 항목이 설치되어 있어야 합니다.

1. Docker Desktop: Kubernetes 기능이 반드시 활성화되어 있어야 합니다.
2. kubectl: Kubernetes 클러스터를 제어하는 명령줄 도구입니다.
3. Git: 소스 코드를 클론하기 위한 버전 관리 시스템입니다.

### 📝 실행 단계

1. 리포지토리 클론 

    ```bash
    git clone https://github.com/Kwonyujin04/cloudsystem_project.git
    cd [프로젝트 폴더 이름]
    ```

2. 도커 데스크톱 및 Kubernetes 활성화 확인

    **Docker Desktop**을 실행하고, 데스크톱 설정 메뉴에서 **Kubernetes**가 정상적으로 활성화되어 있는지 확인합니다.

3. Kubernetes 리소스 배포 

    루트 경로에서 리소스 배포를 실행합니다.

    ```bash
    kubectl apply -f ./k8s
    ```

4. 서비스 접속 확인 및 실행

    파드와 서비스가 정상적으로 실행되었는지 확인하고, 프론트엔드 서비스 주소로 접속합니다.

   ```bash
   kubectl get pods -n moodtracker
   # localhost:30080으로 접속
   ```

---

## 🖼️ 주요 기능 화면

* **일기 및 감정 분석 화면:** 사용자가 일기를 작성하고, AI가 분석한 **감정, 키워드, 추천 음악**을 확인합니다.

<img width="257" height="550" alt="스크린샷 2025-12-09 123426" src="https://github.com/user-attachments/assets/b84e64ca-5493-4cce-b548-bcc3f90c4fe2" />

* **감정 그래프 및 달력:** 사용자의 **감정 변화를 시각화**하여 한눈에 파악할 수 있도록 제공합니다.

<img width="312" height="550" alt="스크린샷 2025-12-09 123456" src="https://github.com/user-attachments/assets/4f4a815b-2bae-460e-be07-108eabe9a232" />

* **누적 감정 통계:** 프로젝트 기간 동안의 **감정별 누적 통계**를 도넛 차트로 보여줍니다.

<img width="350" height="400" alt="스크린샷 2025-12-09 123522" src="https://github.com/user-attachments/assets/dd147121-e4cb-47ac-bc0f-201aa33fd2c0" />
