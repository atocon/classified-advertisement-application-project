package users;

import itemlistings.*;

import java.io.Serializable;

/**
 * A class representing a user account which features information and functionality regarding users.
 */
public class UserAccount implements Serializable {
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    /**
     * A constructor to create UserAccount objects.
     * @param firstName A String representing the first name of a user.
     * @param lastName A String representing the last name of a user.
     * @param username A String representing the username of a user.
     */
    public UserAccount(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    /**
     * A method which displays information about a UserAccount object.
     * @return A String which contains information about a UserAccount object.
     */
    public String toString() {
        return "First Name: " + firstName + "\nLast Name: " + lastName
                + "\nUsername: " + username + "\npassword: " + password;
    }

    /**
     * A method which allows a user to create an ElectronicsListing object.
     */
    public ItemListing createElectronicsListing(String seller, String title, float price,
                                                int screenSize, String condition,
                                                String deliveryMethod, String description) {
        return new ElectronicsListing(seller, title, price, screenSize, condition, deliveryMethod,
                description);
    }

    /**
     * A method which allows a user to create a HouseholdGoodsListing object.
     */
    public ItemListing createHouseholdGoodsListing(String seller, String title, float price,
                                                   int weight, String condition,
                                                   String deliveryMethod, String description) {
        return new HouseholdGoodsListing(seller, title, price, weight, condition, deliveryMethod,
                description);
    }

    /**
     * A method which allows a user to create an OtherListing object.
     */
    public ItemListing createOtherListing(String seller, String title, float price, int weight,
                                          String condition, String deliveryMethod,
                                          String description) {
        return new OtherListing(seller, title, price, weight, condition, deliveryMethod,
                description);
    }

    /**
     * A method which allows a user to create a vehicleListing object.
     */
    public ItemListing createVehicleListing(String seller, String title, float price,
                                      String vehicleType, float mileage, String condition,
                                      String deliveryMethod, String description) {
        return new VehicleListing(seller, title, price, vehicleType, mileage, condition,
                deliveryMethod, description);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}