package by.eshop.repository;

import by.eshop.domain.Buyer;

import java.util.List;

public interface BuyerRepository extends CrudOperations<Long, Buyer>{

    void batchInsert(List<Buyer> buyers);
}
