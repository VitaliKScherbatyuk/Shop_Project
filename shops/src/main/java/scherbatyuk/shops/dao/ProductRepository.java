package scherbatyuk.shops.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import scherbatyuk.shops.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
