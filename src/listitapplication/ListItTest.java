package listitapplication;

import directories.GenericDirectory;
import directories.ItemListingDirectory;
import directories.UserDirectory;
import exceptions.CurrentUserException;
import itemlistings.ElectronicsListing;
import itemlistings.ItemListing;
import org.junit.Before;
import org.junit.Test;
import users.UserAccount;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class utilizes JUnit4 to test the functionality of the ListIt system.
 */
public class ListItTest {

    private ListItSystem listItSystem;
    private ListItMenu listItMenu;
    private UserAccount user1;
    private UserAccount user2;
    private ItemListing item1;
    private ItemListing item2;

    /**
     * This method creates objects prior to running JUnit tests.
     */
    @Before
    public void init() {
        // Creates a single, unique ListIt system.
        listItSystem = ListItSystem.getInstance();

        // Creates a menu for the created ListItSystem object.
        listItMenu = new ListItMenu(listItSystem);

        // Instantiates users to be used in the following JUnit tests.
        user1 = new UserAccount("Bill", "Thompson", "bt1", "user1pass");
        user2 = new UserAccount("Roger", "Smith", "rs2", "user2pass");

        // Adds the users to the ListIt UserDirectory object.
        listItSystem.getListItUserDirectory().addToDirectory(user1);
        listItSystem.getListItUserDirectory().addToDirectory(user2);

        // New item listing objects are created by users to be used in the following JUnit tests.
        item1 = user1.createElectronicsListing(user1.getUsername(), "55” OLED 2k TV", 300, 55,
                "Pre-owned - Excellent", "Shipping or Pick-up", "A 55” OLED 2k TV purchased in " +
                        "2019 that is in excellent condition. The TV is fully functional and " +
                        "comes with the original box, manual, and remote. Please, contact me if" +
                        " you are interested in this TV.");
        item2 = user2.createHouseholdGoodsListing(user2.getUsername(), "Coffee Table", 120, 58,
                "Pre-owned - Good", "Pick-up", "A wooden coffee table that is approximately 58 " +
                        "lbs and in fairly good shape. Available for local pick-up, please " +
                        "contact me if you are interested.");
    }

    /**
     * Tests the UserAccount class and its associated usage in the ListIt system.
     */
    @Test
    public void listItUserTest() {
        // Checks that the user2 object was constructed as expected.
        assertEquals(user2.getUsername(),"rs2");

        // The user at index 2 should be user2 since this user was added to the directory last.
        assertEquals(listItSystem.getListItUserDirectory().get(1).getUsername(), user2.getUsername());
    }

    /**
     * Tests the ItemListing class and its associated usage in the ListIt system.
     */
    @Test
    public void itemListingTest() {
        // Tests that the item listing was constructed as expected.
        assertEquals(item1.getTitle(),"55” OLED 2k TV");

        // Tests that the item listing can be displayed as a String as expected.
        assertEquals(item1.toString(), "Category: Electronics\n" +
                "Title: 55” OLED 2k TV\n" +
                "Price: 300.0\n" +
                "Screen Size: 55\n" +
                "Condition: Pre-owned - Excellent\n" +
                "Delivery Method: Shipping or Pick-up\n" +
                "Description: A 55” OLED 2k TV purchased in 2019 that is in excellent condition. " +
                "The TV is fully functional and comes with the original box, manual, and remote. " +
                "Please, contact me if you are interested in this TV.");

        // Adds the previously created item listings to the ListIt item directory.
        listItSystem.getListItItemDirectory().addToDirectory(item1);
        listItSystem.getListItItemDirectory().addToDirectory(item2);

        // Tests that the ListIt item directory is not empty since item listings have been added.
        assertFalse(listItSystem.getListItItemDirectory().isEmpty());
    }

    /**
     * Tests that UserAccount objects are able to create ItemListing objects as expected.
     */
    @Test
    public void userAccountTest() {
        UserAccount newUser = new UserAccount("Bob", "Smith", "BBS19", "mypassword");
        ElectronicsListing newEListing = (ElectronicsListing) newUser.createElectronicsListing(newUser.getUsername(),
                "iPhone 12", 550, 7, "Pre-owned - Excellent", "Shipping or Pick-up", "For sale is" +
                        " a used iPhone 12 that is in great condition. Please contact me if you " +
                        "are interested, thank you.");
        assertEquals("Category: Electronics\n" +
                "Title: iPhone 12\n" +
                "Price: 550.0\n" +
                "Screen Size: 7\n" +
                "Condition: Pre-owned - Excellent\n" +
                "Delivery Method: Shipping or Pick-up\n" +
                "Description: For sale is a used iPhone 12 that is in great condition. " +
                "Please contact me if you are interested, thank you.", newEListing.toString());
    }

    /**
     * Tests that the shipping costs are calculated as expected for specific item listings.
     */
    @Test
    public void shippingCostTest() {
        // The item1 item listing should cost $100 to ship.
        assertEquals(item1.calculateShippingCost(), 100);

        // The item2 item listing is not available to be shipped so the calculated shipping is -1.
        assertEquals(item2.calculateShippingCost(),-1);
    }

