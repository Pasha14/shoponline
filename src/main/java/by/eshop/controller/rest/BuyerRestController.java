package by.eshop.controller.rest;

import by.eshop.beans.SecurityConfig;
import by.eshop.controller.requests.BuyerCreateRequest;
import by.eshop.domain.Buyer;
import by.eshop.repository.BuyerRepository;
import by.eshop.security.util.PrincipalUtils;
import by.eshop.util.UserGenerator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/buyers")
@RequiredArgsConstructor
public class BuyerRestController {

    public final BuyerRepository buyerRepository;
    public final UserGenerator userGenerator;
    private final SecurityConfig config;
    private final PrincipalUtils principalUtils;



    @GetMapping
    public List<Buyer> findAll(){
        System.out.println("In rest controller");
        return buyerRepository.findAll();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Secret-Key", dataType = "string", paramType = "header",
            value = "Secret header for secret functionality!! Hoho"),
    @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @GetMapping("/hello")
    public List<Buyer> securedFindAll(HttpServletRequest request, @ApiIgnore Principal principal) {

            String username = principalUtils.getUsername(principal);
            String secretKey = request.getHeader("Secret-Key");

            if (StringUtils.isNotBlank(secretKey) && secretKey.equals(config.getSecretKey())) {
                return Collections.singletonList(buyerRepository.findByLogin(username));
            } else {
            //throw new UnauthorizedException();
                return Collections.emptyList();
            }
    }

    @ApiOperation(value = "Search buyers by query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", dataType = "string", paramType = "query", value = "Limit buyers in result list"),
            @ApiImplicitParam(name = "query", dataType = "string", paramType = "query", value = "Search query"),
    })
    @GetMapping("/search")
    public List<Buyer> buyerSearch(@RequestParam Integer limit, @RequestParam String query){
        return buyerRepository.findBuyersByQuery(limit, query);
    }

    @ApiOperation(value = "Generate auto users in system")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buyerCount", dataType = "string", paramType = "path",
                    value = "Count of generated users", required = true, defaultValue = "1")
    })
    @PostMapping("/generate/{buyerCount}")
    public List<Buyer> generateBuyers(@PathVariable Integer buyerCount){
        throw new RuntimeException("Haha!");
//        List<Buyer> generatedBuyers = userGenerator.generate(buyerCount);
//        buyerRepository.batchInsert(generatedBuyers);
//
//        return buyerRepository.findAll();
    }

    @ApiOperation("Creating one buyers")
    @PostMapping
    public Buyer createBuyer(@RequestBody BuyerCreateRequest buyerCreateRequest){
        Buyer generatedBuyer = userGenerator.generate();
        generatedBuyer.setLogin(buyerCreateRequest.getLogin());
        generatedBuyer.setPassword(buyerCreateRequest.getPassword());
        generatedBuyer.setName(buyerCreateRequest.getName());
        generatedBuyer.setSurname(buyerCreateRequest.getSurname());
        return buyerRepository.save(generatedBuyer);
    }

    @ApiOperation("Update one user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "string", paramType = "query", value = "buyer id")
    })
    @PostMapping("/update")
    public Buyer updateBuyer(@RequestBody BuyerCreateRequest buyerCreateRequest, @RequestParam Long id){
        Buyer foundBuyer = buyerRepository.findOne(id);
        foundBuyer.setLogin(buyerCreateRequest.getLogin());
        foundBuyer.setPassword(buyerCreateRequest.getPassword());
        foundBuyer.setName(buyerCreateRequest.getName());
        foundBuyer.setSurname(buyerCreateRequest.getSurname());

        return buyerRepository.update(foundBuyer);
    }

}
