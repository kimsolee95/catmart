package com.shoptest.catmart.member.controller;

import com.shoptest.catmart.member.dto.MemberInputDto;
import com.shoptest.catmart.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping(value = "/join")
  public String memberJoin(Model model) {

    return "member/member_join";
  }

  @PostMapping(value= "/join")
  public String memberJoinSubmit(Model model, MemberInputDto parameter) {

    boolean result = memberService.joinMember(parameter);
    return "member/login"; //회원가입 후 로그인 페이지 리턴으로 변경
  }

  @GetMapping(value= "/login")
  public String memberLogin() {

    return "member/login";
  }

  @GetMapping(value = "/login/fail")
  public String memberLoginFail(Model model) {

    String msg = "아이디 혹은 비밀번호를 확인해주세요.";
    model.addAttribute("errorMessage", msg);

    return "member/login";
  }

}
