CREATE TABLE orders (
  order_id SERIAL PRIMARY KEY,
  customer_name VARCHAR(255),
  customer_contact VARCHAR(255),
  shipping_address VARCHAR(255),
  grand_total NUMERIC,
  order_date DATE
);

CREATE TABLE products (
  product_id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  category VARCHAR(255),
  weight NUMERIC,
  price NUMERIC,
  creation_date DATE
);

CREATE TABLE items (
  item_id SERIAL PRIMARY KEY,
  order_id INT REFERENCES orders(order_id),
  product_id INT REFERENCES products(product_id),
  cost NUMERIC,
  shipping_fee NUMERIC,
  tax_amount NUMERIC
);