    /**
     * Tests that the verifyUser() method within the UserDirectory class works as expected.
     */
    @Test
    public void verifyUserTest() {
        // Uses the verifyUser() method to return a UserAccount object from the user directory.
        UserAccount verifiedUser2 = listItSystem.getListItUserDirectory().verifyUser("rs2",
                "user2pass");
        // Assertions below test that the original user2 object is equal to the verified user object.
        assertEquals(user2.getUsername(), verifiedUser2.getUsername());
        assertEquals(user2.getFirstName(), verifiedUser2.getFirstName());
        assertEquals(user2.getLastName(), verifiedUser2.getLastName());
        assertEquals(user2.getPassword(), verifiedUser2.getPassword());
    }

    /**
     * Tests that the method contained in the GenericDirectory class work as expected.
     */
    @Test
    public void genericDirectoryTest() {
        // Creates a GenericDirectory object of ItemListing objects.
        GenericDirectory<ItemListing> genericItemDirectory = new ItemListingDirectory();
        // The GenericDirectory object should be empty.
        assertTrue(genericItemDirectory.isEmpty());
        genericItemDirectory.addToDirectory(item1);
        genericItemDirectory.addToDirectory(item2);
        // Tests that the ItemListing objects were added to the GenericDirectory object and that
        // the getter method for the class works as expected.
        assertEquals(item1, genericItemDirectory.get(0));

        // Creates a GenericDirectory object of UserAccount objects.
        GenericDirectory<UserAccount> genericUserDirectory = new UserDirectory();
        // The GenericDirectory object should be empty.
        assertTrue(genericUserDirectory.isEmpty());
        genericUserDirectory.addToDirectory(user1);
        genericUserDirectory.addToDirectory(user2);
        // Tests that the UserAccount objects were added to the GenericDirectory object and that
        // the getter method for the class works as expected.
        assertEquals(user2, genericUserDirectory.get(1));
    }

    /**
     * Tests that the ListItMenu runLogoutMenu() method works as expected.
     */
    @Test
    public void logoutTest() throws CurrentUserException {
        // Assigns a UserAccount object as the ListItSystem's current user.
        listItSystem.setCurrentUser(user1);
        // Tests that the current user is the UserAccount object that the attribute was set to.
        assertEquals(user1, listItSystem.getCurrentUser());
        // The runLogoutMenu() method is called which sets the ListItSystem's current user to null.
        listItMenu.runLogoutMenu();
        // Tests that the ListItSystem's current user is in fact null.
        assertNull(listItSystem.getCurrentUser());
    }

    /**
     * Tests that the checkUsername() method works as expected.
     */
    @Test
    public void checkUsernameTest() {
        // Checks that bt1 is a username that is already being used.
        assertFalse(listItSystem.getListItUserDirectory().checkUsername("bt1"));
        // Checks that eg1 is a username that is available.
        assertTrue(listItSystem.getListItUserDirectory().checkUsername("eg1"));
    }

    /**
     * Tests that the readInUsers() method works as expected.
     */
    @Test
    public void readInUsersTest() {
        // Reads in UserAccount objects from the UserDatabase.db file.
        listItSystem.getListItUserDirectory().readInUsers();
        // Confirms that the UserAccount objects were successfully read in from the file.
        assertFalse(listItSystem.getListItUserDirectory().checkUsername("jb87"));
    }

    /**
     * Tests that the readInItemListings() method works as expected.
     */
    @Test
    public void readInItemListingsTest() {
        // Reads in ItemListing objects from the itemlistings.dat file.
        listItSystem.getListItItemDirectory().readInItemListings();
        // Confirms that the ItemListing objects were successfully read in from the file.
        assertEquals(listItSystem.getListItItemDirectory().get(1).getTitle(), "Desk Lamp");
    }

    /**
     * Tests that the InitializeListItUsers class's run() method works as expected.
     */
    @Test
    public void initializeListItUsersTests() {
        // Creates a new InitializeListItUsers object.
        Main.InitializeListItUsers initializeListItUsers =
                new Main.InitializeListItUsers(listItSystem);
        // Call to the run() method.
        initializeListItUsers.run();
        // Gets the first UserAccount object in the ListIt user directory.
        UserAccount testedUser = listItSystem.getListItUserDirectory().get(0);
        // Tests the values with an assertion statement.
        assertEquals(testedUser.getUsername(), "bt1");
    }

    /**
     * Tests that the InitializeListItItemListings class's run() method works as expected.
     */
    @Test
    public void initializeItemListingsTest() {
        // Creates a new InitializeListItItemListings object.
        Main.InitializeListItItemListings initializeListItItemListings =
                new Main.InitializeListItItemListings(listItSystem);
        // Call to the run() method.
        initializeListItItemListings.run();
        // Gets the first ItemListing object in the ListIt item listing directory.
        ItemListing testedItemListing = listItSystem.getListItItemDirectory().get(0);
        // Tests the values with an assertion statement.
        assertEquals(testedItemListing.getTitle(), "iPhone 12");
    }

}