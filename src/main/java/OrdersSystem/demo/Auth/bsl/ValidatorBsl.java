package OrdersSystem.demo.Auth.bsl;

import org.springframework.stereotype.Service;

@Service
public class ValidatorBsl implements IValidatorBsl {
    //validate email format and length and return true if valid and false if not
    @Override
    public boolean validatEmail(String email) {
        if (email.length() < 8) {
            System.out.println("Email must be at least 8 characters");
            return false;
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.println("Email must be in the correct format");
            return false;
        } else {
            return true;
        }
    }
    //validate name format and length and return true if valid and false if not
    @Override
    public boolean validatName(String input) {
        if (input.length() < 3) {
            System.out.println("Name must be at least 3 characters");
            return false;
        } else if (!input.matches("^[a-zA-Z\\s]*$")) {
            System.out.println("Name must be alphabetic");
            return false;
        } else {
            return true;
        }
    }
    //validate password format and length and return true if valid and false if not
    @Override
    public boolean validatPassword(String password) {
        if (password.length() < 8) {
            return false;
        } else if (!password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            return false;
        } else {
            return true;
        }
    }
}
