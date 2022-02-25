create table products
(
    uniq_id                varchar(255) not null,
    sku                    varchar(255),
    brand                  varchar(255),
    category               varchar(255),
    category_tree          varchar(255),
    description            varchar(10000),
    name_title             varchar(255),
    product_image_urls     varchar(5000),
    product_url            varchar(500),
    average_product_rating varchar(255),
    reviews                varchar(11000),
    total_number_reviews   varchar(255),
    sale_price             varchar(255),
    list_price             varchar(255),

    primary key (uniq_id)
)

-- uniq_id,
-- sku,
-- name_title,
-- description,
-- list_price,
-- sale_price,
-- category,
-- category_tree,
-- average_product_rating,
-- product_url,
-- product_image_urls,
-- brand,
-- total_number_reviews,
-- Reviews