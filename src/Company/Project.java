package Company;

import java.util.ArrayList;

class Project {

    private String name;
    private String location;
    Department relevantProject; 
    ArrayList<Employee> employees;

    public Project() {
        name = "";
        location = "";
        relevantProject=new Department();
        employees=new ArrayList<>();
    }

    public Project(String name, String location) {
        this.name = name;
        this.location = location;
        relevantProject=new Department();
        employees=new ArrayList<>();
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
    void addEmpToProj(Employee emp)
    {
        employees.add(emp);
    }
    
    
}
