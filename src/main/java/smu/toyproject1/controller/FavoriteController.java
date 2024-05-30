package smu.toyproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smu.toyproject1.dto.FavoriteRequest;
import smu.toyproject1.service.FavoriteService;

@RestController
@RequestMapping("/depositLike")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<String> saveFavorite(@RequestBody FavoriteRequest request) {
        String memberId = request.getMemberId();
        String bankName = request.getBankName();
        String productName = request.getProductName();

        favoriteService.saveFavorite(memberId, bankName, productName);
        return ResponseEntity.status(HttpStatus.CREATED).body("Favorite saved successfully");
    }
}
