package b303.farm.garden.controller;

import b303.farm.garden.service.GardenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/garden")
@RequiredArgsConstructor
public class GardenController {
    private final GardenService gardenService;

    @GetMapping("/all")
    public ResponseEntity<?> getGardenList() {
        return ResponseEntity.ok().body(gardenService.getAllGardens());
    }
}
