package fontys.sem3.school.accesoryweb.controller;

import fontys.sem3.school.accesoryweb.business.CreateAdminUseCase;

import fontys.sem3.school.accesoryweb.domain.CreateAdminRequest;
import fontys.sem3.school.accesoryweb.domain.CreateAdminResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AdminController {
    private final CreateAdminUseCase createAdminUseCase;
    @PostMapping
    public ResponseEntity<CreateAdminResponse> createAdmin (@RequestBody @Valid CreateAdminRequest request){
        CreateAdminResponse response = createAdminUseCase.createAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
