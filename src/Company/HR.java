package Company;

import Project.Project;
import Department.Department;
import Employee.Accountant;
import Employee.Developer;
import Employee.Employee;
import java.util.ArrayList;
import java.util.Scanner;

public class HR {

    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Employee> allEmployees = new ArrayList<>();
    public static ArrayList<Project> allProjects = new ArrayList<>();
    public static ArrayList<Department> allDepartments = new ArrayList<>();

    static void operationsForHR() {

        int option, temp;
        do {
            System.out.println("All of this is to Employee\nEnter National id for employee :");
            String id = input.nextLine();
            Employee emp = Employee.searchEmp_By_ID(id);
            System.out.println("****************************************************************");
            System.out.println(
                    "1-View Detailed\n2-Take Vacation\n"
                    + "3-Take Permission Hours\n4-Calculate Salary for this Employee\n"
                    + "5-(*(((((*((((((()))))) For this Employee"
                    + "6-Free All constants\n7-Separation\n"
                    + "if HR want to close the console Enter ZERO");
            option = input.nextInt();
            switch (option) {
                case 1: {
                    emp.viewDetailed();
                    break;
                }
                case 2: {
                    System.out.println("Enter Number of Days");
                    temp = input.nextInt();
                    emp.takeVacation(temp);
                    break;
                }
                case 3: {
                    System.out.println("Enter Number of Hours");
                    temp = input.nextInt();
                    emp.takePermissionHours(temp);
                    break;
                }
                case 4: {
                    System.out.println(emp.calculateActualSalary());
                    break;
                }
                case 5: {
                    break;
                }
                case 6: {
                    emp.freeAll();
                    break;
                }
                case 7: {
                    allEmployees.remove(emp);
                    break;
                }

            }
        } while (option != 0);
    }

    public static void main(String[] args) {
        operationsForHR();

    }

    static void enterAllDummies() {
        dummyEmployees();
        dummyDepartments();
        dummyProjects();
    }

    static void dummyEmployees() {
        for (int i = 0; i < 10; i++) {
            allEmployees.add(new Developer("Allam Dev " + i, "DEV " + i, 'm', "Android " + i));
            allEmployees.add(new Accountant("Allam Acc " + i, "ACC " + i, 'm'));
        }
    }

    static void dummyDepartments() {
        for (int i = 0; i < 3; i++) {
            allDepartments.add(new Department("Department Number " + i, allEmployees.get(0), 30000));
        }
    }

    static void dummyProjects() {
        for (int i = 0; i < 4; i++) {
            allProjects.add(new Project("Project Number " + i, "Address" + i));
        }
    }

}
