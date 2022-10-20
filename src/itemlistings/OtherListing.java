package itemlistings;

import java.io.Serializable;

/**
 * A class representing an item listing for all other items which are not electronics, household
 * goods, or vehicles.
 */
public class OtherListing extends ItemListing implements Serializable {
    private int weight;

    /**
     * A constructor to create an item listing for an item.
     * @param seller A String representing the username of the seller.
     * @param title A String representing the title of an item listing.
     * @param price A float representing the price that the item is being sold for.
     * @param weight The weight of the item for sale in pounds.
     * @param condition A String representing the condition of the item that is being sold is in.
     * @param deliveryMethod A String representing the available delivery options of the item
     *                       being sold.
     * @param description A String representing a brief description of the item being sold
     */
    public OtherListing(String seller, String title, float price, int weight,
                        String condition, String deliveryMethod, String description) {
        super(seller, title, price, condition, deliveryMethod, description);
        super.category = "Other";
        this.weight = weight;

    }

    /**
     * A method which displays information about an OtherListing object.
     * @return A String which contains information about an OtherListing object.
     */
    @Override
    public String toString() {
        return "Category: " + category + "\n" +
                "Title: " + title + "\n" +
                "Price: " + price + "\n" +
                "Weight: " + weight + "\n" +
                "Condition: " + condition + "\n" +
                "Delivery Method: " + deliveryMethod + "\n" +
                "Description: " + description;
    }

    /**
     * A method which implements the abstract calculateShippingCost() in the ItemListing
     * class which calculates the cost to ship an item.
     * @return A float representing the cost to ship the item.
     */
    @Override
    public float calculateShippingCost() {
        // The shipping cost is initially set to 0 which represents free shipping.
        float shippingCost = 0;
        // If block executes if the item is available to be shipped.
        if (deliveryMethod.equals("Shipping") || deliveryMethod.equals("Shipping or Pick-up")) {
            /* Shipping costs $20 for a household good which weighs from 10 up to and
            including 20 pounds.*/
            if (10 < weight && weight <= 20) {
                shippingCost = 20.00f;
            }
            /* Shipping costs $25 for a household good which weighs from 20 up to and
            including 50 pounds.*/
            else if (20 < weight && weight <= 50) {
                shippingCost = 25;
            }
            // Shipping costs $100 for a household good which weighs more than 50 pounds.
            else if (50 < weight) {
                shippingCost = 100;
            }
        }
        // The shipping cost is set to -1 for items which are not available for shipping.
        else shippingCost = -1;
        return shippingCost;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}