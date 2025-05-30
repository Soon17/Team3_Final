package kr.kh.team3final.model.dto;

import lombok.Data;

@Data
public class AvailableCarDTO {
  // 렌터카 회사 정보
  private int rentalId;
  private String rentalName;
  private Double avgRating;
  private int reviewCount;
  private Integer rg_num;

  // 차량 정보
  private int carId;
  private int carYear;
  private String carFuel;
  private String carTrans;
  private int carPrice;

  // 썸네일 (car_type 기준)
  private String thumbnail;
}