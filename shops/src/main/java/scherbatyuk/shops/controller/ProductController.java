package scherbatyuk.shops.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import scherbatyuk.shops.service.ProductDTOHelper;
import scherbatyuk.shops.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView createProduct(
			@RequestParam MultipartFile image, 
			@RequestParam String name, 
			@RequestParam String description, 
			@RequestParam Double price) throws IOException {		
		
		productService.save(ProductDTOHelper.createEntity(image, name, description, price));
		return new ModelAndView("redirect:/home");
	}
}
