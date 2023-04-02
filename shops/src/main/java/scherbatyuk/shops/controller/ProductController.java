package scherbatyuk.shops.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import scherbatyuk.shops.domain.Product;
import scherbatyuk.shops.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView createProduct(@Valid @ModelAttribute("product") Product product,
			BindingResult bindingResult) {
		productService.save(product);
		return new ModelAndView("redirect:/home");
	}
}
