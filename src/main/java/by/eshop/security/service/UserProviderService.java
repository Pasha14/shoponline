package by.eshop.security.service;

import by.eshop.domain.Buyer;
import by.eshop.domain.Role;
import by.eshop.repository.BuyerRepository;
import by.eshop.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProviderService implements UserDetailsService {

    private final BuyerRepository buyerRepository;

    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<Buyer> searchResult = Optional.ofNullable(buyerRepository.findByLogin(username));
            if (searchResult.isPresent()) {
                Buyer buyer = searchResult.get();
                return new org.springframework.security.core.userdetails.User(
                        buyer.getLogin(),
                        buyer.getPassword(),
//                        ["ROLE_USER", "ROLE_ADMIN"]
                        AuthorityUtils.commaSeparatedStringToAuthorityList(roleRepository.getUserRoles(buyer).stream().map(Role::getRoleName).collect(Collectors.joining(",")))
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with login '%s'.", username));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }
}
