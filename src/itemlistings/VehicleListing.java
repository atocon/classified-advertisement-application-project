package itemlistings;

import java.io.Serializable;

/**
 * A class representing an item listing for different vehicles such as motorcycles, cars,
 * SUVs, etc.
 */
public class VehicleListing extends ItemListing implements Serializable {
    private String vehicleType;
    private float mileage;

    /**
     * A constructor to create a vehicle item listing.
     * @param seller A String representing the username of the seller.
     * @param title A String representing the title of an item listing.
     * @param price A float representing the price that the item is being sold for.
     * @param vehicleType A String representing the type of vehicle that is for sale (Motorcycle,
     *                    Car, etc.).
     * @param mileage A float representing the number of miles the vehicle has been driven per the
     *                vehicle's odometer.
     * @param condition A String representing the condition of the item that is being sold is in.
     * @param deliveryMethod A String representing the available delivery options of the item
     *                       being sold.
     * @param description A String representing a brief description of the item being sold
     */
    public VehicleListing(String seller, String title, float price,
                          String vehicleType, float mileage, String condition,
                          String deliveryMethod, String description) {
        super(seller, title, price, condition, deliveryMethod, description);
        super.category = "Vehicle";
        this.mileage = mileage;
        this.vehicleType = vehicleType;
    }

    /**
     * A method which displays information about a VehicleListing object.
     * @return A String which contains information about a VehicleListing object.
     */
    @Override
    public String toString() {
        return "Category: " + category + "\n" +
                "Title: " + title + "\n" +
                "Price: " + price + "\n" +
                "Vehicle Type: " + vehicleType + "\n" +
                "Mileage: " + mileage + "\n" +
                "Condition: " + condition + "\n" +
                "Delivery Method: " + deliveryMethod + "\n" +
                "Description: " + description;
    }

    /**
     * A method which implements the abstract calculateShippingCost() in the ItemListing
     * class which calculates the cost to ship a vehicle.
     * @return A float representing the cost to ship a vehicle.
     */
    @Override
    public float calculateShippingCost() {
        // The shipping cost is initially set to 0 which represents free shipping.
        float shippingCost = 0;
        // If block executes if the vehicle is available to be shipped.
        if (deliveryMethod.equals("Shipping") || deliveryMethod.equals("Shipping or Pick-up")) {
            // If the vehicle is a motorcycle, it costs $500 to ship.
            if (vehicleType.equals("Motorcycle")) {
                shippingCost = 500;
            }
            // If the vehicle is a car, it costs $1000 to ship.
            else if (vehicleType.equals("Car")) {
                shippingCost = 1000;
            }
            // If the vehicle is an SUV, it costs $1500 to ship.
            else if (vehicleType.equals("SUV")) {
                shippingCost = 1500;
            }
            // If the vehicle is a Truck, it costs $2500 to ship.
            else if (vehicleType.equals("Truck")) {
                shippingCost = 2500;
            }
        }
        // The shipping cost is set to -1 for vehicles which are not available for shipping.
        else shippingCost = -1;
        return shippingCost;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}