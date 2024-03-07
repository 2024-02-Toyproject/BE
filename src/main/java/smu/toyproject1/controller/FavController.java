package smu.toyproject1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import smu.toyproject1.dto.FavDTO;
import smu.toyproject1.service.FavService;

@Controller
@RequiredArgsConstructor
public class FavController {
    private final FavService favService;

    @GetMapping("/fav/save")
    public String showSaveForm() {
        return "favSaveForm"; // 관심상품 등록 폼으로 이동하는 뷰의 이름을 명확히 합니다.
    }

    @PostMapping("/fav/save")
    public String save(@ModelAttribute FavDTO favDTO) {
        favService.save(favDTO);
        return "mypage"; // 성공적으로 저장 후 마이페이지로 리다이렉트합니다.
    }
}
