package itemlistings;

import java.io.Serializable;

/**
 * An abstract class representing different types of item listings.
 */
public abstract class ItemListing implements Serializable {
    protected String seller;
    protected String title;
    protected String category;
    protected float price;
    protected String condition;
    protected String deliveryMethod;
    protected String description;

    /**
     * A parameterless constructor to create an ItemListing object.
     */
    public ItemListing() {}

    /**
     * Constructor that creates an item listing object.
     * @param seller A String representing the username of the seller.
     * @param title A String representing the title of an item listing.
     * @param price A float representing the price that the item is being sold for.
     * @param condition A String representing the condition of the item that is being sold is in.
     * @param deliveryMethod A String representing the available delivery options of the item
     *                       being sold.
     * @param description A String representing a brief description of the item being sold.
     */
    public ItemListing(String seller, String title, float price, String condition,
                       String deliveryMethod, String description) {
        this.seller = seller;
        this.title = title;
        // The category is defined by each concrete class of the item listing class.
        this.category = "";
        this.price = price;
        this.condition = condition;
        this.deliveryMethod = deliveryMethod;
        this.description = description;
    }

    /**
     * An abstract method to calculate the cost to ship an item which must be implemented by all
     * concrete ItemListing classes.
     * @return A float which represents the shipping to cost an item.
     */
    public abstract float calculateShippingCost();

    /**
     * A method which translates the result of an ItemListing object's calculateShippingCost()
     * method into a meaningful message.
     * @param itemListing An ItemListing object.
     */
    public void displayDeliveryInformation(ItemListing itemListing) {
        if (itemListing.calculateShippingCost() == 0) {
            System.out.println("This item can be shipped for free!");
        }
        else if (itemListing.calculateShippingCost() == -1) {
            System.out.println("This item is only available for pick-up and cannot be shipped.");
        }
        else
            System.out.printf("This item can be shipped for $%.2f%n",
                    itemListing.calculateShippingCost());

    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}