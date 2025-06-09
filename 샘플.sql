SELECT * FROM travel.region;
SELECT * FROM travel.lodging;
SELECT * FROM travel.room;
SELECT * FROM travel.thumbnail;
SELECT * FROM travel.review;
SELECT * FROM travel.re_reservation;

DELETE FROM region;
DELETE FROM lodging;
DELETE FROM room;
DELETE FROM thumbnail;
DELETE FROM review;
DELETE FROM default_option;

ALTER TABLE region AUTO_INCREMENT = 1;
ALTER TABLE lodging AUTO_INCREMENT = 1;
ALTER TABLE room AUTO_INCREMENT = 1;
ALTER TABLE thumbnail AUTO_INCREMENT = 1;
ALTER TABLE review AUTO_INCREMENT = 1;
ALTER TABLE default_option AUTO_INCREMENT = 1;

INSERT INTO member (ME_ID, ME_PW, ME_NAME, ME_NICK, ME_NUMBER, ME_EMAIL, ME_AUTHORITY, ME_BIRTHDAY, ME_GENDER, ME_PROFILE, ME_PROVIDER, ME_DEL) VALUES
('user01', '$2b$12$cE3qQ0dBV6GQx/g6UKRfLupzBEzU1BSZg9JtpNzGLoMyodM3LjMoa', '홍길동', '길동이', '01012345678', 'user01@example.com', 'USER', '19900101', '남자', 'default1.jpg', 'NORMAL', 'N'),
('user02', '$2b$12$cWxlnpiXk37R98U6NZ7zzONKKxqvRLpSkfNHcahJJySXjZrz4/Ch6', '김철수', '철수짱', '01023456789', 'user02@example.com', 'USER', '19910202', '남자', 'default2.jpg', 'NORMAL', 'N'),
('user03', '$2b$12$/7JW.XpEhb6aQFXqI1aR8.dSQrn63W6o3JIdetGItRc3dqnm.MzTq', '이민호', '민호맨', '01034567890', 'user03@example.com', 'USER', '19920303', '남자', 'default3.jpg', 'NORMAL', 'N'),
('user04', '$2b$12$2Viu.RNk48oEnEIio9VGze.3z6kmpx5EX2VM3SeKe1pgXxhe4GglC', '박지훈', '지훈킹', '01045678901', 'user04@example.com', 'USER', '19930404', '남자', 'default4.jpg', 'NORMAL', 'N'),
('user05', '$2b$12$CKcYUPaoaTNCr3dWma9B..AVYx4rGmgqRen9ifepzkboQuJCx03aa', '최우진', '우진이', '01056789012', 'user05@example.com', 'USER', '19940505', '남자', 'default5.jpg', 'NORMAL', 'N'),
('user06', '$2b$12$N05/DC0IcdXuXi2LgGbdB.kHsshLExM4OweZLfTTLpzYfTNaxE4j2', '정태호', '태호짱', '01067890123', 'user06@example.com', 'USER', '19950606', '남자', 'default6.jpg', 'NORMAL', 'N');

INSERT INTO region(RG_NAME) VALUES
("서울"),("경기"),("부산"),("제주");

INSERT INTO default_option(DO_NAME) VALUES
("조식"),("금연 호텔"),("룸 온리"),("흡연 가능"),("수영장"),("반려 동물"),("스파"),("오션뷰"),("취사 가능");

INSERT INTO lodging(LD_NAME,LD_REGION,LD_TYPE,LD_NUMBER,LD_RATING,LD_INFOR,LD_MEAL_PRICE,LD_ME_NUM,LD_RG_NUM) VALUES
("안좋아영","서울특별시 강남구 테헤란로 130","모텔","010-0000-0000",1,
"
[객실정보]
에어컨이 설치된 11개의 객실에는 바닥 난방/온돌 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
알아서 드셈

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
중국어(만다린어)
한국어
영어
일본어",
30000,
1,1
),

("개좋아영","서울특별시 강남구 테헤란로 131","모텔","010-0000-0000",5,
"
[객실정보]
에어컨이 설치된 101개의 객실에는 바닥 난방/온돌 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
호텔에 있는 커피숍/카페에서는 간단하고 맛있는 음식을 즐기실 수 있습니다. 아침 식사(뷔페)를 매일 07:00 ~ 09:00에 유료로 이용하실 수 있습니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
중국어(만다린어)
한국어
영어
일본어",
30000,
1,1
),

