package directories;

import users.UserAccount;

import java.sql.*;
import java.util.ArrayList;

/**
 * A class which is used to store and manage UserAccount objects.
 */
public class UserDirectory extends GenericDirectory<UserAccount> {
    private final ArrayList<UserAccount> userDirectory;

    /**
     * A constructor method for a UserDirectory object which instantiates an array list of
     * UserAccount objects.
     */
    public UserDirectory() {
        userDirectory = super.directory;
    }

    /**
     * A method which determines if a username and password match the username and password of a
     * UserAccount object contained in a UserDirectory object.
     * @param username A String representing the username of a UserAccount object.
     * @param password A String representing the password associated with a UserAccount object.
     * @return A UserAccount object if the username and password match a UserAccount object in
     * the UserDirectory object, or null otherwise.
     */
    public UserAccount verifyUser(String username, String password) {
        for (UserAccount elem : userDirectory) {
            if (username.equals(elem.getUsername())) {
                if (password.equals(elem.getPassword())) {
                    return elem;
                }
            }
        }
        return null;
    }

    /**
     * A method which iterates through each UserAccount in the UserDirectory object to
     * check if a username is already being used.
     * @param username A String representing the username associated with a UserAccount object.
     * @return A boolean true value if the username is available or a boolean false value if the
     * username is taken.
     */
    public boolean checkUsername(String username) {
        UserAccount checkedUser = userDirectory.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findAny()
                .orElse(null);
        if (checkedUser == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method which reads in ListIt User data from the ListIt Database, creates UserAccount
     * objects, and adds each UserAccount object to the UserDirectory object.
     */
    public synchronized void readInUsers() {
        String url = "jdbc:sqlite:src/databases/ListItDatabase.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("ListIt User Database connected.");
            String sql = "SELECT FirstName, LastName, Username, UserPassword FROM User";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    UserAccount user = new UserAccount(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4));
                    userDirectory.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: The ListIt Database could not be accessed.");
            e.printStackTrace();
        }
    }
}