package scherbatyuk.shops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scherbatyuk.shops.dao.ProductRepository;
import scherbatyuk.shops.domain.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAllPeriodicals(){
		return productRepository.findAll();
	}
}
