package shopee.core;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class User {

String username;
String password;
List<ShopeeList> shopeeLists;
//ShopeeList shopeeList; // Change this to a list of lists later when the user can have multiple shopeelists

// Helper list which contains all legal domenes for the email
final static String[] cc = {"ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw", "com"};
protected List<String> newList = Arrays.asList(cc);

/**
 * Constructor for a user that sets the username and password 
 * 
 * @param username
 * @param password
 */
public User(String username, String password) {
    setUsername(username);
    setPassword(password);
    //shopeeList = new ShopeeList("List");
    shopeeLists = new ArrayList<ShopeeList>();
}

// Used for testing
public User(){
}

/**
 * Checks if the username is valid, i.e. it needs to meet the following criteria:
 *  - The email must contain a @-symbol
 *  - The string before the @-symbol must begin with a letter and be at least two characters long.
 *  - The email domian must exist in the helper list called newList
 * 
 * @param username
 * @return True if username meets criteria, False otherwise
 */
private boolean validUsername(String username){
    boolean usernameValid = false;
    
    if (username.indexOf('@') == -1) {
        throw new IllegalArgumentException("Missing @");
    }

    String [] usernameSplit = username.split("@");
    String nameSplit = usernameSplit[0];
    String domeneSplit = usernameSplit[1].split("\\.")[1];
    char firstLetter = nameSplit.charAt(0); 
    if (!(Character.isLetter(firstLetter) &&  nameSplit.length() >= 2)) {
        throw new IllegalArgumentException("The username must begin with a letter, and have a length of minimum two letters before @");
    }
    if (!newList.contains(domeneSplit)) {
        throw new IllegalArgumentException("This domain doesnt exist.");
    }
    
    usernameValid = true;
    return usernameValid;
}

/**
 * Checks if the password is valid, i.e. it needs to meet the following criteria:
 *  - The password needs to be at least 8 characters long
 *  - The password must contain letters, digits and special characters
 * 
 * @param password
 * @return True if password meets criteria, False otherwise
 */
private boolean validPassword(String password){
    boolean passwordValid = false;

    if (password.length() < 8) {
        throw new IllegalArgumentException("The password needs to be at least 8 characters long");
    }

    String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!/]).*$";
    if (!password.matches(regex)) {
        throw new IllegalArgumentException("The password must contain letters, digits and special characters");
    }

    passwordValid = true;
    return passwordValid;
}

/**
 * Returns the username
 * 
 * @return username 
 */
public String getUsername(){
    return username;
}

/**
 * Returns the password
 * 
 * @return password
 */
public String getPassword(){
    return password;
}

/**
 * Sets the username if the username is valid
 * 
 * @param username
 */
public void setUsername(String username){
    if(validUsername(username)) {
        this.username = username;
    }
}

/**
 * Sets the password if the password is valid
 * 
 * @param password
 */
public void setPassword(String password){
    if(validPassword(password)) {
        this.password = password;
    }
}

/**
 * Sets the users shopeeList
 * 
 * @param list
 */
public void setShopeeLists(List<ShopeeList> list){
    this.shopeeLists = list;
}

/**
 * Returns the users shopeeList
 * 
 * @return shopeeList
 */
public List<ShopeeList> getShopeeLists(){
    return this.shopeeLists;
}

public void addShopeeList(ShopeeList list){
    for (ShopeeList shopeeList : this.shopeeLists) {
        if(shopeeList.getListName().equals(list.getListName())){
            throw new IllegalArgumentException("Cant use same list name twice");
        }
    }    
    
    this.shopeeLists.add(list);
    }

public ShopeeList getShopeeList(String name){
    for (ShopeeList list : this.shopeeLists) {
        if(list.getListName().equals(name)){
            return list;
        }
    }
    throw new IllegalArgumentException("No such list name for this user");
}

public void deleteShopeeList(String name) {
    for(ShopeeList list : this.shopeeLists) {
        if(list.getListName().equals(name)) {
            this.shopeeLists.remove(list);
        }
        else {
            throw new IllegalArgumentException("The list does not exist");
        }
    }  
}

}



