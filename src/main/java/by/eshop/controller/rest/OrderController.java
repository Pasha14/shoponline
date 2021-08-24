package by.eshop.controller.rest;

import by.eshop.controller.requests.OrderCreateRequest;
import by.eshop.domain.hibernate.HibernateOrder;
import by.eshop.repository.springData.OrderDataRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/orders")
@RequiredArgsConstructor
@Api(description = "order controller", tags = { "Order" })
public class OrderController {

    private final OrderDataRepository orderDataRepository;

    @GetMapping
    public List<HibernateOrder> findAll(){
        return orderDataRepository.findAll();
    }

    @PostMapping
    public HibernateOrder newOrder(@RequestBody OrderCreateRequest request){
        HibernateOrder newOrder = new HibernateOrder();
        newOrder.setProductId(request.getProductId());
        newOrder.setBuyerId(request.getBuyerId());
        newOrder.setQuantity(request.getQuantity());
        newOrder.setPrice(request.getPrice());
        newOrder.setDeliveryType(request.getDeliveryType());
        newOrder.setPaymentType(request.getPaymentType());
        newOrder.setComment(request.getComment());
        return orderDataRepository.save(newOrder);
    }
}
