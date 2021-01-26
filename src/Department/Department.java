package Department;

import Project.Project;
import Employee.Employee;
import static Company.HR.allDepartments;
import static Company.HR.input;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Department {

    String departmentName;
    Employee departmentManager;
    int numberId;
    static int idGeneretor = 0;
    long startDate;
    private ArrayList<Project> projects;

    public Department() {
        departmentName = "";
        numberId = idGeneretor++;
        startDate = 0;
        projects = new ArrayList<>();
    }

    public Department(String department_name, Employee departmentManager, long startDate) {
        this.departmentName = department_name;
        this.departmentManager = departmentManager;
        this.startDate = startDate;
        numberId = idGeneretor++;
        projects = new ArrayList<>();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Employee getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(Employee departmentManager) {
        this.departmentManager = departmentManager;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public int getNumberId() {
        return numberId;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void addProjectToDept(Project p) {
        projects.add(p);
    }

    void viewDepartment() {
        System.out.println("Department name : " + departmentName);
        System.out.println("Department manager : " + departmentManager);
        System.out.println("Department ID : " + numberId);
        System.out.println("Employee become manager in the department at : " + startDate);
        for (int i = 0; i < projects.size(); i++) {
            System.out.println("Project Name :" + projects.get(i).getName());
            System.out.println("Project Location :" + projects.get(i).getLocation());
        }
    }

    static void addDepartment() {
        System.out.println("Enter Department Name : ");
        String departmentName = input.nextLine();
        boolean flag = true;
        while (flag == true) {
            System.out.println("Enter Employee id to be manager at this department : ");
            String name = input.nextLine();

            if (Employee.searchEmp_By_Name(name) != null) {
                Employee emp = Employee.searchEmp_By_Name(name);
                System.out.println("Enter the appointment of the manager : ");
                long date = input.nextLong();
                Department dept = new Department(departmentName, emp, date);
                allDepartments.add(dept);
                flag = false;
                try {
                    FileWriter myWriter = new FileWriter("Departments.txt");
                    myWriter.write(dept.getDepartmentName()+ " " + dept.getDepartmentManager()+ " " + dept.getNumberId()+" "
                            + dept.getStartDate()+" ");
                    for(int i=0;i<dept.getProjects().size();i++)
                    {
                        myWriter.write(dept.getProjects().get(i).getName()+" "+dept.getProjects().get(i).getLocation()+" ");
                    }
                    myWriter.close();

                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Name of Employee is wrong...Try again!!");
                flag = true;
            }

        }
    }

    public static Department searchDeptByName(String name) {
        for (int i = 0; i < allDepartments.size(); i++) {
            if (name.equals(allDepartments.get(i).getDepartmentName())) {
                return allDepartments.get(i);
            }
        }
        return null;
    }
}
