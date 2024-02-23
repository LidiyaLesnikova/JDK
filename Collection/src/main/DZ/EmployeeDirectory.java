package DZ;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectory<T extends Employee> implements EmployeeDirectoryInterface<T> {
    List<T> listEmployeeDirectory;

    public EmployeeDirectory() {
        listEmployeeDirectory = new ArrayList<>();
    }
    @Override
    public void addObjectEmployeeDirectory(T employee) {
        listEmployeeDirectory.add(employee);
    }
    @Override
    public String findFullNameByExperience(double experience) {
        return listEmployeeDirectory.stream().filter(e -> e.getExperience()>=experience).toList().toString();
    }
    @Override
    public String findTelephoneByName(String name) {
        return listEmployeeDirectory.stream().filter(e -> e.getFullName().contains(name)).toList().toString();
    }
    @Override
    public String findFullNameByNumber(int number) {
        return listEmployeeDirectory.stream().filter(e -> e.getNumber()==number).toList().toString();
    }
    @Override
    public String toString() {
        return "СПРАВОЧНИК СОТРУДНИКОВ: \n"
                + listEmployeeDirectory;
    }
}
