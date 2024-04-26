package smu.toyproject1.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import smu.toyproject1.dto.MemberDTO;
import smu.toyproject1.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public ResponseEntity<?> save(@RequestBody MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);

        // ResponseEntity를 사용하여 HTTP 응답을 보다 유연하게 설정할 수 있습니다.
        // 예를 들어, 성공적으로 저장되었다는 메시지와 함께 HTTP 상태 코드 200(OK)을 반환할 수 있습니다.
        return ResponseEntity.ok("Member information has been successfully saved.");
    }

    @GetMapping("/member/login")
    public String loginForm() {
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
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/member/myPage")
    public String myPage(HttpSession session, Model model) {
        // 세션에서 사용자 정보를 가져옵니다. 실제 구현에서는 로그인 상태 확인 등의 처리가 필요할 수 있습니다.
        String loginName = (String) session.getAttribute("loginName");
        if(loginName == null) {
            // 로그인하지 않은 사용자의 경우 로그인 페이지로 리다이렉트 처리
            return "redirect:/member/login";
        }

        return "mypage"; // mypage.html을 뷰로 사용
    }
}
