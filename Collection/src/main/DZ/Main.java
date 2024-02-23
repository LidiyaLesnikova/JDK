package DZ;

/*
Создать класс справочник сотрудников, который содержит внутри
коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
Табельный номер
Номер телефона
Имя
Стаж
Добавить метод, который ищет сотрудника по стажу (может быть список)
Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
Добавить метод, который ищет сотрудника по табельному номеру
Добавить метод добавление нового сотрудника в справочник
 */

public class Main {
    public static void main(String[] args) {
        EmployeeDirectory employeeDirectory = new EmployeeDirectory<>();
        employeeDirectory.addObjectEmployeeDirectory(new Employee("1234567890", "Иванов Иван Иванович", 5.3));
        employeeDirectory.addObjectEmployeeDirectory(new Employee("1472583691", "Сидоров Петр Петрович", 10.1));
        employeeDirectory.addObjectEmployeeDirectory(new Employee("9876543217", "Петров Олег Иванович", 1.5));
        employeeDirectory.addObjectEmployeeDirectory(new Employee("7418529630", "Степанова Раиса Игоревна", 15));
        System.out.println(employeeDirectory);

        System.out.printf("\nСотрудники со стажем более 7 лет:\n%s\n",employeeDirectory.findFullNameByExperience(7));
        System.out.printf("\nСотрудник с табельным номером 3:\n%s\n",employeeDirectory.findFullNameByNumber(3));
        System.out.printf("\nТелефон сотрудника \"Иван\":\n%s\n",employeeDirectory.findTelephoneByName("Иван"));
    }

}
