public interface IAuthentication {
    public User login(String username, String password);
    public Boolean register(User user);
}
