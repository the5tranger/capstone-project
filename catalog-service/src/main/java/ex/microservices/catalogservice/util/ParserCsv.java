package ex.microservices.catalogservice.util;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParserSettings;
import ex.microservices.catalogservice.model.Product;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParserCsv {
    public static List<Product> parse(InputStream inputStream) {
        List<Product> products = new ArrayList<>();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setMaxCharsPerColumn(100000);
        com.univocity.parsers.csv.CsvParser csvParser = new com.univocity.parsers.csv.CsvParser(settings);
        List<Record> records = csvParser.parseAllRecords(inputStream);
        records.forEach(record -> {
            Product product = new Product();
            product.setProductUrl(record.getString("product_url")); //1
            product.setBrand(record.getString("brand")); //2
            product.setCategory(record.getString("category")); //3
            product.setDescription(record.getString("description")); //4
            product.setCategoryTree(record.getString("category_tree")); //5
            product.setId(record.getString("uniq_id")); //6
            product.setProductImageUrl(record.getString("product_image_urls")); //7
            product.setListPrice(record.getString("list_price")); //8
            product.setName(record.getString("name_title")); //9
            product.setRating(record.getString("average_product_rating")); //10
            product.setSalePrice(record.getString("sale_price")); //11
            product.setReviews(record.getString("Reviews")); //12
            product.setSku(record.getString("sku")); //13
            product.setReviewsCount(record.getString("total_number_reviews")); //14
            products.add(product);
        });
        return products;
    }
}
