package Company;
public class Developer extends Employee{
    String frameWork;
    static double overTime=2000;
    
    public Developer(String name,String nationalId,char gender,String frameWork){
        super(name, nationalId, gender);
        this.frameWork=frameWork;
    }
    
    public Developer(String name,String nationalId,char gender){
        super(name, nationalId, gender);
    }
    
    @Override
    public void view(){
        super.viewDetailed();
        System.out.println("frame work : "+frameWork);   
    }
    
    @Override
    public double calculateActualSalary() {

        return super.calculateActualSalary()+overTime;
    }
    
    public void getStatus(){
        System.out.println("i'm in meeting .");
    }

    @Override
    boolean runTask(Task t) {
        System.out.println("Finish Planning");
        System.out.println("Finish Analysis");
        System.out.println("Finish Design");
        System.out.println("Finish Implementation");
        System.out.println("Finish Testing and Integration");
        System.out.println("Finish Deployment and Maintenance");
        return true;
    }
}