# Product-Management-Console-JDBC

A Java JDBC-based console application to manage product inventory with CRUD operations using Oracle Database.

## **📌 Features**
- **Add a new product** (code, name, price, quantity)
- **View all products** in the database
- **View a product by its code**
- **Update product details** (price, quantity)
- **Delete a product by code**
- **Exit the application**

## **🛠 Technologies Used**
- **Java** (JDBC - Java Database Connectivity)
- **Oracle Database** (19c/XE)
- **Oracle JDBC Driver** (ojdbc8.jar)

## **📋 Prerequisites**
- Java JDK 8+
- Oracle Database 11g/19c/XE
- Oracle JDBC Driver (ojdbc8.jar)



## **⚙ Setup & Installation**

1. **Clone the Repository**
   ```00sh
   git clone https://github.com/your-username/Product-Management-Console-JDBC.git
   cd Product-Management-Console-JDBC
   ```

2. **Set Up Oracle Database**

- Create table:

  ```sql
  CREATE TABLE products (
      code VARCHAR2(20) PRIMARY KEY,
      name VARCHAR2(100) NOT NULL,
      price NUMBER NOT NULL,
      quantity NUMBER NOT NULL
  );
  ```

3. **Configure Database Connection**
   Update these constants in your code:

```java
private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
private static final String DB_USER = "your_username";
private static final String DB_PASSWORD = "your_password";
```

4. **Add JDBC Driver  (for Intellij IDE)**

- Download `ojdbc8.jar`
- In IntelliJ:
  - Right-click project → **Open Module Settings** (F4)
  - Navigate to **Libraries** → Click **+** → **Java**
  - Select the downloaded JDBC driver `.jar` file
  - Click **OK** to add it to your project

## **📂 Project Structure**

Copy

```
📦 Product-Management-Console-JDBC
├── 📜 Product.java            # Product model class
└── 📜 README.md               # Documentation
```

## **🎯 Usage**

Run the program and choose an option:

```java
===== Product Management System =====
1. Add Product
2. View All Products
3. View Product By Code
4. Update Product (Price/Quantity)
5. Delete Product
6. Exit
```

