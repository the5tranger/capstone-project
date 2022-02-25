package ex.microservices.catalogservice.util;

import ex.microservices.catalogservice.model.Product;

import java.lang.reflect.Field;

public class CsvReader<T> {
    private final Field[] modelClassFields = Product.class.getFields();

}
