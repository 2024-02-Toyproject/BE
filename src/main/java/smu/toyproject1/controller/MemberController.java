package smu.toyproject1.controller;

import ch.qos.logback.core.model.Model;
import smu.toyproject1.dto.MemberDTO;
import smu.toyproject1.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }
    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginName", loginResult.getMemberName());
            session.setAttribute("gender", loginResult.getGender());
            session.setAttribute("age", loginResult.getAge());
            return "home";
        } else {
            // login 실패
            return "login";
        }
    }
    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
    @GetMapping("/member/myPage")
    public String myPage() {
        return "mypage";
    }

    @GetMapping("/mypage")
    public String myPage(Model model, HttpSession session) {
        String userEmail = (String) session.getAttribute("loginEmail");
        model.addText("favoriteProductIds");
        return "mypage";
    }
}
