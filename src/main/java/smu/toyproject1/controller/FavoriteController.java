package smu.toyproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.toyproject1.dto.FavoriteRequest;
import smu.toyproject1.entity.Favorite;
import smu.toyproject1.service.FavoriteService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    // 즐겨찾기 저장
    @PostMapping("/depositLike")
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
    public ResponseEntity<List<Favorite>> getFavoritesByMemberId(@PathVariable String memberId) {
        List<Favorite> favorites = favoriteService.findByMemberId(memberId);
        if (favorites.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(favorites);
    }
}
