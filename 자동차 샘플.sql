SELECT * FROM travel.rental;
SELECT * FROM travel.car_type;
SELECT * FROM travel.car;
SELECT * FROM travel.review;

-- 기존 데이터 삭제 및 AUTO_INCREMENT 초기화
DELETE FROM car;
DELETE FROM rental;
DELETE FROM car_type;

ALTER TABLE rental AUTO_INCREMENT = 1;
ALTER TABLE car_type AUTO_INCREMENT = 1;
ALTER TABLE car AUTO_INCREMENT = 1;

-- 렌터카 업체 삽입
INSERT INTO rental (RE_NAME, RE_NUMBER, RE_REGION, RE_ME_NUM, RE_RG_NUM) VALUES
('발리렌트카', '010-1111-1111', '서울특별시 강남구 테헤란로 123', 1, 1),
('사랑렌트카', '010-2222-2222', '경기도 성남시 분당구 판교역로 235', 2, 2),
('카모아렌트카', '010-3333-3333', '제주특별자치도 제주시 신북로 470', 3, 4);

-- 차량 타입 삽입 (CT_TYPE 값은 ENUM에 맞춰서 'SMALL', 'SUV', 'VAN' 등으로 수정)
INSERT INTO car_type (CT_NAME, CT_TYPE) VALUES
('스파크 4세대', '소형'),
('모닝 어반', '소형'),
('쏘렌토 MQ4', 'SUV'),
('산타페 TM', 'SUV'),
('카니발 KA4', '승합차'),
('스타리아 라운지', '승합차');

INSERT INTO car (CR_YEAR, CR_FUEL_TYPE, CR_TRANS, CR_PRICE, CR_COUNT, CR_RE_NUM, CR_CT_KEY, CR_THUMBNAIL) VALUES
-- 스파크 4세대
(2022, '휘발유', '오토', 100000, 5, 1, 1, 'spark4.png'),
(2022, '휘발유', '오토', 105000, 4, 2, 1, 'spark4.png'),
(2022, '휘발유', '오토', 98000, 3, 3, 1, 'spark4.png'),
(2023, '휘발유', '수동', 110000, 3, 1, 1, 'spark4.png'),
(2023, '휘발유', '수동', 115000, 2, 2, 1, 'spark4.png'),
(2023, '휘발유', '수동', 108000, 1, 3, 1, 'spark4.png'),

-- 모닝 어반
(2021, '휘발유', '오토', 90000, 2, 1, 2, 'morning.png'),
(2021, '휘발유', '오토', 92000, 3, 2, 2, 'morning.png'),
(2021, '휘발유', '오토', 89000, 2, 3, 2, 'morning.png'),
(2022, '휘발유', '오토', 95000, 4, 1, 2, 'morning.png'),
(2022, '휘발유', '오토', 97000, 2, 2, 2, 'morning.png'),
(2022, '휘발유', '오토', 94000, 1, 3, 2, 'morning.png'),

-- 쏘렌토 MQ4
(2022, '경유', '오토', 200000, 6, 1, 3, 'sorento.png'),
(2022, '경유', '오토', 210000, 3, 2, 3, 'sorento.png'),
(2022, '경유', '오토', 198000, 2, 3, 3, 'sorento.png'),
(2023, '하이브리드', '오토', 220000, 5, 1, 3, 'sorento.png'),
(2023, '하이브리드', '오토', 225000, 3, 2, 3, 'sorento.png'),
(2023, '하이브리드', '오토', 210500, 1, 3, 3, 'sorento.png'),

-- 산타페 TM
(2020, '경유', '오토', 180000, 3, 1, 4, 'santafe.png'),
(2020, '경유', '오토', 170500, 2, 2, 4, 'santafe.png'),
(2020, '경유', '오토', 185000, 1, 3, 4, 'santafe.png'),
(2021, '경유', '수동', 170000, 2, 1, 4, 'santafe.png'),
(2021, '경유', '수동', 165000, 1, 2, 4, 'santafe.png'),
(2021, '경유', '수동', 172000, 1, 3, 4, 'santafe.png'),

-- 카니발 KA4
(2022, '경유', '오토', 250000, 4, 1, 5, 'carnival.png'),
(2022, '경유', '오토', 245000, 3, 2, 5, 'carnival.png'),
(2022, '경유', '오토', 255000, 2, 3, 5, 'carnival.png'),
(2023, '하이브리드', '오토', 260000, 5, 1, 5, 'carnival.png'),
(2023, '하이브리드', '오토', 268000, 3, 2, 5, 'carnival.png'),
(2023, '하이브리드', '오토', 258000, 2, 3, 5, 'carnival.png'),

-- 스타리아 라운지
(2023, '경유', '오토', 28000, 3, 1, 6, 'staria.png'),
(2023, '경유', '오토', 27500, 2, 2, 6, 'staria.png'),
(2024, '전기', '오토', 30000, 2, 1, 6, 'staria2.png'),
(2024, '전기', '오토', 31000, 1, 2, 6, 'staria2.png'),
(2024, '전기', '오토', 29500, 1, 3, 6, 'staria2.png');


INSERT INTO review (RV_RATING, RV_CONTENT, RV_DATE, RV_TABLE_NAME, RV_NUMBER, RV_ME_NUM)
VALUES
(5, '렌트카 정말 깨끗하고 잘 나가요!', '2025-05-01', 'rental', 1, 2),
(4, '직원이 친절하고 차량도 상태 좋았어요.', '2025-05-10', 'rental', 1, 3),
(2, '차가 조금 늦게 도착해서 아쉬웠어요.', '2025-05-15', 'rental', 2, 4),
(3, '차 상태는 무난했어요.', '2025-05-20', 'rental', 3, 5),
(5, '완전 강추합니다. 다시 이용할 듯!', '2025-05-25', 'rental', 1, 1),
(4, '차량이 생각보다 연비가 좋아요.', '2025-05-28', 'rental', 1, 5),
(3, '내비게이션 업데이트가 안 되어있었어요.', '2025-05-28', 'rental', 1, 3),
(5, '완전 만족입니다. 다음에도 여기서 빌릴게요.', '2025-05-29', 'rental', 1, 2),
(2, '외관에 약간의 흠집이 있었음.', '2025-05-29', 'rental', 2, 4),
(5, '예약부터 반납까지 완벽했습니다.', '2025-05-29', 'rental', 3, 1);