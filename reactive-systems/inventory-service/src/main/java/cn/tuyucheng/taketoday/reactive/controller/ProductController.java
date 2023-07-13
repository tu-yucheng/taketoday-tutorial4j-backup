package cn.tuyucheng.taketoday.reactive.controller;

import cn.tuyucheng.taketoday.domain.Product;
import cn.tuyucheng.taketoday.reactive.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public Flux<Product> getAllProducts() {
		LOGGER.info("Get all products invoked.");
		return productService.getProducts();
	}

}