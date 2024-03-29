package b303.farm.garden.controller;
import b303.farm.garden.service.GardenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/garden")
@RequiredArgsConstructor
public class GardenController {
    private final GardenService gardenService;

    @GetMapping("/all")
    public ResponseEntity<?> getGardenList() {
        return ResponseEntity.ok().body(gardenService.getAllGardens());
    }

    //내가 찜한 레시피 조회
    @GetMapping("/myGardenFavorites/{email}")
    public ResponseEntity<?> getMyFavoriteGardens(@PathVariable String email) {
        return ResponseEntity.ok().body(gardenService.getMyFavoriteGardens(email));
    }

    @PostMapping("/{id}/gardenfavorite/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> favoriteGarden(@PathVariable Long id, @PathVariable String email) {
        return ResponseEntity.ok().body(gardenService.favoriteGarden(id, email));
    }

    @GetMapping("/{id}/checkGardenIsLiked/{email}")
    public Boolean checkGardenIsLiked(@PathVariable Long id, @PathVariable String email) {
        return gardenService.checkGardenIsLiked(id, email);
    }
}
