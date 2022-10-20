package itemlistings;

import java.io.Serializable;

/**
 * A class representing an item listing for an electronic such as a TV, cell phone,
 * tablet, etc.
 */
public class ElectronicsListing extends ItemListing implements Serializable {
    private int screenSize;

    /**
     * A constructor to create an item listing for an electronic good.
     * @param seller A String representing the username of the seller.
     * @param title A String representing the title of an item listing.
     * @param price A float representing the price that the item is being sold for.
     * @param screenSize An int representing the screen size, in inches, of the electronic item for
     *                   sale.
     * @param condition A String representing the condition of the item that is being sold is in.
     * @param deliveryMethod A String representing the available delivery options of the item
     *                       being sold.
     * @param description A String representing a brief description of the item being sold.
     */
    public ElectronicsListing(String seller, String title, float price, int screenSize,
                              String condition, String deliveryMethod, String description) {
        super(seller, title, price, condition, deliveryMethod, description);
        super.category = "Electronics";
        this.screenSize = screenSize;
    }

    /**
     * A method which displays information about an ElectronicsListing object.
     * @return A String which contains information about an ElectronicsListing object.
     */
    @Override
    public String toString() {
        return "Category: " + category + "\n" +
                "Title: " + title + "\n" +
                "Price: " + price + "\n" +
                "Screen Size: " + screenSize + "\n" +
                "Condition: " + condition + "\n" +
                "Delivery Method: " + deliveryMethod + "\n" +
                "Description: " + description;
    }

    /**
     * A method which implements the abstract calculateShippingCost() in the ItemListing
     * class which calculates the cost to ship an electronics good.
     * @return A float representing the cost to ship an electronics good.
     */
    @Override
    public float calculateShippingCost() {
        // The shipping cost is initially set to 0 which represents free shipping.
        float shippingCost = 0;
        // If block executes if the item is available to be shipped.
        if (deliveryMethod.equals("Shipping") || deliveryMethod.equals("Shipping or Pick-up")) {
            /* Shipping costs $25 for an electronic item with a screen size from 10 up to and
            including 22 inches.*/
            if (10 < screenSize && screenSize <= 22) {
                shippingCost = 25;
            }
            /* Shipping costs $50 for an electronic item with a screen size from 22 up to and
            including 43 inches.*/
            else if (screenSize < 22 && screenSize <= 43) {
                shippingCost = 50;
            }
            /* Shipping costs $100 for an electronic item with a screen size less than or equal
            to 55 inches.*/
            else if (screenSize <= 55) {
                shippingCost = 100;
            }
        }
        // The shipping cost is set to -1 for electronic items which are not available for shipping.
        else shippingCost = -1;
        return shippingCost;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
}