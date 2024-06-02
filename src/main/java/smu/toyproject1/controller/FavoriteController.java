package smu.toyproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.toyproject1.dto.FavoriteRequest;
import smu.toyproject1.entity.Favorite;
import smu.toyproject1.service.FavoriteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    // 즐겨찾기 저장
    @PostMapping("/favorites")
    public ResponseEntity<String> saveFavorite(@RequestBody FavoriteRequest request) {
        String memberId = request.getMemberId();
        String bankName = request.getBankName();
        String productName = request.getProductName();

        // 중복 체크 및 삭제 로직
        boolean isDuplicate = favoriteService.checkDuplicate(memberId, bankName, productName);
        if (isDuplicate) {
            favoriteService.deleteFavorite(memberId, bankName, productName);
            return ResponseEntity.status(HttpStatus.OK).body("Duplicate favorite deleted");
        }

        favoriteService.saveFavorite(memberId, bankName, productName);
        return ResponseEntity.status(HttpStatus.CREATED).body("Favorite saved successfully");
    }

    // 특정 회원의 즐겨찾기 조회
    @GetMapping("/api/favorites/{memberId}")
    public ResponseEntity<List<Map<String, Object>>> getFavoritesByMemberId(@PathVariable String memberId) {
        List<Favorite> favorites = favoriteService.findByMemberId(memberId);
        List<Map<String, Object>> response = new ArrayList<>();
        for (Favorite favorite : favorites) {
            Map<String, Object> map = new HashMap<>();
            map.put("bankName", favorite.getBankName());
            map.put("productName", favorite.getProductName());
            response.add(map);
        }
        return ResponseEntity.ok(response);
    }
}
