package com.shoptest.catmart.cart.domain;


import com.shoptest.catmart.member.domain.Member;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 장바구니 도메인
 * */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long cartId;

  //foreign key로 memberId 지정하여 컬럼 값 생성
  //(회원 -> 장바구니 1:1 || 회원 1명당 1개의 장바구니를 생성)
  @OneToOne
  @JoinColumn(name = "member_id")
  private Member member;

  /* 등록일자 */
  private LocalDateTime createdAt;

  /* 수정일자 */
  private LocalDateTime modifiedAt;

}
