package webshop.demo.data.mapper;

import org.springframework.jdbc.core.RowMapper;
import webshop.demo.model.Product;
import webshop.demo.model.ProductDescription;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDescriptionMapper implements RowMapper<ProductDescription> {


    @Override
    public ProductDescription mapRow(ResultSet resultSet, int i) throws SQLException {

        ProductDescription productDescription = new ProductDescription();
        productDescription.setId(resultSet.getLong("product_description_id"));
        productDescription.setDescription(resultSet.getString("product_description"));

        return productDescription;
    }
}
