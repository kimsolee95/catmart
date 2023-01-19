package com.shoptest.catmart.member.controller;

import com.shoptest.catmart.common.model.ResponseResult;
import com.shoptest.catmart.member.dto.MemberInputDto;
import com.shoptest.catmart.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiMemberController {

  private final MemberService memberService;

  /* 회원가입용 api -> dto parameter validate exception 때문에 form 전송 대신 api 사용해서 전송하는 것으로 수정 */
  @PostMapping("/api/members")
  public ResponseEntity<?> memberJoin(Model model, @RequestBody @Valid MemberInputDto parameter) {

    boolean result = memberService.joinMember(parameter);
    ResponseResult responseResult = new ResponseResult(true);

    return ResponseEntity.ok().body(responseResult);
  }



}
