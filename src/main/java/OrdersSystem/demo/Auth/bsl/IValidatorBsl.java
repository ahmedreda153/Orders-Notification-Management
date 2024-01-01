package OrdersSystem.demo.Auth.bsl;


public interface IValidatorBsl {
    public boolean validatEmail(String input);
    public boolean validatName(String input);
    public boolean validatPassword(String input);
}
