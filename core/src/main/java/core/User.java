package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

private String username;
private String password;
private List<ShopeeList> shoppingLists; 

// helperlist which contains all legal domenes for the email
protected static String[] cc = {"ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw", "com"};
protected List<String> newList = Arrays.asList(cc);

/**
 * Constructor for a user that sets the username, password 
 * and creates a list to store all the different shopeelists in
 * @param username
 * @param password
 * @param shoppingLists
 */
public User(String username, String password) {
    if (validateArguments(username, password)) {
        this.username = username;
        this.password = password;
        shoppingLists = new ArrayList<>();
    }
}

public User(){

}

/**
 * 
 * @param username
 * @param password
 * @return True if username and password meets usercriteria, False otherwise
 */

private boolean validateArguments(String username, String password){
    boolean usernameValid = false;
    boolean passwordValid = false;
    
    // validate username: email
    if (username.indexOf('@') == -1) {
        throw new IllegalArgumentException("Missing @");
    }
    String [] usernameSplit = username.split("@");
    String nameSplit = usernameSplit[0];
    String [] domeneSplit = usernameSplit[1].split("\\.");

    char firstLetter = nameSplit.charAt(0);
    
    if (!(Character.isLetter(firstLetter) ||  nameSplit.length() >= 2)) {
        throw new IllegalArgumentException("The username must begin with a letter, and have a length of minimum two letters before @");
    }
    if (domeneSplit.length != 2 || domeneSplit[domeneSplit.length - 1] != "com") {
        throw new IllegalArgumentException("The domain needs to be two letters long or 'com'");
    }
    if (!newList.contains(domeneSplit[1])) {
        throw new IllegalArgumentException("Dette domenet eksisterer ikke");
    }
    usernameValid = true;
    
    // validate password
    if (password.length() < 8) {
        throw new IllegalArgumentException("The password needs to be at least 8 characters long");
    }
    String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$";
    if (!password.matches(regex)) {
        throw new IllegalArgumentException("The password must contain letters, digits and special characters");
    }
    passwordValid = true;
    
    return (usernameValid && passwordValid);
}

public String getUsername(){
    return username;
}

public String getPassword(){
    return password;
}

public List<ShopeeList> getShoppinglists(){
    return shoppingLists;
}

public void setUsername(String username){
    this.username = username;
}

public void setPassword(String password){
    this.password = password;
}


}

