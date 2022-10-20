package databases;

import users.UserAccount;
import java.sql.*;

import static java.lang.String.format;

/**
 * A class which holds methods responsible for manipulating user data stored in the
 * ListIt Database.
 */
public class UserDatabase {

    /**
     * A constructor which allows for the creation of UserDatabase objects.
     */
    public UserDatabase() {}

    /**
     * A method which inserts ListIt User data into a SQL database for long-term storage.
     * @param user A UserAccount object representing a ListIt User.
     * @throws SQLException An exception that is thrown if the ListItDatabase is unreachable.
     */
    public static void insert(UserAccount user) throws SQLException {
        String url = "jdbc:sqlite:src/databases/ListItDatabase.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO User(FirstName, LastName, Username, UserPassword)" +
                    " VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getFirstName());
                pstmt.setString(2, user.getLastName());
                pstmt.setString(3, user.getUsername());
                pstmt.setString(4, user.getPassword());
                pstmt.executeUpdate();
            }
        }
    }

    /**
     * A method which displays UserAccount objects which are stored within a UserDirectory object.
     */
    public void displayUsers() {
        String url = "jdbc:sqlite:src/databases/ListItDatabase.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql1 = "SELECT COUNT(*) AS total, FirstName, LastName, Username FROM User";
            try (Statement stmt = conn.createStatement(); ResultSet rs1 = stmt.executeQuery(sql1)) {
                System.out.println("There are currently " + rs1.getInt(1) + " users registered in" +
                        " ListIt:");
            }
            String sql2 = "SELECT FirstName, LastName, Username FROM User ORDER BY FirstName, " +
                    "LastName";
            try (Statement stmt = conn.createStatement(); ResultSet rs2 = stmt.executeQuery(sql2)) {
                System.out.printf("%-20s %-20s %-20s %n", "First Name:", "Last Name:", "Username:");
                while (rs2.next()) {
                    System.out.printf("%-20s %-20s %-20s %n", rs2.getString(1), rs2.getString(2),
                            rs2.getString(3));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error: The ListIt Database could not be accessed.");
            e.printStackTrace();
        }
    }

    /**
     * This method was intended to join 5 tables from the ListItDatabase.db file
     * together to display item listing information for a specific user's item listings.
     * Unfortunately, this method results in an error that was unable to be resolved after many
     * hours of troubleshooting.
     * @param currentUser A UserAccount object whose associated item listings would be displayed.
     */
    public void displayUserListings(UserAccount currentUser) {
        String url = "jdbc:sqlite:src/databases/ListItDatabase.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.printf("%-12s %-12s %-12s %-12s %-20s %-12s %-12s %n", "First Name:",
                    "Last Name:", "Username:", "Category:", "Title:", "Price:", "Description:");
            try (Statement stmt = conn.createStatement()) {
                String sql = format("SELECT FirstName, LastName, User.Username, Category, Title, " +
                        "Price, " +
                        "Description " +
                        "FROM User " +
                        "JOIN ElectronicsListing ON User.Username = ElectronicsListing.Username " +
                        "WHERE User.Username = '%s'", currentUser.getUsername());
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out.printf("%-12s %-12s %-12s %-12s %-20s %-12.2f %-12s %n",
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getFloat(6), rs.getString(7));
                }
            }
            try (Statement stmt = conn.createStatement()) {
                String sql2 = format("SELECT FirstName, LastName, User.Username, Category, Title," +
                        " " +
                        "Price, " +
                        "Description " +
                        "FROM User " +
                        "JOIN HouseholdGoodsListing ON User.Username = HouseholdGoodsListing" +
                        ".Username " +
                        "WHERE User.Username = '%s'", currentUser.getUsername());
                ResultSet rs = stmt.executeQuery(sql2);
                while (rs.next()) {
                    System.out.printf("%-12s %-12s %-12s %-12s %-20s %-12.2f %-12s %n",
                            rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getFloat(6), rs.getString(7));
                }
            }
            try (Statement stmt = conn.createStatement()) {
                String sql3 = format("SELECT FirstName, LastName, User.Username, Category, Title," +
                        " " +
                        "Price, " +
                        "Description " +
                        "FROM User " +
                        "JOIN OtherListing ON User.Username = OtherListing" +
                        ".Username " +
                        "WHERE User.Username = '%s'", currentUser.getUsername());
                ResultSet rs = stmt.executeQuery(sql3);
                while (rs.next()) {
                    System.out.printf("%-12s %-12s %-12s %-12s %-20s %-12.2f %-12s %n",
                            rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getFloat(6), rs.getString(7));
                }
            }
            try (Statement stmt = conn.createStatement()) {
                String sql4 = format("SELECT FirstName, LastName, User.Username, Category, Title," +
                        " " +
                        "Price, " +
                        "Description " +
                        "FROM User " +
                        "JOIN VehicleListing ON User.Username = VehicleListing" +
                        ".Username " +
                        "WHERE User.Username = '%s'", currentUser.getUsername());
                ResultSet rs = stmt.executeQuery(sql4);
                while (rs.next()) {
                    System.out.printf("%-12s %-12s %-12s %-12s %-20s %-12.2f %-12s %n",
                            rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getFloat(6), rs.getString(7));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error: The ListIt User Database could not be accessed.");
            e.printStackTrace();
        }
    }
}