
-- 1. 보험 데이터 초기화 및 삽입
DELETE FROM insurancel;
ALTER TABLE insurancel AUTO_INCREMENT = 1;
INSERT INTO insurancel(ir_type, ir_price)
VALUES ("일반", 0), ("완전", 9900), ("슈퍼", 20000);

-- 2. 렌터카 업체 데이터 초기화 및 삽입
DELETE FROM rental;
ALTER TABLE rental AUTO_INCREMENT = 1;
INSERT INTO rental (RE_NAME, RE_NUMBER, RE_REGION, RE_ME_NUM, RE_RG_NUM) VALUES
('발리렌트카', '010-1111-1111', '서울특별시 강남구 테헤란로 123', 1, 1),
('제주사랑렌트카', '010-2222-2222', '경기도 성남시 분당구 판교역로 235', 2, 2),
('카모아렌트카', '010-3333-3333', '제주특별자치도 제주시 신북로 470', 3, 4);
-- 3. 차량 타입 데이터 초기화 및 삽입
DELETE FROM car_type;
ALTER TABLE car_type AUTO_INCREMENT = 1;
INSERT INTO car_type (CT_NAME, CT_TYPE) VALUES
('스파크 4세대', '소형'),
('모닝 어반', '소형'),
('쏘렌토 MQ4', 'SUV'),
('산타페 TM', 'SUV'),
('카니발 KA4', '승합차'),
('스타리아 라운지', '승합차');


-- 4. 차량 데이터 초기화 및 삽입
DELETE FROM car;
ALTER TABLE car AUTO_INCREMENT = 1;
INSERT INTO car (CR_YEAR, CR_FUEL_TYPE, CR_TRANS, CR_PRICE, CR_COUNT, CR_RE_NUM, CR_CT_KEY, CR_THUMBNAIL) VALUES
-- 스파크 4세대
(2022, '휘발유', '오토', 100000, 3, 1, 1, 'spark4.png'),
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

-- 5. 차량 패키지 삽입
DELETE FROM car_package;
ALTER TABLE car_package AUTO_INCREMENT = 1;
INSERT INTO car_package (CP_CR_ID, CP_IR_NUM) VALUES
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 2), (2, 3),
(3, 1), (3, 2), (3, 3),
(4, 1), (4, 2), (4, 3),
(5, 1), (5, 2), (5, 3),
(6, 1), (6, 2), (6, 3);

-- 6. 렌터카 예약 삽입
DELETE FROM re_reservation;
ALTER TABLE re_reservation AUTO_INCREMENT = 1;
INSERT INTO re_reservation (
    RR_CHECKIN, RR_CHEKOUT, RR_PRICE, RR_DATE, RR_STATE,
    RR_NAME, RR_NUMBER, RR_BIRTH, RR_CP_NUM, RR_ME_NUM
) VALUES
('2025-06-10 00:00:00', '2025-06-12 00:00:00', 55000, NOW(), '예약완료', '홍길동', 900101, 19900101, 1, 1),
('2025-06-10 00:00:00', '2025-06-12 00:00:00', 55000, NOW(), '예약완료', '김철수', 910202, 19910202, 1, 2),
('2025-06-10 00:00:00', '2025-06-12 00:00:00', 55000, NOW(), '예약완료', '이민호', 920303, 19920303, 1, 3),
('2025-06-12 00:00:00', '2025-06-14 00:00:00', 55000, NOW(), '예약완료', '정태호', 930404, 19930404, 1, 4);
