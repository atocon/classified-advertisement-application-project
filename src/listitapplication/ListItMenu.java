package listitapplication;

import exceptions.CurrentUserException;
import itemlistings.ItemListing;
import users.UserAccount;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * A class representing a menu for ListIt through which users can interact with and provide input
 * to the ListIt system.
 */
public class ListItMenu {
    private final ListItSystem listItSystem;

    /**
     * A constructor to create a ListItMenu object.
     *
     * @param listItSystem The ListItSystem object for which the user menu is being created for.
     */
    public ListItMenu(ListItSystem listItSystem) {
        this.listItSystem = listItSystem;
    }

    /**
     * A method which displays the ListIt menu which users can interact with and provide input to.
     *
     * @throws CurrentUserException An exception which checks that the ListItSystem's currentUser
     *                              attribute is set to a valid UserAccount object.
     * @throws SQLException An exception that is thrown if the ListIt Database is unreachable.
     */
    public void runListItMenu() throws CurrentUserException, SQLException {
        int initialInput;

        displayWelcomeMessage();

        boolean validInput = true;
        do {
            try {
                initialInput = getInitialMenuSelection();
                if (initialInput == 1) {
                    runLoginMenu();
                } else if (initialInput == 2) {
                    runCreateUserAccountMenu();
                } else if (initialInput == 3) {
                    listItSystem.getListItItemDirectory().displayAllListings();
                    runListItMenu();
                } else if (initialInput == 4) {
                    checkCurrentUser();
                    ItemListing createdItemListing = runCreateItemListingMenu();
                    runVerifyItemListingMenu(createdItemListing);
                } else if (initialInput == 5) {
                    runLogoutMenu();
                } else {
                    System.out.println("Error: Enter a valid menu selection.");
                    validInput = false;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error: Enter a valid menu selection.");
                validInput = false;
            }
        } while (!validInput);
    }

    /**
     * A method which checks that the ListItSystem has a valid UserAccount set as the currentUser.
     *
     * @throws CurrentUserException An exception which checks that the ListItSystem's currentUser
     *                              * attribute is set to a valid UserAccount object.
     */
    public void checkCurrentUser() throws CurrentUserException {
        if (listItSystem.getCurrentUser() == null) {
            throw new CurrentUserException("Error: Please, make sure you are " +
                    "logged in prior to attempting to create an item listing.");
        }
    }

    /**
     * A method which displays a welcome message for the ListIt system.
     */
    public void displayWelcomeMessage() {
        System.out.println("Welcome to ListIt!");
    }

    /**
     * A method which gets a users initial menu selection at the ListIt system main menu.
     *
     * @return An int which represents a user's menu selection.
     */
    public int getInitialMenuSelection() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please, select an option from the menu below. Enter the number " +
                "associated with the desired option (i.e. 1, 2, etc.):\n" +
                "1. Login\n" +
                "2. Create a ListIt Account\n" +
                "3. Browse Item Listings\n" +
                "4. Create an Item Listing\n" +
                "5. Logout");
        return Integer.parseInt(userInput.nextLine());
    }

    /**
     * A method which allows a user to log into the ListItSystem application.
     *
     * @throws CurrentUserException An error message if the user is not logged in attempts to
     *                              create an item listing.
     * @throws SQLException An exception that is thrown if the ListIt Database is unreachable.
     */
    public void runLoginMenu() throws CurrentUserException, SQLException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter your ListIt username:");
        String username = userInput.nextLine();
        System.out.println("Enter your password:");
        String password = userInput.nextLine();
        UserAccount verifiedUser = listItSystem.getListItUserDirectory().verifyUser(username,
                password);
        if (verifiedUser != null) {
            listItSystem.setCurrentUser(verifiedUser);
            System.out.println("Login successful.\n");
            runListItMenu();
        } else {
            System.out.println("Error: Login failed. A matching username and password could not" +
                    " be found. Please, try again or create an account.\n");
            runListItMenu();
        }
    }

    /**
     * A method which allows a user to create a ListIt user account.
     * @throws CurrentUserException An error message if the user is not logged in attempts to
     *                              create an item listing.
     * @throws SQLException An exception that is thrown if the ListIt Database is unreachable.
     */
    public void runCreateUserAccountMenu() throws CurrentUserException, SQLException {
        String firstName;
        String lastName;
        String username;
        String password1;
        String password2;
        boolean validInput;
        UserAccount createdUserAccount;

        Scanner userInput = new Scanner(System.in);

        if (listItSystem.getCurrentUser() == null) {

            System.out.println("Enter your first name:");
            firstName = userInput.nextLine();

            System.out.println("Enter your last name:");
            lastName = userInput.nextLine();

            do {
                System.out.println("Enter an alphanumeric username for your ListIt account:");
                username = userInput.nextLine();
                if (username.matches("^[a-zA-Z0-9]*$")) {
                    if (listItSystem.getListItUserDirectory().checkUsername(username)) {
                        System.out.println("The ListIt username you entered is available!");
                        validInput = true;
                    } else {
                        System.out.println("Error: The ListIt username you entered is already taken, " +
                                "please choose another username.");
                        validInput = false;
                    }

                } else {
                    System.out.println("Error: The username you entered is not alphanumeric, please " +
                            "try again.");
                    validInput = false;
                }
            } while (!validInput);

            do {
                System.out.println("Enter a password for your ListIt account:");
                password1 = userInput.nextLine();
                System.out.println("Re-enter your password for verification purposes::");
                password2 = userInput.nextLine();
                if (!password1.equals(password2)) {
                    System.out.println("Error: The passwords you entered did not match, please try " +
                            "again.");
                }
            } while (!password1.equals(password2));
            createdUserAccount = new UserAccount(firstName, lastName, username, password1);
            runVerifyUserAccountMenu(createdUserAccount);
        } else {

            System.out.println("Error: You cannot created a new ListIt account if you are already" +
                    " logged in. Please, logout to create an account or choose a different menu " +
                            "option.\n");
            runListItMenu();
        }
    }

    /**
     * A method which allows a user to verify that entered user account information is correct,
     * create a ListIt user account, and add the UserAccount object to the ListIt UserDirectory
     * object and ListIt Database for storage.
     * @param user A UserAccount object which represents the account that is being created and
     *             added to ListIt.
     * @throws CurrentUserException An error message if the user is not logged in attempts to
     *                              create an item listing.
     * @throws SQLException An exception that is thrown if the ListIt Database is unreachable.
     */
    public void runVerifyUserAccountMenu(UserAccount user) throws CurrentUserException, SQLException {
        String userConfirmation;

        Scanner userInput = new Scanner(System.in);
        System.out.println("Is the following user account information correct? " +
                "Enter “yes” to confirm, “no” to try again, or “quit” to return to the main menu:");
        System.out.println(user);
        userConfirmation = userInput.nextLine();
        if (userConfirmation.toLowerCase().equals("yes")) {
            System.out.println("You confirmed the user account information is correct. Your " +
                    "ListIt account has been successfully created and you are logged into your " +
                    "account!\n");
            listItSystem.getListItUserDirectory().addToDirectory(user);
            listItSystem.getListItUserDatabase().insert(user);
            listItSystem.setCurrentUser(user);
            runListItMenu();
        } else if (userConfirmation.toLowerCase().equals("no")) {
            runCreateUserAccountMenu();
        } else if (userConfirmation.toLowerCase().equals("quit")) {
            System.out.println();
            runListItMenu();
        } else {
            System.out.println("Error: Enter a valid option, please try again.");
            runVerifyUserAccountMenu(user);
        }
    }

    /**
     * A method which allows a user to logout of ListIt and terminates the application.
     */
    public void runLogoutMenu() {
        listItSystem.setCurrentUser(null);
        System.out.println("Logout Successful.\n");
    }

    /**
     * A method which allows a user to create an ItemListing object through the ListIt system menu.
     * @return An ItemListing object which features information based on user input.
     */
    public ItemListing runCreateItemListingMenu() {
        int userSelection;
        String title;
        float price;
        int userConditionSelection;
        String condition = "";
        int userDeliveryMethodSelection;
        String deliveryMethod = "";
        int userVehicleTypeSelection;
        String vehicleType = "";
        float mileage;
        int screenSize;
        int weight;
        String description;
        ItemListing createdItemListing = null;

        Scanner userInput = new Scanner(System.in);

        System.out.println("Choose one of the following categories in which the item should be " +
                "listed for sale. Enter the number associated with the desired option " +
                "(i.e. 1, 2, etc.):\n" +
                "1. Vehicles\n" +
                "2. Electronics\n" +
                "3. Household Goods\n" +
                "4. Other");
        userSelection = Integer.parseInt(userInput.nextLine());

        System.out.println("Enter a title for your item listing:");
        title = userInput.nextLine();

        System.out.println("Enter the price you wish to list the item for (i.e. 50, 1000, etc.):");
        price = Float.parseFloat(userInput.nextLine());

        System.out.println("What condition is the item in? Select an option from the menu below " +
                "by entering the number associated with the desired option (i.e. 1, 2, etc.):\n" +
                "1. New\n" +
                "2. Pre-owned - Excellent\n" +
                "3. Pre-owned - Good\n" +
                "4. Pre-owned - Fair\n" +
                "5. Pre-owned - Poor");
        userConditionSelection = Integer.parseInt(userInput.nextLine());
        if (userConditionSelection == 1) {
            condition = "New";
        }
        else if (userConditionSelection == 2) {
            condition = "Pre-owned - Excellent";
        }
        else if (userConditionSelection == 3) {
            condition = "Pre-owned - Good";
        }
        else if (userConditionSelection == 4) {
            condition = "Pre-owned - Fair";
        }
        else if (userConditionSelection == 5) {
            condition = "Pre-owned - Poor";
        }

        System.out.println("Is this item available for shipping, pick-up, or both? Select an " +
                "option from the menu below by entering the number associated with the desired " +
                "option (i.e. 1, 2, etc.):\n" +
                "1. Shipping\n" +
                "2. Pick-up\n" +
                "3. Shipping or Pick-up");
        userDeliveryMethodSelection = Integer.parseInt(userInput.nextLine());
        if (userDeliveryMethodSelection == 1) {
            deliveryMethod = "Shipping";
        }
        else if (userDeliveryMethodSelection == 2) {
            deliveryMethod = "Pick-up";
        }
        else if (userDeliveryMethodSelection == 3) {
            deliveryMethod = "Shipping or Pick-up";
        }

        if (userSelection == 1) {
            System.out.println("What type of vehicle would you like to sell? Select an option " +
                    "from the menu below by entering the number associated with the desired " +
                    "option (i.e. 1, 2, etc.):\n" +
                    "1. Car\n" +
                    "2. SUV\n" +
                    "3. Truck\n" +
                    "4. Motorcycle");
            userVehicleTypeSelection = Integer.parseInt(userInput.nextLine());
            if (userVehicleTypeSelection == 1) {
                vehicleType = "Car";
            }
            else if (userVehicleTypeSelection == 2) {
                vehicleType = "SUV";
            }
            else if (userVehicleTypeSelection == 3) {
                vehicleType = "Truck";
            }
            else if (userVehicleTypeSelection == 4) {
                vehicleType = "Motorcycle";
            }

            System.out.println("Enter the mileage of the vehicle to the nearest thousandth (i" +
                    ".e. 1000, 30000, 130000, etc.):");
            mileage = Float.parseFloat(userInput.nextLine());

            System.out.println("Enter a brief description (250 characters or less) of the vehicle" +
                    " being listed for sale:");
            description = userInput.nextLine();

            createdItemListing =
                    listItSystem.getCurrentUser().createVehicleListing(listItSystem.getCurrentUser().getUsername(),
                    title, price, vehicleType, mileage, condition, deliveryMethod, description);
        }
        if (userSelection == 2) {
            System.out.println("Enter the screen size in inches of the electronic item you are " +
                    "selling (i.e. 6, 12, etc.). Enter 0 if the item does not have a screen:");
            screenSize = Integer.parseInt(userInput.nextLine());

            System.out.println("Enter a brief description (250 characters or less) of the " +
                    "electronic item being listed for sale:");
            description = userInput.nextLine();

            createdItemListing =
                    listItSystem.getCurrentUser().createElectronicsListing(listItSystem.getCurrentUser().getUsername(),
                    title, price, screenSize, condition, deliveryMethod, description);
        }
        if (userSelection == 3) {
            System.out.println("Enter the weight in pounds of the household good you are " +
                    "selling (i.e. 20, 55, etc.):");
            weight = Integer.parseInt(userInput.nextLine());

            System.out.println("Enter a brief description (250 characters or less) of the " +
                    "household good being listed for sale:");
            description = userInput.nextLine();

            createdItemListing =
                    listItSystem.getCurrentUser().createHouseholdGoodsListing(listItSystem.getCurrentUser().getUsername(),
                    title, price, weight, condition, deliveryMethod, description);
        }
        if (userSelection == 4) {
            System.out.println("Enter the weight in pounds of the item you are " +
                    "selling (i.e. 20, 55, etc.):");
            weight = Integer.parseInt(userInput.nextLine());

            System.out.println("Enter a brief description (250 characters or less) of the " +
                    "item being listed for sale:");
            description = userInput.nextLine();

            createdItemListing =
                    listItSystem.getCurrentUser().createOtherListing(listItSystem.getCurrentUser().getUsername(),
                    title, price, weight, condition, deliveryMethod, description);
        }
        return createdItemListing;
    }


    /**
     * A method which verifies an ItemListing object created by a user and allows them to add the
     * item listing to the ListIt ItemListingDirectory object and ListIt Database for long-term
     * storage.
     * @param itemListing A user-created ItemListing object.
     * @throws CurrentUserException An error message if the user is not logged in attempts to
     *                              create an item listing.
     * @throws SQLException An exception that is thrown if the ListIt Database is unreachable.
     */
    public void runVerifyItemListingMenu(ItemListing itemListing) throws CurrentUserException, SQLException {
        String userConfirmation;

        Scanner userInput = new Scanner(System.in);
        System.out.println("Is the following information about your item listing correct? " +
                "Enter “yes” to confirm, “no” to try again, or “quit” to return to the main menu:");
        System.out.println(itemListing);
        userConfirmation = userInput.nextLine();
        if (userConfirmation.toLowerCase().equals("yes")) {
            System.out.println("You confirmed that the item listing information is correct. Would " +
                    "you like to post the item listing to ListIt? Enter “yes” or “no”:");
            userConfirmation = userInput.nextLine();
            if (userConfirmation.toLowerCase().equals("yes")) {
                listItSystem.getListItItemDirectory().addToDirectory(itemListing);
                listItSystem.getListItItemDatabase().insert(itemListing);
                System.out.println("Your item listing has been successfully listed for sale on " +
                        "ListIt!\n");
                runListItMenu();
            } else if (userConfirmation.toLowerCase().equals("no")) {
                runListItMenu();
            } else {
                System.out.println("Error: Enter a valid option, please try again.");
                runVerifyItemListingMenu(itemListing);
            }
        } else if (userConfirmation.toLowerCase().equals("no")) {
            runCreateItemListingMenu();
        } else if (userConfirmation.toLowerCase().equals("quit")) {
            runListItMenu();
        } else {
            System.out.println("Error: Enter a valid option, please try again.");
            runVerifyItemListingMenu(itemListing);
        }
    }
}