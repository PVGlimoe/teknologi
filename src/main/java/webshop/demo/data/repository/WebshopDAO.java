package webshop.demo.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import webshop.demo.data.mapper.ProductDescriptionMapper;
import webshop.demo.data.mapper.ProductMapper;
import webshop.demo.model.PictureLink;
import webshop.demo.model.Product;
import webshop.demo.model.ProductDescription;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebshopDAO {

    //Man burde nok lave det på seperate, det gør vi også normalt

    private final JdbcTemplate jdbcTemplate;
    private final ProductMapper productMapper = new ProductMapper();
    private final ProductDescriptionMapper productDescriptionMapper = new ProductDescriptionMapper();

    @Autowired
    public WebshopDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void createProduct(Product product, PictureLink pictureLink, ProductDescription productDescription) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT into product (product_name, product_price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            return ps;
        }, keyHolder);

        long productId = keyHolder.getKey().longValue();

        createDescription(productDescription, productId);
        createPictureLink(pictureLink, productId);

    }

        public void createDescription (ProductDescription productDescription, long productId) {
        jdbcTemplate.update("INSERT into product_description (product_id, product_description) VALUES (?, ?)",
                productId,
                productDescription.getDescription()
                );
        }

    public void createPictureLink (PictureLink pictureLink, long productId) {
        jdbcTemplate.update("INSERT into picture_link (product_id, picture_link) VALUES (?, ?)",
                productId,
                pictureLink.getLink()
        );
    }

    public Product getProduct(long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM product WHERE product_id = ?",
                productMapper,
                id
        );
    }

    public List<Product> getListOfProducts(){
        return jdbcTemplate.query(
                "SELECT * FROM product",
                productMapper
        );
    }

    public void updateProduct(long productId){
        Product product = getProduct(productId);
        jdbcTemplate.update("UPDATE product set product_name = ?, product_price = ? WHERE product_id = ?",
                product.getName(),
                product.getPrice(),
                product.getId());

    }

    public ProductDescription getProductDescription (long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM product_description WHERE product_description_id = ?",
                productDescriptionMapper
        );
    }

        public List<ProductDescription> getListOfProductDescription(){
            return jdbcTemplate.query("SELECT * FROM product_description",
                    productDescriptionMapper
            );
        }


        public void test(long id){
            String s = "";
        }
    }









