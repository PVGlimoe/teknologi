package webshop.demo.data.mapper;

import org.springframework.jdbc.core.RowMapper;
import webshop.demo.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {


    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {

        Product product = new Product();
        product.setId(resultSet.getLong("product_id"));
        product.setName(resultSet.getString("product_name"));
        product.setPrice(resultSet.getDouble("product_price"));
        return product;
    }

}
