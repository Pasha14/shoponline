package by.eshop.controller.rest;

import by.eshop.domain.jdbctamplate.Buyer;
import by.eshop.domain.jdbctamplate.Role;
import by.eshop.repository.jdbcTamplate.BuyerRepository;
import by.eshop.repository.jdbcTamplate.RoleRepository;
import by.eshop.security.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final BuyerRepository buyerRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;
    private final RoleRepository roleRepository;

    @PostMapping
    public Buyer createBuyer(@RequestBody Buyer buyer){

        Buyer savedBuyer = buyerRepository.save(buyer);
        List<Role> roles = roleRepository.findAll();
        buyerRepository.saveBuyerRoles(savedBuyer, roles);

        return savedBuyer;

    }
}
