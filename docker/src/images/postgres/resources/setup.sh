#!/bin/bash

#Run creating sql script
createdb product_sales -U postgres
psql -d product_sales -f ./conf/setup.sql -U postgres

# Generate orders
for i in {1..100}; do
  customer_name="Customer $i"
  customer_contact="contact$i@example.com"
  shipping_address="Address $i"
  grand_total=$((RANDOM % 1000))

  days_ago=$((RANDOM % 731 + 1))
  order_date=$(date -d "-$days_ago days" +%Y-%m-%d)

  psql -d product_sales -c "INSERT INTO orders (customer_name, customer_contact, shipping_address, grand_total, order_date) VALUES ('$customer_name', '$customer_contact', '$shipping_address', $grand_total, '$order_date');" -U postgres
done

# Generate products
categories=("Electronics" "Books" "Clothing" "Home & Kitchen")

for i in {1..50}; do
  name="Product $i"
  category=${categories[$((RANDOM % ${#categories[@]}))]}
  weight=$((RANDOM % 1000))
  price=$((RANDOM % 1000))

  days_ago=$((RANDOM % 731 + 1))
  creation_date=$(date -d "-$days_ago days" +%Y-%m-%d)

  psql -d product_sales -c "INSERT INTO products (name, category, weight, price, creation_date) VALUES ('$name', '$category', $weight, $price, '$creation_date');" -U postgres
done

# Generate items
orders_count=$(psql -d product_sales -t -c "SELECT COUNT(*) FROM orders;" -U postgres)
products_count=$(psql -d product_sales -t -c "SELECT COUNT(*) FROM products;" -U postgres)

for i in {1..100}; do
  order_id=$((RANDOM % orders_count + 1))
  product_id=$((RANDOM % products_count + 1))
  cost=$((RANDOM % 100))
  shipping_fee=$((RANDOM % 50))
  tax_amount=$((RANDOM % 20))

  psql -d product_sales -c "INSERT INTO items (order_id, product_id, cost, shipping_fee, tax_amount) VALUES ($order_id, $product_id, $cost, $shipping_fee, $tax_amount);" -U postgres
done
