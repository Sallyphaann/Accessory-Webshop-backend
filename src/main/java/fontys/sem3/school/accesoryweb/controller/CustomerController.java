package fontys.sem3.school.accesoryweb.controller;

import fontys.sem3.school.accesoryweb.business.CreateCustomerUseCase;
import fontys.sem3.school.accesoryweb.business.GetCustomerUseCase;
import fontys.sem3.school.accesoryweb.business.UpdateCustomerUseCase;
import fontys.sem3.school.accesoryweb.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.school.accesoryweb.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class CustomerController {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer (@RequestBody @Valid CreateCustomerRequest request){
        CreateCustomerResponse response = createCustomerUseCase.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    public ResponseEntity<User> getCustomer(@PathVariable(value = "id") final long id) {
        final Optional<User> customerOptional = getCustomerUseCase.getCustomerById(id);
        if (customerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customerOptional.get());
    }
    @PutMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") long id,
                                              @RequestBody @Valid UpdateCustomerRequest request) {
        request.setId(id);
        updateCustomerUseCase.updateCustomer(request);
        return ResponseEntity.noContent().build();
    }




}
