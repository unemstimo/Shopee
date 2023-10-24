package shopee.ui.dataaccess;

import shopee.core.ShopeeList;
import shopee.core.User;

public class RemoteUserAccess implements UserAccess{

    @Override
    public User loadUser(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUser'");
    }

    @Override
    public boolean editShopeeList(String listName, ShopeeList editedList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editShopeeList'");
    }

    @Override
    public boolean addShopeeList(ShopeeList shopeeList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addShopeeList'");
    }

    @Override
    public boolean deleteShopeeList(ShopeeList shopeeList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteShopeeList'");
    }

    @Override
    public void setUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUser'");
    }

    @Override
    public User getUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }
    
}
