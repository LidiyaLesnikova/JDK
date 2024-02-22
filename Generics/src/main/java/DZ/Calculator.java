package DZ;

public class Calculator extends Exception {
    public <T extends Number> double sum(T num1, T num2){
        return num1.doubleValue() + num2.doubleValue();
    }
    public <T extends Number> double multiply(T num1, T num2){
        return num1.doubleValue() * num2.doubleValue();
    }
    public <T extends Number> String divide(T num1, T num2){
        if (num2.doubleValue()==0) {
            return "На ноль делить нельзя";
        } else {
            return String.valueOf(num1.doubleValue() / num2.doubleValue());
        }
    }
    public <T extends Number> double subtract(T num1, T num2){
        return num1.doubleValue() - num2.doubleValue();
    }
}
