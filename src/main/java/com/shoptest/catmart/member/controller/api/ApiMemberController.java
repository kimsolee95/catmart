package com.shoptest.catmart.member.controller.api;

import com.shoptest.catmart.common.model.ResponseResult;
import com.shoptest.catmart.member.dto.MemberInputDto;
import com.shoptest.catmart.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

  @PostMapping("/api/members")
  @ApiResponses({
      @ApiResponse(code=200, message="회원가입 완료"),
      @ApiResponse(code=412, message="중복 이메일 존재 -> 비즈니스적 오류 시 예외 처리 실행"),
  })
  @ApiOperation(value="회원가입 API", notes = "회원 가입 시, 기존에 존재하는 이메일일 경우 예외 처리합니다.")
  public ResponseEntity<?> memberJoin(Model model, @RequestBody @Valid MemberInputDto parameter) {

    boolean result = memberService.joinMember(parameter);
    ResponseResult responseResult = new ResponseResult(true);

    return ResponseEntity.ok().body(responseResult);
  }



}
