package databases;

import itemlistings.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A class which holds methods responsible for manipulating item listing data stored in the
 * ListIt Database.
 */
public class ItemListingDatabase {

    /**
     * A constructor which allows for the creation of ItemListingDatabase objects.
     */
    public ItemListingDatabase() {}

    /**
     * A method which inserts data from an ItemListing object into the ListIt database for
     * long-term storage.
     * @param itemListing An ItemListing object.
     * @throws SQLException An exception that is thrown if the ListItDatabase is unreachable.
     */
    public static void insert(ItemListing itemListing) throws SQLException {
        String url = "jdbc:sqlite:src/databases/ListItDatabase.db";
            try (Connection conn = DriverManager.getConnection(url)) {
                if (itemListing.getCategory().equals("Electronics")) {
                    String sql1 = "INSERT INTO ElectronicsListing(Category, Username, Title, Price, " +
                            "ScreenSize, Condition, DeliveryMethod, Description) VALUES (?," +
                            " ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql1)) {
                        pstmt.setString(1, itemListing.getCategory());
                        pstmt.setString(2, itemListing.getSeller());
                        pstmt.setString(3, itemListing.getTitle());
                        pstmt.setFloat(4, itemListing.getPrice());
                        pstmt.setInt(5, ((ElectronicsListing) itemListing).getScreenSize());
                        pstmt.setString(6, itemListing.getCondition());
                        pstmt.setString(7, itemListing.getDeliveryMethod());
                        pstmt.setString(8, itemListing.getDescription());
                        pstmt.executeUpdate();
                    }
                }
                if (itemListing.getCategory().equals("Household Goods")) {
                    String sql2 = "INSERT INTO HouseholdGoodsListing(Category, Username, Title, " +
                            "Price, Weight, Condition, DeliveryMethod, Description) VALUES (?," +
                            " ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql2)) {
                        pstmt.setString(1, itemListing.getCategory());
                        pstmt.setString(2, itemListing.getSeller());
                        pstmt.setString(3, itemListing.getTitle());
                        pstmt.setFloat(4, itemListing.getPrice());
                        pstmt.setInt(5, ((HouseholdGoodsListing) itemListing).getWeight());
                        pstmt.setString(6, itemListing.getCondition());
                        pstmt.setString(7, itemListing.getDeliveryMethod());
                        pstmt.setString(8, itemListing.getDescription());
                        pstmt.executeUpdate();
                    }
                }
                if (itemListing.getCategory().equals("Other")) {
                    String sql3 = "INSERT INTO OtherListing(Category, Username, Title, " +
                            "Price, Weight, Condition, DeliveryMethod, Description) VALUES (?," +
                            " ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql3)) {
                        pstmt.setString(1, itemListing.getCategory());
                        pstmt.setString(2, itemListing.getSeller());
                        pstmt.setString(3, itemListing.getTitle());
                        pstmt.setFloat(4, itemListing.getPrice());
                        pstmt.setInt(5, ((OtherListing) itemListing).getWeight());
                        pstmt.setString(6, itemListing.getCondition());
                        pstmt.setString(7, itemListing.getDeliveryMethod());
                        pstmt.setString(8, itemListing.getDescription());
                        pstmt.executeUpdate();
                    }
                }
                if (itemListing.getCategory().equals("Vehicle")) {
                    String sql4 = "INSERT INTO VehicleListing(Category, Username, Title, " +
                            "Price, VehicleType, Mileage, Condition, DeliveryMethod, Description) " +
                            "VALUES (?," +
                            " ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql4)) {
                        pstmt.setString(1, itemListing.getCategory());
                        pstmt.setString(2, itemListing.getSeller());
                        pstmt.setString(3, itemListing.getTitle());
                        pstmt.setFloat(4, itemListing.getPrice());
                        pstmt.setString(5, ((VehicleListing) itemListing).getVehicleType());
                        pstmt.setFloat(6, ((VehicleListing) itemListing).getMileage());
                        pstmt.setString(7, itemListing.getCondition());
                        pstmt.setString(8, itemListing.getDeliveryMethod());
                        pstmt.setString(9, itemListing.getDescription());
                        pstmt.executeUpdate();
                    }
                }
            }
        }
    }