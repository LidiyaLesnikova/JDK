package DZ;

public class Calculator {
    public <T extends Number> double sum(T num1, T num2){
        return num1.doubleValue() + num2.doubleValue();
    }
    public <T extends Number> double multiply(T num1, T num2){
        return num1.doubleValue() * num2.doubleValue();
    }
    public <T extends Number> double divide(T num1, T num2){
        return num1.doubleValue() / num2.doubleValue();
    }
    public <T extends Number> double subtract(T num1, T num2){
        return num1.doubleValue() - num2.doubleValue();
    }

    public <T> boolean compareArrays(T arr1, T arr2) {
        boolean rez = false;
        return rez;
    }
}
