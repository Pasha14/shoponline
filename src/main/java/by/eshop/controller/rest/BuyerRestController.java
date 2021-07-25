package by.eshop.controller.rest;

import by.eshop.controller.requests.BuyerCreateRequest;
import by.eshop.domain.Buyer;
import by.eshop.repository.BuyerRepository;
import by.eshop.util.UserGenerator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyers")
@RequiredArgsConstructor
public class BuyerRestController {

    public final BuyerRepository buyerRepository;
    public final UserGenerator userGenerator;

    @GetMapping
    public List<Buyer> findAll(){
        System.out.println("In rest controller");
        return buyerRepository.findAll();
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
