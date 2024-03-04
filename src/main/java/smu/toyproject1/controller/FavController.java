package smu.toyproject1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import smu.toyproject1.dto.FavDTO;
import smu.toyproject1.service.FavService;

@Controller
public class FavController {
    private final FavService favService;

    public FavController(FavService favService) {
        this.favService = favService;
    }

    @PostMapping("/favorite")
    public String addFavorite(@RequestParam("userEmail") String userEmail, @RequestParam("productID") Long productId) {
        FavDTO favDTO = new FavDTO();
        favDTO.setMemberEmail(userEmail);
        favDTO.setProduct_id(productId);
        favService.save(favDTO);
        return "mypage"; // 적절한 뷰로 리다이렉트
    }
}