("그냥그런호텔","서울특별시 강남구 테헤란로 133","호텔","010-0000-0000",2,
"
[객실정보]
에어컨이 설치된 401개의 객실에는 바닥 난방/온돌 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
호텔에 있는 커피숍/카페에서는 간단하고 맛있는 음식을 즐기실 수 있습니다. 아침 식사(뷔페)를 매일 07:00 ~ 09:00에 유료로 이용하실 수 있습니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
한국어
영어
일본어",
30000,
1,1
),

("개좋은호텔","서울특별시 강남구 테헤란로 134","호텔","010-0000-0000",5,
"
[객실정보]
에어컨이 설치된 1001개의 객실에는 바닥 난방/온돌 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
호텔에 있는 커피숍/카페에서는 간단하고 맛있는 음식을 즐기실 수 있습니다. 아침 식사(뷔페)를 매일 07:00 ~ 09:00에 유료로 이용하실 수 있습니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.
너무너무 좋아영

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
한국어
영어
흰두어
일본어",
30000,
1,1
),
('JW 메리어트 호텔 서울', '서울특별시 서초구 신반포로 176', '호텔', '010-1111-1111', 5,
'
[객실정보]
에어컨이 설치된 379개의 객실에는 바닥 난방 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
호텔에 있는 커피숍/카페에서는 간단하고 맛있는 음식을 즐기실 수 있습니다. 아침 식사(뷔페)를 매일 06:30 ~ 10:00에 유료로 이용하실 수 있습니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
중국어(만다린어)
한국어
영어
일본어', 30000, 1, 1),

('그랜드 하얏트 서울', '서울특별시 용산구 소월로 322', '호텔', '010-1111-1111', 5,
'
[객실정보]
에어컨이 설치된 615개의 객실에는 바닥 난방 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
호텔에 있는 커피숍/카페에서는 간단하고 맛있는 음식을 즐기실 수 있습니다. 아침 식사(뷔페)를 매일 06:30 ~ 10:00에 유료로 이용하실 수 있습니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
중국어(만다린어)
한국어
영어
일본어', 30000, 1, 1),

('노보텔 앰배서더 서울 동대문 호텔 & 레지던스', '서울특별시 중구 을지로 238', '호텔', '010-1111-1111', 4,
'
[객실정보]
에어컨이 설치된 523개의 객실에는 바닥 난방 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
호텔에 있는 커피숍/카페에서는 간단하고 맛있는 음식을 즐기실 수 있습니다. 아침 식사(뷔페)를 매일 06:30 ~ 10:00에 유료로 이용하실 수 있습니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
중국어(만다린어)
한국어
영어
일본어', 30000, 1, 1),

('라마다 앙코르 바이 윈덤 부산 해운대', '부산광역시 해운대구 해운대해변로 9', '호텔', '010-1111-1111', 4,
'
[객실정보]
에어컨이 설치된 402개의 객실에는 바닥 난방 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
호텔에 있는 커피숍/카페에서는 간단하고 맛있는 음식을 즐기실 수 있습니다. 아침 식사(뷔페)를 매일 06:30 ~ 10:00에 유료로 이용하실 수 있습니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
중국어(만다린어)
한국어
영어
일본어', 30000, 1, 3),

('파라다이스 호텔 부산', '부산광역시 해운대구 해운대해변로 296', '호텔', '010-1111-1111', 5,
'
[객실정보]
에어컨이 설치된 532개의 객실에는 바닥 난방 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
호텔에 있는 커피숍/카페에서는 간단하고 맛있는 음식을 즐기실 수 있습니다. 아침 식사(뷔페)를 매일 06:30 ~ 10:00에 유료로 이용하실 수 있습니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
중국어(만다린어)
한국어
영어
일본어', 30000, 1, 3),

('시그니엘 부산', '부산광역시 해운대구 달맞이길 30', '호텔', '010-1111-1111', 5,
'
[객실정보]
에어컨이 설치된 260개의 객실에는 바닥 난방 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
호텔에 있는 커피숍/카페에서는 간단하고 맛있는 음식을 즐기실 수 있습니다. 아침 식사(뷔페)를 매일 06:30 ~ 10:00에 유료로 이용하실 수 있습니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
중국어(만다린어)
한국어
영어
일본어', 30000, 1, 3),

('더 노벰버 스테이 구리역점', '경기도 구리시 건원대로76번길 57', '호텔', '010-1111-1111', 3,
'
[객실정보]
에어컨이 설치된 100개의 객실에는 바닥 난방 및 평면 TV도 갖추어져 있어 편하게 머무실 수 있습니다. 유선 및 무선 인터넷이 무료로 제공되며 케이블 채널 프로그램도 구비되어 있어 지루하지 않게 시간을 보내실 수 있습니다. 편의 시설/서비스로는 금고 및 책상 등이 있으며 객실 정돈 서비스는 매일 제공됩니다.

[식사정보]
호텔에 있는 커피숍/카페에서는 간단하고 맛있는 음식을 즐기실 수 있습니다. 아침 식사(뷔페)를 매일 06:30 ~ 10:00에 유료로 이용하실 수 있습니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
대표적인 편의 시설과 서비스로는 무료 유선 인터넷, 드라이클리닝/세탁 서비스, 24시간 운영되는 프런트 데스크 등이 있습니다.

[서비스 지원 언어]
중국어'
, 30000, 1, 3),

('호텔야경 수원점', '경기도 수원시 팔달구 덕영대로 924번길 25', '호텔', '010-1111-1111', 4,
'
[객실정보]
에어컨과 냉장고가 구비된 120개의 객실에는 바닥 난방 및 고급 침구가 제공되며 편안한 숙박을 보장합니다. 무료 와이파이와 케이블 TV도 제공됩니다. 매일 하우스키핑이 이루어지며, 요청 시 추가 침구 제공이 가능합니다.

[식사정보]
호텔 1층 카페에서 가벼운 브런치와 음료를 제공합니다. 주변에 다양한 맛집이 있어 외식도 편리합니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.
비즈니스 편의시설
비즈니스 센터, 회의실, 무료 주차장이 제공됩니다. 셀프 체크인 가능.
서비스 지원 언어
한국어
영어
중국어(간체)
일본어', 30000, 1, 2),

('베스트웨스턴 플러스 호텔 분당', '경기도 성남시 분당구 황새울로 105번길 19', '호텔', '010-1111-1111', 5,
'
[객실정보]
총 150개의 세련된 객실에는 에어컨, 미니바, 책상, 평면 TV가 구비되어 있으며 고급 침구와 온돌 바닥이 포함되어 편안한 휴식을 제공합니다. 전 객실 와이파이 무료.

[식사정보]
호텔 내 레스토랑에서 조식 뷔페 이용 가능 (06:30~09:30). 커피숍도 상시 운영됩니다.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
24시간 프런트, 피트니스 센터, 무료 주차 및 미팅룸 제공.

[서비스 지원 언어]
한국어
영어
중국어
일본어', 30000, 1, 2),

('더숨 포레스트 호텔', '경기도 용인시 처인구 포곡읍 성산로 633', '호텔', '010-1111-1111', 4,
'
[객실정보]
자연 속에서 휴식할 수 있는 80개의 객실은 바닥 난방과 에어컨, 대형 창이 설치되어 있으며 조용한 환경을 제공합니다. 전 객실 무료 와이파이 제공.

[식사정보]
조식 뷔페는 오전 7시부터 9시까지 제공되며, 한식과 양식 선택 가능. 야외 테라스 카페도 운영 중.

[공인등급]
고객 편의를 위해 저희 등급 시스템을 기준으로 해당 정보를 제공했습니다.

[비즈니스 편의시설]
비즈니스룸, 주차장, 셀프 빨래방, 픽업 서비스 제공.

[서비스 지원 언어]
한국어
영어
중국어
일본어', 30000, 1, 2);


INSERT INTO room(RM_NAME,RM_PRICE,RM_PERSON,RM_INFOR,RM_ROOMCOUNT,RM_LD_NUM) VALUES
("1호실","10000","3","앙~~~","1","1"),
("2호실","10000","3","앙~~~","1","1"),
("1호실","20000","3","앙~~~","1","2"),
("2호실","20000","3","앙~~~","1","2"),
("1호실","30000","3","앙~~~","1","3"),
("2호실","30000","3","앙~~~","1","3"),
("1호실","40000","3","앙~~~","1","4"),
("2호실","40000","3","앙~~~","1","4"),
("1호실","50000","3","앙~~~","1","5"),
("2호실","50000","3","앙~~~","1","5"),
-- LD_NUM 5
('1호실', 30000, 2, '에어컨과 깔끔한 침대 구비', 1, 5),
('2호실', 35000, 3, '트윈베드와 욕조 완비', 1, 5),
-- LD_NUM 6
('1호실', 32000, 2, '에어컨 및 미니바 포함', 1, 6),
('2호실', 37000, 4, '가족형 객실, 발코니 있음', 1, 6),
-- LD_NUM 7
('1호실', 31000, 2, '도심 뷰, 에어컨 완비', 1, 7),
('2호실', 36000, 3, '비즈니스용 책상 포함', 1, 7),
-- LD_NUM 8
('1호실', 30000, 2, '부산 바다 뷰 룸', 1, 8),
('2호실', 33000, 4, '패밀리 룸, 넓은 공간', 1, 8),
-- LD_NUM 9
('1호실', 34000, 2, '프리미엄 침대와 욕실', 1, 9),
('2호실', 39000, 4, '전망 좋은 테라스 룸', 1, 9),
-- LD_NUM 10
('1호실', 29000, 2, '심플한 디자인의 싱글룸', 1, 10),
('2호실', 31000, 3, '트윈룸, 에어컨 완비', 1, 10),
-- LD_NUM 11
('1호실', 28000, 2, '기본형 스탠다드룸', 1, 11),
('2호실', 33000, 4, '가족형 룸, 전자레인지 포함', 1, 11),
-- LD_NUM 12
('1호실', 27000, 2, '조용한 공간, 책상 구비', 1, 12),
('2호실', 30000, 3, '트윈룸, 욕실 포함', 1, 12),
-- LD_NUM 13
('1호실', 35000, 2, '세련된 인테리어와 발코니', 1, 13),
('2호실', 39000, 4, '럭셔리 룸, 욕조 포함', 1, 13),
-- LD_NUM 14
('1호실', 31000, 2, '자연 속에 위치한 조용한 룸', 1, 14),
('2호실', 34000, 3, '힐링뷰 포함, 테라스 있음', 1, 14);

INSERT INTO thumbnail(TH_TABLE,TH_KEY,TH_UPLOAD,TH_NAME) VALUES
("lodging",1,"/resources/static/img/","ho1.jpg"),
("lodging",1,"/resources/static/img/","ho2.jpg"),
("lodging",1,"/resources/static/img/","ho3.jpg"),
("lodging",1,"/resources/static/img/","ho4.jpg"),
("lodging",1,"/resources/static/img/","ho5.jpg"),
("room",1,"/resources/static/img/","ho2.jpg"),
("room",1,"/resources/static/img/","ho3.jpg"),
("room",1,"/resources/static/img/","ho4.jpg"),
("room",2,"/resources/static/img/","ho2.jpg"),
("room",2,"/resources/static/img/","ho3.jpg"),
("room",2,"/resources/static/img/","ho4.jpg"),
("lodging",2,"/resources/static/img/","ho1.jpg"),
("lodging",2,"/resources/static/img/","ho2.jpg"),
("lodging",2,"/resources/static/img/","ho3.jpg"),
("lodging",2,"/resources/static/img/","ho4.jpg"),
("lodging",2,"/resources/static/img/","ho5.jpg"),
("room",3,"/resources/static/img/","ho2.jpg"),
("room",3,"/resources/static/img/","ho3.jpg"),
("room",3,"/resources/static/img/","ho4.jpg"),
("room",4,"/resources/static/img/","ho2.jpg"),
("room",4,"/resources/static/img/","ho3.jpg"),
("room",4,"/resources/static/img/","ho4.jpg"),
("lodging",3,"/resources/static/img/","ho1.jpg"),
("lodging",3,"/resources/static/img/","ho2.jpg"),
("lodging",3,"/resources/static/img/","ho3.jpg"),
("lodging",3,"/resources/static/img/","ho4.jpg"),
("lodging",3,"/resources/static/img/","ho5.jpg"),
("room",5,"/resources/static/img/","ho2.jpg"),
("room",5,"/resources/static/img/","ho3.jpg"),
("room",5,"/resources/static/img/","ho4.jpg"),
("room",6,"/resources/static/img/","ho2.jpg"),
("room",6,"/resources/static/img/","ho3.jpg"),
("room",6,"/resources/static/img/","ho4.jpg"),
("lodging",4,"/resources/static/img/","ho1.jpg"),
("lodging",4,"/resources/static/img/","ho2.jpg"),
("lodging",4,"/resources/static/img/","ho3.jpg"),
("lodging",4,"/resources/static/img/","ho4.jpg"),
("lodging",4,"/resources/static/img/","ho5.jpg"),
("room",7,"/resources/static/img/","ho2.jpg"),
("room",7,"/resources/static/img/","ho3.jpg"),
("room",7,"/resources/static/img/","ho4.jpg"),
("room",8,"/resources/static/img/","ho2.jpg"),
("room",8,"/resources/static/img/","ho3.jpg"),
("room",8,"/resources/static/img/","ho4.jpg"),
("lodging",5,"/resources/static/img/","ho1.jpg"),
("lodging",5,"/resources/static/img/","ho2.jpg"),
("lodging",5,"/resources/static/img/","ho3.jpg"),
("lodging",5,"/resources/static/img/","ho4.jpg"),
("lodging",5,"/resources/static/img/","ho5.jpg"),
("room",9,"/resources/static/img/","ho2.jpg"),
("room",9,"/resources/static/img/","ho3.jpg"),
("room",9,"/resources/static/img/","ho4.jpg"),
("room",10,"/resources/static/img/","ho2.jpg"),
("room",10,"/resources/static/img/","ho3.jpg"),
("room",10,"/resources/static/img/","ho4.jpg"),
("lodging",6,"/resources/static/img/","ho1.jpg"),
("lodging",6,"/resources/static/img/","ho2.jpg"),
("lodging",6,"/resources/static/img/","ho3.jpg"),
("lodging",6,"/resources/static/img/","ho4.jpg"),
("lodging",6,"/resources/static/img/","ho5.jpg"),
("room",11,"/resources/static/img/","ho2.jpg"),
("room",11,"/resources/static/img/","ho3.jpg"),
("room",11,"/resources/static/img/","ho4.jpg"),
("room",12,"/resources/static/img/","ho2.jpg"),
("room",12,"/resources/static/img/","ho3.jpg"),
("room",12,"/resources/static/img/","ho4.jpg"),
("lodging",7,"/resources/static/img/","ho1.jpg"),
("lodging",7,"/resources/static/img/","ho2.jpg"),
("lodging",7,"/resources/static/img/","ho3.jpg"),
("lodging",7,"/resources/static/img/","ho4.jpg"),
("lodging",7,"/resources/static/img/","ho5.jpg"),
("room",13,"/resources/static/img/","ho2.jpg"),
("room",13,"/resources/static/img/","ho3.jpg"),
("room",13,"/resources/static/img/","ho4.jpg"),
("room",14,"/resources/static/img/","ho2.jpg"),
("room",14,"/resources/static/img/","ho3.jpg"),
("room",14,"/resources/static/img/","ho4.jpg"),
("lodging",8,"/resources/static/img/","ho1.jpg"),
("lodging",8,"/resources/static/img/","ho2.jpg"),
("lodging",8,"/resources/static/img/","ho3.jpg"),
("lodging",8,"/resources/static/img/","ho4.jpg"),
("lodging",8,"/resources/static/img/","ho5.jpg"),
("room",15,"/resources/static/img/","ho2.jpg"),
("room",15,"/resources/static/img/","ho3.jpg"),
("room",15,"/resources/static/img/","ho4.jpg"),
("room",16,"/resources/static/img/","ho2.jpg"),
("room",16,"/resources/static/img/","ho3.jpg"),
("room",16,"/resources/static/img/","ho4.jpg"),
("lodging",9,"/resources/static/img/","ho1.jpg"),
("lodging",9,"/resources/static/img/","ho2.jpg"),
("lodging",9,"/resources/static/img/","ho3.jpg"),
("lodging",9,"/resources/static/img/","ho4.jpg"),
("lodging",9,"/resources/static/img/","ho5.jpg"),
("room",17,"/resources/static/img/","ho2.jpg"),
("room",17,"/resources/static/img/","ho3.jpg"),
("room",17,"/resources/static/img/","ho4.jpg"),
("room",18,"/resources/static/img/","ho2.jpg"),
("room",18,"/resources/static/img/","ho3.jpg"),
("room",18,"/resources/static/img/","ho4.jpg"),
("lodging",10,"/resources/static/img/","ho1.jpg"),
("lodging",10,"/resources/static/img/","ho2.jpg"),
("lodging",10,"/resources/static/img/","ho3.jpg"),
("lodging",10,"/resources/static/img/","ho4.jpg"),
("lodging",10,"/resources/static/img/","ho5.jpg"),
("room",19,"/resources/static/img/","ho2.jpg"),
("room",19,"/resources/static/img/","ho3.jpg"),
("room",19,"/resources/static/img/","ho4.jpg"),
("room",20,"/resources/static/img/","ho2.jpg"),
("room",20,"/resources/static/img/","ho3.jpg"),
("room",20,"/resources/static/img/","ho4.jpg"),
("lodging",11,"/resources/static/img/","ho1.jpg"),
("lodging",11,"/resources/static/img/","ho2.jpg"),
("lodging",11,"/resources/static/img/","ho3.jpg"),
("lodging",11,"/resources/static/img/","ho4.jpg"),
("lodging",11,"/resources/static/img/","ho5.jpg"),
("room",21,"/resources/static/img/","ho2.jpg"),
("room",21,"/resources/static/img/","ho3.jpg"),
("room",21,"/resources/static/img/","ho4.jpg"),
("room",22,"/resources/static/img/","ho2.jpg"),
("room",22,"/resources/static/img/","ho3.jpg"),
("room",22,"/resources/static/img/","ho4.jpg"),
("lodging",12,"/resources/static/img/","ho1.jpg"),
("lodging",12,"/resources/static/img/","ho2.jpg"),
("lodging",12,"/resources/static/img/","ho3.jpg"),
("lodging",12,"/resources/static/img/","ho4.jpg"),
("lodging",12,"/resources/static/img/","ho5.jpg"),
("room",23,"/resources/static/img/","ho2.jpg"),
("room",23,"/resources/static/img/","ho3.jpg"),
("room",23,"/resources/static/img/","ho4.jpg"),
("room",24,"/resources/static/img/","ho2.jpg"),
("room",24,"/resources/static/img/","ho3.jpg"),
("room",24,"/resources/static/img/","ho4.jpg"),
("lodging",13,"/resources/static/img/","ho1.jpg"),
("lodging",13,"/resources/static/img/","ho2.jpg"),
("lodging",13,"/resources/static/img/","ho3.jpg"),
("lodging",13,"/resources/static/img/","ho4.jpg"),
("lodging",13,"/resources/static/img/","ho5.jpg"),
("room",25,"/resources/static/img/","ho2.jpg"),
("room",25,"/resources/static/img/","ho3.jpg"),
("room",25,"/resources/static/img/","ho4.jpg"),
("room",26,"/resources/static/img/","ho2.jpg"),
("room",26,"/resources/static/img/","ho3.jpg"),
("room",26,"/resources/static/img/","ho4.jpg"),
("lodging",14,"/resources/static/img/","ho1.jpg"),
("lodging",14,"/resources/static/img/","ho2.jpg"),
("lodging",14,"/resources/static/img/","ho3.jpg"),
("lodging",14,"/resources/static/img/","ho4.jpg"),
("lodging",14,"/resources/static/img/","ho5.jpg"),
("room",27,"/resources/static/img/","ho2.jpg"),
("room",27,"/resources/static/img/","ho3.jpg"),
("room",27,"/resources/static/img/","ho4.jpg"),
("room",28,"/resources/static/img/","ho2.jpg"),
("room",28,"/resources/static/img/","ho3.jpg"),
("room",28,"/resources/static/img/","ho4.jpg");

DELETE FROM lo_reservation;
ALTER TABLE lo_reservation AUTO_INCREMENT = 1;

-- 샘플 INSERT
INSERT INTO lo_reservation (
  LR_CHECKIN, LR_CHECKOUT, LR_COUNT, LR_TOTAL_PRICE, LR_STATE,
  LR_DATE, LR_BOOKER_NAME, LR_GUEST_NAME, LR_GUEST_NUMBER,
  LR_MEAL_STATE, LR_ME_NUM, LR_RM_NUM
) VALUES
('2025-06-01 00:00:00', '2025-06-02 00:00:00', 2, 220000, '예약완료',
 NOW(), '홍길동', '홍길동', '010-1111-1111', 'Y', 1, 1),

('2025-06-14 00:00:00', '2025-06-16 00:00:00', 3, 330000, '예약완료',
 NOW(), '김철수', '김철수', '010-2222-2222', 'N', 2, 2),

('2025-06-18 00:00:00', '2025-06-20 00:00:00', 2, 200000, '환불완료',
 NOW(), '이영희', '이영희', '010-3333-3333', 'Y', 3, 3),

('2025-06-21 00:00:00', '2025-06-22 00:00:00', 1, 120000, '예약취소',
 NOW(), '박민수', '박민수', '010-4444-4444', 'N', 4, 4),

('2025-06-25 00:00:00', '2025-06-27 00:00:00', 2, 250000, '예약완료',
 NOW(), '최예린', '최예린', '010-5555-5555', 'Y', 5, 5);