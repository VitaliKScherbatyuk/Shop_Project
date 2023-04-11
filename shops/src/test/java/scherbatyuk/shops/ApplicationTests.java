package scherbatyuk.shops;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import scherbatyuk.shops.dao.BucketRepository;
import scherbatyuk.shops.dao.ProductRepository;
import scherbatyuk.shops.dao.UserRepository;
import scherbatyuk.shops.domain.Bucket;
import scherbatyuk.shops.domain.Product;
import scherbatyuk.shops.domain.User;
import scherbatyuk.shops.domain.UserRole;
import scherbatyuk.shops.service.BucketService;
import scherbatyuk.shops.service.ProductService;
import scherbatyuk.shops.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BucketService bucketService;

	@Autowired
	private BucketRepository bucketRepository;

	@Test
	public void testSaveUser() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User userFromDb = users.get(0);
		assertTrue(userFromDb.getEmail().equals(user.getEmail()));
		assertTrue(userFromDb.getFirstName().equals(user.getFirstName()));
		assertTrue(userFromDb.getLastName().equals(user.getLastName()));
		assertTrue(userFromDb.getRole().equals(user.getRole()));
	}

	@Test
	public void testFindByEmail() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("myCustomEmail@gmail.com");
		user.setFirstName("2");
		user.setLastName("2");
		user.setPassword("2");
		user.setPasswordConfirm("2");
		user.setRole(UserRole.ROLE_USER);

		userRepository.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User findByEmail = userService.findByEmail(user.getEmail());

		assertTrue(findByEmail.getEmail().equals(user.getEmail()));
		assertTrue(findByEmail.getFirstName().equals(user.getFirstName()));
		assertTrue(findByEmail.getLastName().equals(user.getLastName()));
		assertTrue(findByEmail.getRole().equals(user.getRole()));
	}

	@Test
	public void testSavePeriodical() {
		List<Product> products = productRepository.findAll();
		assertThat(products, hasSize(0));

		Product product = new Product();
		product.setName("1");
		product.setDescription("1");
		product.setEncodedImage("1");
		product.setPrice(1d);

		productService.save(product);

		products = productRepository.findAll();
		assertThat(products, hasSize(1));

		Product productFromDb = products.get(0);
		assertTrue(productFromDb.getName().equals(product.getName()));
		assertTrue(productFromDb.getDescription().equals(product.getDescription()));
		assertTrue(productFromDb.getEncodedImage().equals(product.getEncodedImage()));
		assertTrue(productFromDb.getPrice().equals(product.getPrice()));
	}

	@Test
	public void testFindById() {
		List<Product> products = productRepository.findAll();
		assertThat(products, hasSize(0));

		Product product = new Product();
		product.setName("1");
		product.setDescription("1");
		product.setEncodedImage("1");
		product.setPrice(1d);

		productRepository.save(product);

		products = productRepository.findAll();
		assertThat(products, hasSize(1));

		Product productFromDb = productService.findById(products.get(0).getId());

		assertTrue(productFromDb.getName().equals(product.getName()));
		assertTrue(productFromDb.getDescription().equals(product.getDescription()));
		assertTrue(productFromDb.getEncodedImage().equals(product.getEncodedImage()));
		assertTrue(productFromDb.getPrice().equals(product.getPrice()));
	}

	@Test
	public void testGetAllPeriodicals() {
		List<Product> products = productRepository.findAll();
		assertThat(products, hasSize(0));

		Product product = new Product();
		product.setName("1");
		product.setDescription("1");
		product.setEncodedImage("1");
		product.setPrice(1d);

		Product product2 = new Product();
		product2.setName("12");
		product2.setDescription("12");
		product2.setEncodedImage("12");
		product2.setPrice(12d);

		productRepository.saveAll(Arrays.asList(product, product2));

		products = productRepository.findAll();
		assertThat(products, hasSize(2));

		List<Product> productFromDb = productService.getAllProduct();

		assertTrue(productFromDb.get(0).getName().equals(product.getName()));
		assertTrue(productFromDb.get(0).getDescription().equals(product.getDescription()));
		assertTrue(productFromDb.get(0).getEncodedImage().equals(product.getEncodedImage()));
		assertTrue(productFromDb.get(0).getPrice().equals(product.getPrice()));

		assertTrue(productFromDb.get(1).getName().equals(product2.getName()));
		assertTrue(productFromDb.get(1).getDescription().equals(product2.getDescription()));
		assertTrue(productFromDb.get(1).getEncodedImage().equals(product2.getEncodedImage()));
		assertTrue(productFromDb.get(1).getPrice().equals(product2.getPrice()));
	}

	@Test
	public void testAddToBucket() {
		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		User userFromDb = userRepository.findAll().stream().findFirst().get();

		List<Product> products = productRepository.findAll();
		assertThat(products, hasSize(0));

		Product product = new Product();
		product.setName("1");
		product.setDescription("1");
		product.setEncodedImage("1");
		product.setPrice(1d);

		productService.save(product);

		Product productFromDb = productRepository.findAll().stream().findFirst().get();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setProduct(productFromDb);
		bucket.setUser(userFromDb);
		bucket.setPurchaseDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketService.add(bucket);

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(1));

		Bucket bucketFromDb = buckets.get(0);

		assertTrue(bucketFromDb.getProduct().equals(productFromDb));
		assertTrue(bucketFromDb.getUser().equals(userFromDb));
		assertTrue(bucketFromDb.getPurchaseDate().equals(now));
	}

	@Test
	public void testDeleteFromBucket() {
		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		User userFromDb = userRepository.findAll().stream().findFirst().get();

		List<Product> products = productRepository.findAll();
		assertThat(products, hasSize(0));

		Product product = new Product();
		product.setName("1");
		product.setDescription("1");
		product.setEncodedImage("1");
		product.setPrice(1d);

		Product product2 = new Product();
		product2.setName("12");
		product2.setDescription("12");
		product2.setEncodedImage("12");
		product2.setPrice(12d);

		productRepository.saveAll(Arrays.asList(product, product2));

		List<Product> productFromDb = productRepository.findAll();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setProduct(productFromDb.get(0));
		bucket.setUser(userFromDb);
		bucket.setPurchaseDate(now);

		Bucket bucket2 = new Bucket();
		bucket2.setProduct(productFromDb.get(1));
		bucket2.setUser(userFromDb);
		bucket2.setPurchaseDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketRepository.saveAll(Arrays.asList(bucket, bucket2));

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(2));

		bucketService.delete(buckets.get(0));

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(1));
	}

	@Test
	public void testGetAll() {
		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		User userFromDb = userRepository.findAll().stream().findFirst().get();

		List<Product> products = productRepository.findAll();
		assertThat(products, hasSize(0));

		Product product= new Product();
		product.setName("1");
		product.setDescription("1");
		product.setEncodedImage("1");
		product.setPrice(1d);

		Product product2 = new Product();
		product2.setName("12");
		product2.setDescription("12");
		product2.setEncodedImage("12");
		product2.setPrice(12d);

		productRepository.saveAll(Arrays.asList(product, product2));

		List<Product> productFromDb = productRepository.findAll();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setProduct(productFromDb.get(0));
		bucket.setUser(userFromDb);
		bucket.setPurchaseDate(now);

		Bucket bucket2 = new Bucket();
		bucket2.setProduct(productFromDb.get(1));
		bucket2.setUser(userFromDb);
		bucket2.setPurchaseDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketRepository.saveAll(Arrays.asList(bucket, bucket2));

		List<Bucket> bucketsFromDb = bucketService.getAll();
		assertThat(bucketsFromDb, hasSize(2));
	}

}
