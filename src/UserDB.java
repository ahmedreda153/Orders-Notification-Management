import java.util.ArrayList;
/**
 * UserDB
 */
public class UserDB {
    private ArrayList<User> Users;


    public UserDB() {
        Users = new ArrayList<User>();
        
    }

    public UserDB(ArrayList<User> Users) {
        this.Users = Users;
    }

    public ArrayList<User> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<User> Users) {
        this.Users = Users;
    }

    public void addUser(User user) {
        Users.add(user);
    }

    public void removeUser(User user) {
        Users.remove(user);
    }

    public User getUserById(int id) {
        for (User user : Users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User user : Users) {
            if (user.getemail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User login(String email, String password) {
        for (User user : Users) {
            if (user.getemail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        System.out.println("Invalid email or password");
        return null;
    }

    public Boolean register(User user) {
        if (getUserByEmail(user.getemail()) == null) {
            Users.add(user);
            return true;
        }
        System.out.println("Email already exists");
        return false;
    }

    public double getBalance(String email){
        for (User user : Users) {
            if (user.getemail().equals(email)) {
                return user.getBalance();
            }
        }
        return 0;
    }
}