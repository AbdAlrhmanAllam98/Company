package Company;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Employee> allEmployees = new ArrayList<>();
    static ArrayList<Project> allProjects = new ArrayList<>();
    static ArrayList<Department> allDepartments = new ArrayList<>();

    static void operationsForHR() {

        int option, temp;
        do {
            System.out.println("All of this is to Employee\nEnter National id for employee :");
            String id = input.nextLine();
            Employee emp = searchEmp_By_Id(id);
            System.out.println("****************************************************************");
            System.out.println(
                     "1-View Detailed\n2-Take Vacation\n"
                    + "3-Take Permission Hours\n4-Calculate Salary for this Employee\n"
                    + "5-Free All constants\n6-Separation\n"
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
                    emp.freeAll();
                    break;
                }
                case 6: {
                    allEmployees.remove(emp);
                    break;
                }

            }
        } while (option != 0);
    }

    static void addEmployee() {
        System.out.println("Enter employee name : ");
        String name = input.nextLine();
        System.out.println("Enter employee ID : ");
        String id = input.nextLine();
        System.out.println("Enter employee Gender :");
        char gender = input.next().charAt(0);
        Employee emp = null;
        System.out.println("1-Developer\n2-Accountant");
        int option = input.nextInt();
        if (option == 1) {
            input.nextLine();
            System.out.println("what is the framework?");
            String frameWork = input.nextLine();
            emp = new Developer(name, id, gender, frameWork);
        } else if (option == 2) {
            input.nextLine();
            emp = new Accountant(name, id, gender);

        }
        allEmployees.add(emp);
    }

    static Employee searchEmp_By_Id(String id) {
        for (int i = 0; i < allEmployees.size(); i++) {
            if (allEmployees.get(i).getNationalId().equals(id)) {
                return allEmployees.get(i);
            }
        }
        return null;
    }

    static Employee searchEmp_By_Name(String name) {
        for (int i = 0; i < allEmployees.size(); i++) {
            if (allEmployees.get(i).getName().equals(name)) {
                return allEmployees.get(i);
            }
        }
        return null;
    }

    static void printPayRoll_To_AllEmployees(ArrayList<Employee> allEmps) {
        for (int i = 0; i < allEmps.size(); i++) {
            System.out.println(
                    allEmps.get(i).getName() + " : "
                    + allEmps.get(i).calculateActualSalary());
        }
    }

    static double PayRoll_To_AllEmployees(ArrayList<Employee> allEmps) {
        double sum = 0;
        for (int i = 0; i < allEmps.size(); i++) {
            sum += allEmps.get(i).calculateActualSalary();
        }
        return sum;
    }

    static void printPayRoll_To_Developers(ArrayList<Employee> allEmps) {
        for (int i = 0; i < allEmps.size(); i++) {
            if (allEmps.get(i) instanceof Developer) {
                System.out.println(allEmps.get(i).getName() + " : " + allEmps.get(i).calculateActualSalary());
            }
        }
    }

    static double PayRoll_To_Developers(ArrayList<Employee> allEmps) {
        double sum = 0;
        for (int i = 0; i < allEmps.size(); i++) {
            if (allEmps.get(i) instanceof Developer) {
                sum += allEmps.get(i).calculateActualSalary();
            }
        }
        return sum;
    }

    static void printPayRoll_To_Accountant(ArrayList<Employee> allEmps) {
        for (int i = 0; i < allEmps.size(); i++) {
            if (allEmps.get(i) instanceof Accountant) {
                System.out.println(allEmps.get(i).getName() + " : " + allEmps.get(i).calculateActualSalary());
            }
        }
    }

    static double PayRoll_To_Accountant(ArrayList<Employee> allEmps) {
        double sum = 0;
        for (int i = 0; i < allEmps.size(); i++) {
            if (allEmps.get(i) instanceof Accountant) {
                sum += allEmps.get(i).calculateActualSalary();
            }
        }
        return sum;
    }

    static void addProject() {
        System.out.println("Enter Project Name :");
        String name = input.nextLine();
        System.out.println("Enter Project Location :");
        String location = input.nextLine();
        Project proj = new Project(name, location);
        boolean flag = false;
        while (flag == false) {
            System.out.println("Enter Department relevance name:");
            String deptName = input.nextLine();

            if (searchDeptByName(deptName) != null) {
                Department relevant = searchDeptByName(deptName);
                relevant.addProjectToDept(proj);
                proj.setRelevantProject(relevant);
                allProjects.add(proj);
                flag = true;
            } else {
                System.out.println("Name of department is wrong...Try again!!");
                flag = false;
            }
        }
    }

    static void addDepartment() {
        System.out.println("Enter Department Name : ");
        String departmentName = input.nextLine();
        boolean flag = true;
        while (flag == true) {
            System.out.println("Enter Employee id to be manager at this department : ");
            String name = input.nextLine();
            if (searchEmp_By_Name(name) != null) {
                Employee emp = searchEmp_By_Name(name);
                System.out.println("Enter the appointment of the manager : ");
                long date = input.nextLong();
                Department dept = new Department(departmentName, emp, date);
                allDepartments.add(dept);
            }
            else
            {
                System.out.println("Name of Employee is wrong...Try again!!");
            }

        }
    }

    static Project searchProjByName(String name) {
        for (int i = 0; i < allProjects.size(); i++) {
            if (name.equals(allProjects.get(i).getName())) {
                return allProjects.get(i);
            }
        }
        return null;
    }

    static Department searchDeptByName(String name) {
        for (int i = 0; i < allDepartments.size(); i++) {
            if (name.equals(allDepartments.get(i).getDepartmentName())) {
                return allDepartments.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) {

        dummyEmployees();
        dummyDepartments();
        dummyProjects();
        operationsForHR();
        //printPayRoll(allEmployees);

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
