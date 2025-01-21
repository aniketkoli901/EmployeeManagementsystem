package HardProgram;

public class Developer extends Employee 
{
	   public Developer(int id, String name, double salary) {
	        super(id, name, salary);
	    }

	    @Override
	    public void display() {
	        System.out.print("Developer - ");
	        super.display();
	    }
}
