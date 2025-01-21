package HardProgram;

public class SalesExe extends Employee
{
	public SalesExe(int id, String name, double salary) {
        super(id, name, salary);
    }

    @Override
    public void display() {
        System.out.print("Sales Executive - ");
        super.display();
    }

}
