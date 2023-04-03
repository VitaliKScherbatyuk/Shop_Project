package scherbatyuk.shops.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import scherbatyuk.shops.domain.Product;

public class ProductDTOHelper {
	
	public static Product createEntity(MultipartFile file, String name, String description, Double price) throws IOException {
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setEncodedImage(Base64.getEncoder().encodeToString(file.getBytes()));
		
		return product;
	}
}
