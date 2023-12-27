public class Authentication implements IAuthentication {
    private UserDB userDB;
    private IValidator validator;

    @Override
    public User login(String username, String password) {
        return userDB.login(username, password);
    }

    @Override
    public Boolean register(User user) {
        if (!validator.validatEmail(user.getemail()) && !validator.validatPassword(user.getPassword())) {
            return false;
        }
        return userDB.register(user);
    }
}
