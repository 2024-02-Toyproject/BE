package smu.toyproject1.controller;

import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.toyproject1.dto.MemberDTO;
import smu.toyproject1.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000") // CORS 설정
@RestController // JSON 데이터 반환을 위해 @RestController 사용
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
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginName", loginResult.getMemberName());
            session.setAttribute("gender", loginResult.getGender());
            session.setAttribute("age", loginResult.getAge());
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return ResponseEntity.ok(loginResult); // 로그인 성공 시 MemberDTO 객체 반환
        } else {
            // login 실패
            return ResponseEntity.badRequest().body("Login failed"); // 로그인 실패 시 에러 메시지 반환
        }
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/member/myPage")
    public ResponseEntity<?> myPage(HttpSession session) {
        // 세션에서 사용자 정보를 가져옵니다.
        String loginName = (String) session.getAttribute("loginName");
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginName == null) {
            // 로그인하지 않은 사용자의 경우 에러 메시지 반환
            return ResponseEntity.badRequest().body("You are not logged in");
        }

        // 사용자 정보를 DTO 객체로 만들어서 반환
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberName(loginName);
        memberDTO.setMemberEmail(loginEmail);
        memberDTO.setAge((String) session.getAttribute("age"));
        memberDTO.setGender((String) session.getAttribute("gender"));


        return ResponseEntity.ok(memberDTO);
    }
}
