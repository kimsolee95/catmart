package com.shoptest.catmart.member.domain;

import com.shoptest.catmart.member.type.Kind;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원 도메인
 * */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long memberId;
  
  /* 이메일 */
  private String email;
  
  /* 이름 */
  private String name;
  
  /* 비밀번호 */
  private String password;
  
  /* 주소 */
  private String address;
  
  /* 우편번호 */
  private String zipcode;
  
  /* 상세주소 */
  private String addressDetail;
  
  /* 회원 종류 - 고객 / 판매 및 관리자 */
  @Enumerated(EnumType.STRING)
  private Kind memberKind;
  
  /* 등록일자 */
  private LocalDateTime createdAt;
  
  /* 수정일자 */
  private LocalDateTime modifiedAt;
  
}
