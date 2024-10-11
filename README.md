# Order Management API

## Description

This project is a Java-based API for managing customers, orders, and products. It allows for the creation, retrieval, update, and deletion of these entities, facilitating an efficient order management system.

## Technologies Used

- **Spring Boot**
- **Spring Data R2DBC** (for PostgreSQL)
- **Spring Data MongoDB** (for MongoDB)
- **Lombok**
- **PostgreSQL** (for storing customers and products)
- **MongoDB** (for storing orders)
- **Swagger** for API documentation

## Database Configuration

This API uses two different databases:
- **PostgreSQL** for managing customer and product data.
- **MongoDB** for managing orders.

### Entities

#### 1. Customer
- **Fields**:
    - `id`: Unique identifier of the customer.
    - `first_name`: Customer's first name.
    - `last_name`: Customer's last name.
    - `email`: Customer's email address.
    - `phone`: Customer's phone number.
    - `address`: Customer's address.
    - `city`: Customer's city.
    - `state`: Customer's state.
    - `country`: Customer's country.
    - `created_at`: Timestamp of when the customer was created.
    - `updated_at`: Timestamp of when the customer was last updated.

#### 2. Product
- **Fields**:
    - `id`: Unique identifier of the product.
    - `name`: Name of the product.
    - `description`: Description of the product.
    - `price`: Price of the product.
    - `stock_quantity`: Available stock quantity of the product.
    - `created_at`: Timestamp of when the product was created.
    - `updated_at`: Timestamp of when the product was last updated.

#### 3. Order
- **Fields**:
    - `id`: Unique identifier of the order.
    - `customerId`: ID of the customer who placed the order.
    - `products`: List of product IDs included in the order.
    - `total`: Total amount of the order.

## Endpoints

### 1. Customer Endpoints

- **GET** `/customer`: Retrieves all customers.
- **GET** `/customer/{customerId}`: Retrieves a customer by their ID.
- **POST** `/customer`: Creates a new customer.
- **PUT** `/customer/{customerId}`: Updates an existing customer by their ID.
- **DELETE** `/customer/{customerId}`: Deletes a customer by their ID.

### 2. Product Endpoints

- **GET** `/product`: Retrieves all products.
- **GET** `/product/{productId}`: Retrieves a product by its ID.
- **POST** `/product`: Creates a new product.
- **PUT** `/product/{productId}`: Updates an existing product by its ID.
- **DELETE** `/product/{productId}`: Deletes a product by its ID.

### 3. Order Endpoints

- **GET** `/order`: Retrieves all orders.
- **GET** `/order/{orderId}`: Retrieves an order by its ID.
- **POST** `/order`: Creates a new order.
- **PUT** `/order/{orderId}`: Updates an existing order by its ID.
- **DELETE** `/order/{orderId}`: Deletes an order by its ID.

## Running the Application

To run the application, use the following command in the root directory of your project:

```bash
docker-compose up
```
## Documentation

The API documentation can be accessed through Swagger UI. The default URL is: http://localhost:8080/swagger-ui/index.html#/

**Note:** The actual URL may vary based on your specific configuration:
- **Port**: If your application is running on a different port, please adjust the port number in the URL accordingly.
- **Context Path**: If your application is deployed with a specific context path, include that in the URL. For example, if your context path is `/api`, the URL will be `http://localhost:8080/api/swagger-ui.html#`.

Make sure to check your application settings to determine the correct URL for accessing the Swagger UI.
