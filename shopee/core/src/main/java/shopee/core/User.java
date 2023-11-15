package shopee.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User class represents a user in the system.
 */
public class User {

    private String username;
    private String password;
    private List<ShopeeList> shopeeLists;

    // Helper list which contains all legal domains for the email
    private static final String[] CC = {
        "ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq",
        "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb",
        "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm",
        "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by",
        "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck",
        "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx",
        "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec",
        "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk",
        "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg",
        "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs",
        "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht",
        "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir",
        "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh",
        "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la",
        "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv",
        "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk",
        "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt",
        "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne",
        "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz",
        "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm",
        "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro",
        "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg",
        "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr",
        "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf",
        "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr",
        "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy",
        "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf",
        "ws", "ye", "yt", "za", "zm", "zw", "com"};

    private final List<String> newList = Arrays.asList(CC);

    /**
     * Constructor for a user that sets the username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(final String username, final String password) {
        setUsername(username);
        setPassword(password);
        shopeeLists = new ArrayList<>();
    }

    // Used for testing
    public User() {
    }

    /**
     * Checks if the username is valid, i.e., it needs to meet the following criteria:
     * - The email must contain an @-symbol
     * - The string before the @-symbol must begin with a letter and be at least 
     * two characters long.
     * - The email domain must exist in the helper list called newList
     *
     * @param username the username to validate
     * @return true if username meets criteria, false otherwise
     * @throws IllegalArgumentException if the username is invalid
     */
    private boolean validUsername(final String username) {
        if (username.indexOf('@') == -1) {
            throw new IllegalArgumentException("Missing @");
        }

        final String[] usernameSplit = username.split("@");
        final String nameSplit = usernameSplit[0];
        final String domainSplit = usernameSplit[1].split("\\.")[1];
        final char firstLetter = nameSplit.charAt(0);
        if (!(Character.isLetter(firstLetter) && nameSplit.length() >= 2)) {
            throw new IllegalArgumentException("Illegal username, should be name@something.domain");
        }
        if (!newList.contains(domainSplit)) {
            throw new IllegalArgumentException("This domain doesn't exist.");
        }

        return true;
    }

    /**
     * Checks if the password is valid, i.e., it needs to meet the following criteria:
     * - The password needs to be at least 8 characters long
     * - The password must contain letters, digits, and special characters
     *
     * @param password the password to validate
     * @return true if password meets criteria, false otherwise
     * @throws IllegalArgumentException if the password is invalid
     */
    private boolean validPassword(final String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password needs to be at least 8 characters long");
        }

        final String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!/]).*$";
        if (!password.matches(regex)) {
            throw new IllegalArgumentException("Password must have letters, digits and characters");
        }

        return true;
    }

    /**
     * Returns the username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username if the username is valid.
     *
     * @param username the username to set
     * @throws IllegalArgumentException if the username is invalid
     */
    public void setUsername(final String username) {
        validUsername(username);
        this.username = username;
    }

    /**
     * Sets the password if the password is valid.
     *
     * @param password the password to set
     * @throws IllegalArgumentException if the password is invalid
     */
    public void setPassword(final String password) {
        validPassword(password);
        this.password = password;
    }

    /**
     * Sets the user's shopeeList.
     *
     * @param list the list to set
     */
    public void setShopeeLists(final List<ShopeeList> list) {
        this.shopeeLists = list;
    }

    /**
     * Returns the user's shopeeList.
     *
     * @return shopeeList
     */
    public List<ShopeeList> getShopeeLists() {
        return this.shopeeLists;
    }

    /**
     * This adds a ShopeeList object to the user's list.
     *
     * @param list the list to add
     * @throws IllegalArgumentException if the list name already exists
     */
    public void addShopeeList(final ShopeeList list) {
        for (final ShopeeList shopeeList : this.shopeeLists) {
            if (shopeeList.getListName().equals(list.getListName())) {
                throw new IllegalArgumentException("Cannot use the same list name twice.");
            }
        }
        this.shopeeLists.add(list);
    }

    /**
     * This method adds a ShopeeList at a specified index.
     *
     * @param index   the index to add the list
     * @param newList the list to add
     */
    public void addShopeeList(final int index, final ShopeeList newList) {
        this.shopeeLists.add(index, newList);
    }

    /**
     * Returns a ShopeeList with a given name.
     *
     * @param name the name of the list to retrieve
     * @return the ShopeeList object with the given name if the list exists
     * @throws IllegalArgumentException if the list name does not exist for this user
     */
    public ShopeeList getShopeeList(final String name) {
        for (final ShopeeList list : this.shopeeLists) {
            if (list.getListName().equals(name)) {
                return list;
            }
        }
        throw new IllegalArgumentException("No such list name for this user.");
    }

    /**
     * This method removes a ShopeeList at a certain index.
     *
     * @param index the index to remove
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void deleteShopeeList(final int index) {
        if (index >= 0 && index < this.shopeeLists.size()) {
            this.shopeeLists.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    /**
     * This method removes a ShopeeList with a certain list name.
     *
     * @param listName the name of the list to remove
     */
    public void deleteShopeeList(final String listName) {
        this.shopeeLists.remove(this.getShopeeList(listName));
    }

    /**
     * This method replaces an old list with a new one.
     *
     * @param listName the name of the list to replace
     * @param newList  the new ShopeeList which replaces the old one
     * @throws IllegalArgumentException if the list name does not exist for this user
     */
    public void replaceShopeeList(final String listName, final ShopeeList newList) {
        int index = -1;
        boolean found = false;

        for (final ShopeeList list : this.shopeeLists) {
            index++;
            if (list.getListName().equals(listName)) {
                this.deleteShopeeList(listName);
                found = true;
                break;
            }
        }

        if (found) {
            this.addShopeeList(index, newList);
        } else {
            throw new IllegalArgumentException("No such list name for this user.");
        }
    }
}




