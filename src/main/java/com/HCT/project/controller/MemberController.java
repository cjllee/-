package com.HCT.project.controller;

import com.HCT.project.dto.MemberDTO;
import com.HCT.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm(){
        return "membersave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        int saveResult = memberService.save(memberDTO);
        if(saveResult > 0 ){
            return "memberlogin";
        }
        else{
            return "membersave";
        }

    }

    @GetMapping("/login")
    public String loginForm(){
        return "memberlogin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO,
                        HttpSession session){
        boolean loginResult = memberService.login(memberDTO);

        if(loginResult){
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "main";
        }
        else {
            return "memberlogin";
        }

    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "memberlist";
    }


    @GetMapping
    public String findById(@RequestParam("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "memberdetail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        memberService.delete(id);
        return "redirect:/member/";
    }


    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        return "memberupdate";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        boolean result = memberService.update(memberDTO);
        if (result) {
            return "redirect:/member?id=" + memberDTO.getId();
        } else {
            return "index";
        }
    }

    @PostMapping("/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }


}
