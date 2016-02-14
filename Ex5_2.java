import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

class Employee implements Serializable
{
    int EID;
    String name, dept;
    double salary;
    
    Employee() { }
    
    Employee(int ID, String n, String d, double s)
    {
        this.EID = ID;
        this.name = n;
        this.dept = d;
        this.salary = s;
    }
    
    Employee getDetails()
    {
        Scanner s = new Scanner(System.in);
        
        System.out.print("Enter employee ID : ");
        this.EID = s.nextInt();
        s.nextLine();									// to consume newline char left @nextInt()
        System.out.print("Enter employee name : ");
        this.name = s.nextLine();
        System.out.print("Enter employee department : ");
        this.dept = s.nextLine();
        System.out.print("Enter employee salary : ");
        this.salary = s.nextDouble();
        
        return this;
    }
}

class Order implements Serializable
{
    int orderNo, itemNo, quantity;
    double price;
    HashMap<Integer, Double> h = new HashMap<Integer, Double>();
    
    Order()
    {
        this.orderNo = 0;
        this.itemNo = 0;
        this.quantity = 0;
        this.price = 0.0;
    }
    
    Order(int o, int i, int q)
    {
        h.put(101, 4.59);
        h.put(103, 29.95);
        h.put(107, 36.50);
        h.put(125, 49.99);
        
        this.orderNo = o;
        this.itemNo = i;
        this.quantity = q;
        this.price = q * h.get(i);
    }
    
    void display()
    {
        System.out.println("Order Number : " + this.orderNo);
        System.out.println("Item Number : " + this.itemNo);
        System.out.println("Quantity : " + this.quantity);
        System.out.println("Total Price : " + this.price);
    }
    
    void getItemNo(int i)
    {
        this.itemNo = i;
    }
    
    void getOrderNo(int o)
    {
        this.orderNo = o;
    }
    
    void getQuantity(int q)
    {
        this.quantity = q;
    }
    
    void getPrice(float p)
    {
        this.price = p;
    }
    
    Order getDetails()
    {
        Scanner s = new Scanner(System.in);
            
        System.out.print("Enter item number : ");
        this.itemNo = s.nextInt();
        System.out.print("Enter order number : ");
        this.orderNo = s.nextInt();
        System.out.print("Enter quantity : ");
        this.quantity = s.nextInt();
        
        // this.price = this.quantity * h.get(this.itemNo);
        
        return this;
    }
}

public class Ex5_2 implements Serializable
{
    public static void main(String args[]) throws Exception
    {
        // Writing Employee class details to file Data.txt
        
        Employee[] e = new Employee[5];
        FileOutputStream f1 = new FileOutputStream("Data.txt");
        ObjectOutputStream out1 = new ObjectOutputStream(f1);
        
        for(int i = 0 ; i < 5 ; i++)
        {
            e[i] = new Employee();
            e[i] = e[i].getDetails();
            out1.writeObject(e[i]);
        }
        out1.close();
        f1.close();
        
        // Writing Order class details to file Mail_Orders.txt
        
        Order[] o = new Order[5];
        FileOutputStream f2 = new FileOutputStream("Mail_Orders.txt");
        ObjectOutputStream out2 = new ObjectOutputStream(f2);
        
        for(int i = 0 ; i < 5 ; i++)
        {
            o[i] = new Order();
            o[i] = o[i].getDetails();
            out2.writeObject(o[i]);
        }
        out2.close();
        f2.close();
        
        // Reading Order class details from file Mail_Orders.txt
        
        FileInputStream fin = new FileInputStream("Mail_Orders.txt");
        ObjectInputStream in = new ObjectInputStream(fin);
        Order[] readData = new Order[5];
        
        for(int i = 0 ; i < 5 ; i++)
        {
            readData[i] = (Order) in.readObject();
        }
        
        for(int i = 0 ; i < 5 ; i++)
        {
            readData[i].display();
        }
    }
}
