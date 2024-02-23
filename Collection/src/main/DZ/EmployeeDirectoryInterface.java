package DZ;

public interface EmployeeDirectoryInterface<T> {
    void addObjectEmployeeDirectory(T employee);
    String findFullNameByExperience(double experience);
    String findTelephoneByName(String name);
    String findFullNameByNumber(int number);
}
