package kr.kh.team3final.model.dto;

import lombok.Data;

@Data
public class AvailableCarDTO {
  // 렌터카 회사 정보
  private int rentalId;
  private String rentalName;
  private Double avgRating;
  private int reviewCount;
  private String rg_name;

  // 차량 정보
  private int carId;
  private int carYear;
  private String carFuel;
  private String carTrans;
  private int carPrice;

  private String thumbnail;
}