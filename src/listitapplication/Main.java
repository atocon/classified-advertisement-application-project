package listitapplication;

import exceptions.CurrentUserException;

import java.sql.SQLException;

/**
 * A class which represents the implementation of the ListIt application.
 */
public class Main {

    /**
     * The main runnable method for the ListIt Application.
     * @param args A collection of String objects.
     * @throws CurrentUserException An exception which checks that the ListItSystem's currentUser
     * attribute is set to a valid UserAccount object.
     * @throws InterruptedException An exception which is thrown when an active thread is
     * interrupted.
     */
    public static void main(String[] args) throws InterruptedException, CurrentUserException, SQLException {

        // Instantiates a single, unique ListItSystem object.
        ListItSystem listItSystem = ListItSystem.getInstance();

        // Creates runnable objects which can be passed to separate threads for processing.
        InitializeListItUsers initializeUsers = new InitializeListItUsers(listItSystem);
        InitializeListItItemListings initializeItemListings =
                new InitializeListItItemListings(listItSystem);

        // Create separate Thread objects to read in UserAccount objects and ItemListing objects.
        Thread thread1 = new Thread(initializeUsers);
        Thread thread2 = new Thread(initializeItemListings);

        // Starts each of the created threads to initialize the ListItSystem.
        thread1.start();
        thread2.start();

        // Ensures the created threads run prior to the main thread for proper system initialization.
        thread1.join();
        thread2.join();
        System.out.println();

        // Demonstrates that ListIt Users can be displayed from the ListIt Database.
        listItSystem.getListItUserDatabase().displayUsers();

        //
        listItSystem.getListItUserDatabase().displayUserListings(listItSystem.getListItUserDirectory().get(4));

        // A ListItMenu object is instantiated so users can interact with the system.
        ListItMenu listItMenu = new ListItMenu(listItSystem);

        // Runs the ListIt menu so users can interact with the ListIt system.
        listItMenu.runListItMenu();
    }

    /**
     * A runnable class which can be used to read in UserAccount objects to initialize the ListIt
     * System.
     */
    static class InitializeListItUsers implements Runnable {
        private final ListItSystem listItSystem;

        /**
         * A constructor method which creates a runnable object which can be used to read in user
         * accounts to the ListIt System.
         * @param listItSystem A ListItSystem object representing the ListIt System to which user
         *                    account objects will be read.
         */
        public InitializeListItUsers(ListItSystem listItSystem) {
            this.listItSystem = listItSystem;
        }

        /**
         * A method which allows a thread to be created to read user accounts into a ListItSystem
         * object.
         */
        @Override
        public void run() {
            listItSystem.getListItUserDirectory().readInUsers();
            System.out.println("ListIt users successfully initialized.");
        }
    }

    /**
     * A runnable class which can be used to read in ItemListing objects to initialize the ListIt
     * System.
     */
    static class InitializeListItItemListings implements Runnable {
        private final ListItSystem listItSystem;

        /**
         * A constructor method which creates a runnable object which can be used to read in item
         * listings to the ListIt System.
         * @param listItSystem A ListItSystem object representing the ListIt System to which item
         *                    listing objects will be read.
         */
        public InitializeListItItemListings(ListItSystem listItSystem) {
            this.listItSystem = listItSystem;
        }

        /**
         * A method which allows a thread to be created to read item listings into a ListItSystem
         * object.
         */
        @Override
        public void run() {
            listItSystem.getListItItemDirectory().readInItemListings();
            System.out.println("ListIt item listings successfully initialized.");
        }
    }
}