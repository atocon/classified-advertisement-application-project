package listitapplication;

import databases.ItemListingDatabase;
import databases.UserDatabase;
import directories.ItemListingDirectory;
import directories.UserDirectory;
import users.UserAccount;

/**
 * A class which represents a facade for the ListIt System which maintains a directory of users
 * and item listings.
 */
public class ListItSystem {
    private ItemListingDirectory listItItemDirectory;
    private ItemListingDatabase listItItemDatabase;
    private UserDirectory listItUserDirectory;
    private UserDatabase listItUserDatabase;
    private UserAccount currentUser;
    // A single, unique instance of a ListItSystem object.
    private static final ListItSystem instance = new ListItSystem();

    /**
     * A private constructor for ListItSystem objects so the singleton pattern can be implemented.
     */
    private ListItSystem() {
        listItItemDirectory = new ItemListingDirectory();
        listItItemDatabase = new ItemListingDatabase();
        listItUserDirectory = new UserDirectory();
        listItUserDatabase = new UserDatabase();
    }

    /**
     * A method which implements the singleton pattern to get a single, unique instance
     * of a ListItSystem object.
     *
     * @return A unique instance of a ListItSystem object.
     */
    public static ListItSystem getInstance() {
        return instance;
    }

    /**
     * A method which gets the ItemListingDirectory object associated with the single, unique
     * ListItSystem object.
     * @return An ItemListingDirectory object.
     */
    public ItemListingDirectory getListItItemDirectory() {
        return listItItemDirectory;
    }

    /**
     * A method which gets the ItemListingDatabase object associated with the single, unique
     * ListItSystem object.
     * @return An ItemListingDatabase object.
     */
    public ItemListingDatabase getListItItemDatabase() {return listItItemDatabase;}

    /**
     * A method which gets the UserDirectory object associated with the single, unique
     * ListItSystem object.
     * @return A UserDirectory object.
     */
    public UserDirectory getListItUserDirectory() {
        return listItUserDirectory;
    }

    /**
     * A method which gets the UserDatabase object associated with the single, unique
     * ListItSystem object.
     * @return A UserDatabase object.
     */
    public UserDatabase getListItUserDatabase() {return listItUserDatabase;}

    /**
     * A method which gets the current user who is logged into ListIt.
     * @return A UserAccount object.
     */
    public UserAccount getCurrentUser() {
        return currentUser;
    }

    /**
     * A method which sets the ListItSystem's currentUser attribute to a specific UserAccount
     * object, which represents a user being logged-in to the ListIt system.
     * @param currentUser A UserAccount object representing the user who is logged into the
     *                    ListIt system.
     */
    public void setCurrentUser(UserAccount currentUser) {
        this.currentUser = currentUser;
    }
}