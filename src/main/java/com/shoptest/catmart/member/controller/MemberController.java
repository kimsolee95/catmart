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
    return "index";
  }

}
