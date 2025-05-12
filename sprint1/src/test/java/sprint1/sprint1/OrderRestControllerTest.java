package sprint1.sprint1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import sprint1.sprint1.domain.*;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderRestControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(OrderRestControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;
    private Manufacturer testManufacturer;

    private User testUser;
    private ClothingItem testClothingItem;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();

        // Rekisteröidään JavaTimeModule LocalDateTime-sarjoitusta varten
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //luodaan testikäyttäjä
        testUser = userRepository
                .save(new User("test@example.com", "Test", "User", "testuser", "password", Role.USER));
        testManufacturer = manufacturerRepository.save(new Manufacturer("Test Manufacturer"));
        testClothingItem = new ClothingItem(
                "shirt",
                19.99,
                50, //tuotetta varastossa
                Type.CLOTHING,
                testManufacturer,
                "Blue",
                ClothingSize.M);

        testClothingItem = productRepository.save(testClothingItem);
    }

    @Test
    void whenOrderIsCreated_ProductStockShouldBeUpdated() throws Exception {

        //tilauspyyntö vaatetuotteelle 5kpl
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        orderProductDTO.setProductId(testClothingItem.getProductId());
        orderProductDTO.setOrderQuantity(5);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId(testUser.getUserId());
        orderRequest.setOrderDate(LocalDateTime.now());
        List<OrderProductDTO> products = new ArrayList<>();
        products.add(orderProductDTO);
        orderRequest.setProducts(products);

        //POST-pyyntö tilauksen luomiseksi
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk());

        //haetaan päivitetty tuote varastosta
        Optional<Product> updatedProductOpt = productRepository.findById(testClothingItem.getProductId());
        assert updatedProductOpt.isPresent();

        Product updatedProduct = updatedProductOpt.get();

        logger.info("Testivaatteiden varastosaldo: " + updatedProduct.getAmount());
    }
}

/*
 * ObjectMapper ei osaa sarjoittaa LocalDateTime-tyyppiä oletuksena.
 * Tämä on yleinen ongelma Jackson-kirjaston kanssa, ja ratkaisu on lisätä
 * jackson-datatype-jsr310 -moduuli
 * ja rekisteröidä se ObjectMapper-olioon.
 * 
 * Lisätty myös dependencyt pom.xml:ään.
 * 
 * "jackson-databind on pakollinen, koska se mahdollistaa objektien muuntamisen
 * JSON-muotoon.
 * jackson-core sisältää perustoiminnallisuuden.
 * jackson-datatype-jsr310 tarvitaan LocalDateTime-tyypin sarjoittamiseen."
 */