package by.eshop.controller.rest;

import by.eshop.beans.TaskGoogleConfig;
import by.eshop.domain.Buyer;
import by.eshop.repository.BuyerRepository;
import by.eshop.util.UserGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class BuyerRestController {

    public final BuyerRepository buyerRepository;
    public final UserGenerator userGenerator;
    public final TaskGoogleConfig taskGoogleConfig;

    @GetMapping
    public List<Buyer> findAll(){
        System.out.println("In rest controller");
        return buyerRepository.findAll();
    }

    @GetMapping("/search")
    public List<Buyer> buyerSearch(@RequestParam Integer limit, @RequestParam String query){
        return buyerRepository.findBuyersByQuery(limit, query);
    }

    @PostMapping("/generate/{buyerCount}")
    public List<Buyer> generateBuyers(@PathVariable Integer buyerCount){
        throw new RuntimeException("Haaha!");
//        List<Buyer> generatedBuyers = userGenerator.generate(buyerCount);
//        buyerRepository.batchInsert(generatedBuyers);
//
//        return buyerRepository.findAll();
    }





}
