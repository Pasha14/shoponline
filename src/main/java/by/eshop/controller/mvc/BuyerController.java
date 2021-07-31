// вместо MVC использую REST
//package by.eshop.controller.mvc;
//
//import by.eshop.controller.requests.BuyerCreateRequest;
//import by.eshop.domain.Buyer;
//import by.eshop.repository.BuyerRepository;
//import by.eshop.util.UserGenerator;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Collections;
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class BuyerController {
//
//    private final BuyerRepository buyerRepository;
//    private final UserGenerator userGenerator;
//
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public ModelAndView helloHandler(){
//
//
//            List<Buyer> buyers = buyerRepository.findAll();
//
//            return new ModelAndView("bye", Collections.singletonMap("buyers", buyers));
//    }
//
////    @RequestMapping(value = "/buyers", method = RequestMethod.POST)
////    public ModelAndView createBuyers(@ModelAttribute BuyerCreateRequest buyerCreateRequest) {
////
////        Buyer generatedBuyer = userGenerator.generate();
////        generatedBuyer.setLogin(buyerCreateRequest.getLogin());
////        generatedBuyer.setPassword(buyerCreateRequest.getPassword());
////        generatedBuyer.setName(buyerCreateRequest.getName());
////        generatedBuyer.setSurname(buyerCreateRequest.getSurname());
////
////        buyerRepository.save(generatedBuyer);
////
////        List<Buyer> buyers = buyerRepository.findAll();
////        return new ModelAndView("bye", Collections.singletonMap("buyers", buyers));
////    }
//
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public ModelAndView buyerSearch(@RequestParam Integer limit, @RequestParam String query){
//
//        List<Buyer> buyers = buyerRepository.findBuyersByQuery(limit, query);
//        return new ModelAndView("bye", Collections.singletonMap("buyers", buyers));
//    }
//
//    @RequestMapping(value = "buyers/generate/{count}", method = RequestMethod.POST)
//    public void generateBuyers(@PathVariable Integer count){
//        List<Buyer> generatedBuyers = userGenerator.generate(count);
//        buyerRepository.batchInsert(generatedBuyers);
//    }
//
//
//}
