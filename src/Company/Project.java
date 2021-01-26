package Company;
import Employee.Employee;
import static Company.MainClass.allProjects;
import static Company.MainClass.input;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Project {

    private String name;
    private String location;
    Department relevantProject;
    ArrayList<Employee> employees;

    public Project() {
        name = "";
        location = "";
        relevantProject = new Department();
        employees = new ArrayList<>();
    }

    public Project(String name, String location) {
        this.name = name;
        this.location = location;
        relevantProject = new Department();
        employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Department getRelevantProject() {
        return relevantProject;
    }

    public void setRelevantProject(Department relevantProject) {
        this.relevantProject = relevantProject;
    }

    void addEmpToProj(Employee emp) {
        employees.add(emp);
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

            if (Department.searchDeptByName(deptName) != null) {
                Department relevant = Department.searchDeptByName(deptName);
                relevant.addProjectToDept(proj);
                proj.setRelevantProject(relevant);
                allProjects.add(proj);
                flag = true;
                try {
                    FileWriter myWriter = new FileWriter("Projects.txt");
                    myWriter.write(proj.getName()+ " " + proj.getLocation()+ " " + proj.getRelevantProject());
                    myWriter.close();

                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Name of department is wrong...Try again!!");
                flag = false;
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
}
