package DZ;

public class Employee {
    private static int counterNumbers=1;
    private int number;
    private String telephone;
    private String fullName;
    private double experience;

    public Employee(String telephone, String fullName, double experience) {
        this.number = counterNumbers++;
        this.telephone = telephone;
        this.fullName = fullName;
        this.experience = experience;
    }

    public int getNumber() {
        return number;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getFullName() {
        return fullName;
    }
    public double getExperience() {
        return experience;
    }
    @Override
    public String toString() {
        return "ФИО: "+fullName+"(таб.номер "+number+", телефон "+telephone+", стаж "+experience+")\n";
    }
}
