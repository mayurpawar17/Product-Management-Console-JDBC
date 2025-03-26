import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Product {
    // Database connection parameters
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "mayur";
    private static final String PASSWORD = "mayur123";

    // SQL queries
    private static final String INSERT_QUERY = "INSERT INTO product72 (code,name,price,qty) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM product72";
    private static final String SELECT_BY_CODE_QUERY = "SELECT * FROM product72 WHERE code=?";
    private static final String DELETE_QUERY = "DELETE FROM product72 WHERE code=?";
    private static final String UPDATE_QUERY = "UPDATE product72 SET price=?,qty=? WHERE code=?";

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            // Load JDBC driver and establish connection
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

                // Prepare all statements upfront for better performance
                PreparedStatement addStmt = connection.prepareStatement(INSERT_QUERY);
                PreparedStatement viewAllStmt = connection.prepareStatement(SELECT_ALL_QUERY);
                PreparedStatement viewByCodeStmt = connection.prepareStatement(SELECT_BY_CODE_QUERY);
                PreparedStatement deleteStmt = connection.prepareStatement(DELETE_QUERY);
                PreparedStatement updateStmt = connection.prepareStatement(UPDATE_QUERY);

                while (true) {
                    displayMenu();
                    System.out.print("Select option: ");
                    int option = scanner.nextInt();

                    switch (option) {
                        case 1 -> addProduct(scanner, addStmt);
                        case 2 -> viewAllProducts(viewAllStmt);
                        case 3 -> viewProductByCode(scanner, viewByCodeStmt);
                        case 4 -> updateProduct(scanner, updateStmt);
                        case 5 -> deleteProduct(scanner, deleteStmt);
                        case 0 -> {
                            System.out.println("Exiting system...");
                            System.exit(0);
                        }
                        default -> System.out.println("Invalid option!");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Displays the main menu options
     */
    private static void displayMenu() {
        System.out.println("\n===== Product Management System =====");
        System.out.println("1. Add product");
        System.out.println("2. View all products");
        System.out.println("3. View product by code");
        System.out.println("4. Update product by code (price/qty)");
        System.out.println("5. Delete product by code");
        System.out.println("0. Exit");
    }

    /**
     * Adds a new product to the database
     */
    private static void addProduct(Scanner scanner, PreparedStatement stmt) throws Exception {
        System.out.println("\n--- Add Product ---");
        System.out.print("Enter Product code: ");
        String code = scanner.next();
        System.out.print("Enter Product name: ");
        String name = scanner.next();
        System.out.print("Enter Product price: ");
        int price = scanner.nextInt();
        System.out.print("Enter Product Quantity: ");
        int qty = scanner.nextInt();

        stmt.setString(1, code);
        stmt.setString(2, name);
        stmt.setInt(3, price);
        stmt.setInt(4, qty);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Product added successfully!");
        }
    }

    /**
     * Displays all products from the database
     */
    private static void viewAllProducts(PreparedStatement stmt) throws Exception {
        System.out.println("\n--- All Products ---");
        ResultSet rs = stmt.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("No products found!");
            return;
        }

        while (rs.next()) {
            System.out.printf("Code: %s | Name: %s | Price: %d | Qty: %d%n",
                    rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
    }

    /**
     * Displays a specific product by code
     */
    private static void viewProductByCode(Scanner scanner, PreparedStatement stmt) throws Exception {
        System.out.println("\n--- View Product ---");
        System.out.print("Enter Product code: ");
        String code = scanner.next();

        stmt.setString(1, code);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.printf("Code: %s | Name: %s | Price: %d | Qty: %d%n",
                    rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        } else {
            System.out.println("Product not found!");
        }
    }

    /**
     * Updates product price and quantity
     */
    private static void updateProduct(Scanner scanner, PreparedStatement stmt) throws Exception {
        System.out.println("\n--- Update Product ---");
        System.out.print("Enter product code to update: ");
        String code = scanner.next();
        System.out.print("Enter new price: ");
        int price = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int qty = scanner.nextInt();

        stmt.setInt(1, price);
        stmt.setInt(2, qty);
        stmt.setString(3, code);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }

    /**
     * Deletes a product by code
     */
    private static void deleteProduct(Scanner scanner, PreparedStatement stmt) throws Exception {
        System.out.println("\n--- Delete Product ---");
        System.out.print("Enter Product code: ");
        String code = scanner.next();

        stmt.setString(1, code);
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }
}
