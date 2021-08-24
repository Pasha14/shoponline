package by.eshop.controller.rest;

import by.eshop.controller.requests.BuyerCreateRequest;
import by.eshop.domain.hibernate.HibernateBuyer;
import by.eshop.repository.springData.BuyerDataRepository;
import by.eshop.security.requests.AuthRequest;
import by.eshop.security.requests.AuthResponse;
import by.eshop.security.util.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
@Api(description = "Login and registration controller", tags = { "Login" })
public class LoginAndRegistration {

    private final AuthenticationManager authenticationManager;

    private final TokenUtils tokenUtils;

    private final UserDetailsService userProvider;

    private final BuyerDataRepository buyerDataRepository;

    @ApiOperation(value = "Login user in system", notes = "Return Auth-Token with user login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful authorization"),
            @ApiResponse(code = 400, message = "Request error"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request) {

        /*Check login and password*/
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        /*Generate token with answer to user*/
        return ResponseEntity.ok(
                AuthResponse
                        .builder()
                        .login(request.getLogin())
                        .token(tokenUtils.generateToken(userProvider.loadUserByUsername(request.getLogin())))
                        .build()
        );

    }

    @PostMapping("/registration")
    public HibernateBuyer createBuyer(@RequestBody BuyerCreateRequest request){
        HibernateBuyer newBuyer = new HibernateBuyer();
        newBuyer.setLogin(request.getLogin());
        newBuyer.setPassword(request.getPassword());
        newBuyer.setName(request.getName());
        newBuyer.setSurname(request.getSurname());
        newBuyer.setBirthDate(request.getBirthDate());
        newBuyer.setPhone(request.getPhone());
        newBuyer.setEmail(request.getEmail());
        newBuyer.setPostalCode(request.getPostalCode());
        newBuyer.setCity(request.getCity());
        newBuyer.setAddress(request.getAddress());

        return buyerDataRepository.save(newBuyer);
    }
}
