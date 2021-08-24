package by.eshop.controller.rest.hibernate;

import by.eshop.domain.hibernate.HibernateBuyer;
import by.eshop.repository.hibernate.HibernateBuyerRepository;
import by.eshop.repository.springData.BuyerDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hibernate")
@RequiredArgsConstructor
public class HibernateUserController {

    private final HibernateBuyerRepository buyerRepository;

    private final BuyerDataRepository buyerDataRepository;

    @GetMapping
    public Page<HibernateBuyer> findAll(){
    return buyerDataRepository.findAll(PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "id")));
    }


}
