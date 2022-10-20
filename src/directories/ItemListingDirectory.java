package directories;

import itemlistings.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * A class which is used to store and manage ItemListing objects.
 */
public class ItemListingDirectory extends GenericDirectory<ItemListing> {
    private final ArrayList<ItemListing> itemDirectory;

    /**
     * A constructor method for an ItemListingDirectory object which instantiates an array list of
     * ItemListing objects.
     */
    public ItemListingDirectory() {
        itemDirectory = super.directory;
    }

    /**
     * A method which displays all the item listings in ListIt to the console.
     */
    public void displayAllListings() {
        this.displayItemsByCategory("Electronics");
        System.out.println();
        this.displayItemsByCategory("Household Goods");
        System.out.println();
        this.displayItemsByCategory("Other");
        System.out.println();
        this.displayItemsByCategory("Vehicle");
        System.out.println();
    }

    /**
     * A method which displays a specific category of ItemListing objects that are stored within
     * an ItemDirectory object.
     * @param category A String representing the specific category of ItemListing objects that
     *                 are desired to be displayed.
     */
    public void displayItemsByCategory(String category) {
        System.out.println("Here are the current " + category.toLowerCase() + " listings in " +
                "ListIt:");
        for (ItemListing elem : itemDirectory) {
            if (elem != null && category.equals("Electronics")) {
                if (elem instanceof ElectronicsListing) {
                    System.out.println();
                    System.out.println("Electronics listing:\nSeller: "
                            + elem.getSeller() + "\nTitle: " + elem.getTitle() +
                            "\nPrice: " + String.format("$%.2f", elem.getPrice()) + "\nScreen " +
                            "Size: " + ((ElectronicsListing) elem).getScreenSize() +
                            '"' + "\nCondition: " + elem.getCondition() + "\nDelivery Method: " +
                            elem.getDeliveryMethod() + "\nDescription: " + elem.getDescription());
                                    }
            }
            if (elem != null && category.equals("Household Goods")) {
                if (elem instanceof HouseholdGoodsListing) {
                    System.out.println();
                    System.out.println("Household Goods listing:\nSeller: "
                            + elem.getSeller() + "\nTitle: " + elem.getTitle() +
                            "\nPrice: " + String.format("$%.2f", elem.getPrice()) + "\nWeight " +
                            "Size: " + ((HouseholdGoodsListing) elem).getWeight() + "lbs" +
                            "\nCondition: " + elem.getCondition() + "\nDelivery Method: " +
                            elem.getDeliveryMethod() + "\nDescription: " + elem.getDescription());
                                    }
            }
            if (elem != null && category.equals("Other")) {
                if (elem instanceof OtherListing) {
                    System.out.println();
                    System.out.println("Other listing:\nSeller: "
                            + elem.getSeller() + "\nTitle: " + elem.getTitle() +
                            "\nPrice: " + String.format("$%.2f", elem.getPrice()) + "\nWeight " +
                            "Size: " + ((OtherListing) elem).getWeight() + "lbs\nCondition: " +
                            elem.getCondition() + "\nDelivery Method: " +
                            elem.getDeliveryMethod() + "\nDescription: " + elem.getDescription());
                }
            }
            if (elem != null && category.equals("Vehicle")) {
                if (elem instanceof VehicleListing) {
                    System.out.println();
                    System.out.println("Vehicle listing:\nSeller: "
                            + elem.getSeller() + "\nTitle: " + elem.getTitle() +
                            "\nPrice: " + String.format("$%.2f", elem.getPrice()) + "\nVehicle " +
                            "Type: " + ((VehicleListing) elem).getVehicleType() + "\nMileage: " +
                            ((VehicleListing) elem).getMileage() + "\nCondition: " +
                            elem.getCondition() + "\nDelivery Method: " +
                            elem.getDeliveryMethod() + "\nDescription: " + elem.getDescription());
                }
            }
        }
    }

    /**
     * A method which reads in ItemListing objects from the ListIt Database and adds them to the
     * ItemListingDirectory object.
     */
    public synchronized void readInItemListings() {
        String url = "jdbc:sqlite:src/databases/ListItDatabase.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("ListIt Item Listing Database connected.");
            String sql = "SELECT * FROM ElectronicsListing";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    ItemListing itemListing = new ElectronicsListing(rs.getString(2),
                            rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6),
                            rs.getString(7), rs.getString(8));
                    itemDirectory.add(itemListing);
                }
            }
            String sql2 = "SELECT * FROM HouseholdGoodsListing";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql2)) {
                while (rs.next()) {
                    ItemListing itemListing = new HouseholdGoodsListing(rs.getString(2),
                            rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6),
                            rs.getString(7), rs.getString(8));
                    itemDirectory.add(itemListing);
                }
            }
            String sql3 = "SELECT * FROM OtherListing";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql3)) {
                while (rs.next()) {
                    ItemListing itemListing = new OtherListing(rs.getString(2),
                            rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6),
                            rs.getString(7), rs.getString(8));
                    itemDirectory.add(itemListing);
                }
            }
            String sql4 = "SELECT * FROM VehicleListing";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql4)) {
                while (rs.next()) {
                    ItemListing itemListing = new VehicleListing(rs.getString(2),
                            rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getFloat(6),
                            rs.getString(7), rs.getString(8), rs.getString(9));
                    itemDirectory.add(itemListing);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: The ListIt Database could not be accessed.");
            e.printStackTrace();
        }
    }
}