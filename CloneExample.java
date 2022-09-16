/**
 * This code should help in testing the clone and deep 
 * clone methods of project 1.
 *
 * Author: Marcus Hill
 */ 
import java.lang.Cloneable;

public class CloneExample
{
   /*
    * This is an example of an object that may be passed
    * to your DLL.
    */
    public static class Car implements Cloneable
    {
        private Manufacturer make; 

        public Car(Manufacturer make)
        {
            this.make = make;
        }

        public Car(String brand)
        {
            this.make = new Manufacturer(brand);
        }

        public Car clone()
        {
            return new Car(new Manufacturer(this.make.getBrand()));
        }
        
        public boolean equals(Car x)
        {
            if(this.make.getBrand() == x.make.getBrand())
            {
                return true;
            }

            return false;
        }

        public Manufacturer getMake()
        {
            return this.make;
        }

        public void setMake(Manufacturer make)
        {
            this.make = make;
        }
        
        public String toString()
        {
            return make.toString();
        }
        
    }

    public static class Manufacturer
    {
        String brand;

        public Manufacturer(String brand)
        {
            this.brand = brand;
        }

        public Manufacturer clone()
        {
            return new Manufacturer(new String(this.brand));
        }
        
        public boolean equals(Manufacturer x)
        {
            return this.brand.equals(x.brand);
        }

        public String getBrand()
        {
            return this.brand;
        }

        public void setBrand(String brand)
        {
            this.brand = brand;
        }
        
        public String toString()
        {
            return "{" + brand + "}";
        }
    }

    public static void main(String[] args)
    {
        DLL<Car> list = new DLL<Car>();   
        
        //Example 1
        list.addLast(new Car("Honda"));
        list.addLast(new Car("Toyota"));
        list.addLast(new Car("BMW"));

        System.out.println("Example 1: Added Honda, Toyota, BMW using addLast");
        System.out.println("Expected:\tnull <-- {Honda} <--> {Toyota} <--> {BMW} --> null");
        System.out.print("Your List:\t");
        System.out.println(list);

        //Example 2
        list.removeLast();
        list.removeLast();

        System.out.println("\nExample 2: Called removeLast two times");
        System.out.println("Expected:\tnull <-- {Honda} --> null");
        System.out.print("Your List:\t");
        System.out.println(list);

        //Example 3
        list.addFirst(new Car("Ford"));
        list.addFirst(new Car("Kia"));
        list.addFirst(new Car("Volvo"));

        System.out.println("\nExample 3: Added Ford, Kia, Volvo using addFirst."); 
        System.out.println("Expected:\tnull <-- {Volvo} <--> {Kia} <--> {Ford}"
                           + " <--> {Honda} --> null");
        System.out.print("Your List:\t");
        System.out.println(list);

        //Example 4
        DLL<Car> listCopy = list.clone();
        listCopy.first().setMake(new Manufacturer("Jeep"));

        System.out.println("\nExample 4: Cloned the list and changed the" 
                           + " Manufactuer of the first car in the copy list.");
        System.out.println("\t   The original list should also be changed.");
        System.out.println("\nExpected Original List:\t" + "null <-- {Jeep} <--> "
                           + "{Kia} <--> {Ford} <--> {Honda} --> null");
        System.out.println("Your original List\t" + list);

        //Example 5
        // This is a deep clone of the original list after
        // the first element was changed to Jeep.
        DLL<Car> listDeepCopy = list.deepClone(); 
        listDeepCopy.first().setMake(new Manufacturer("Audi"));

        System.out.println("\nExample 5: Deep Cloned the original list, then "
                           + "changed the manufacturer of the first"); 
        System.out.println("\t   element of the deep copy list");
        System.out.println("\nExpected Original List:\t" + "null <-- {Jeep} "
                           + "<--> {Kia} <--> {Ford} <--> {Honda} --> null");
        System.out.println("Expected Deep Copy:\t" + "null <-- {Audi} <--> "
                           + "{Kia} <--> {Ford} <--> {Honda} --> null");
        System.out.println("Your Original List:\t" + list);
        System.out.println("Your Deep Copy List:\t" + listDeepCopy);

	// Example 6
	DLL<Car> listDeepCopy2 = list.deepClone();

	System.out.println(listDeepCopy2.get(2));
	listDeepCopy2.insert(2, new Car("Lambo"));
	System.out.println(listDeepCopy2);
	System.out.println(listDeepCopy2.size());

	DLL<Car> empty = new DLL<Car>();
	System.out.println(empty.isEmpty());
	System.out.println(listDeepCopy2.isEmpty());
	System.out.println(listDeepCopy2.first());
	System.out.println(listDeepCopy2.last());
	listDeepCopy2.removeFirst();
	System.out.println(listDeepCopy2);
	System.out.println(listDeepCopy2.size());
	// listDeepCopy2.remove(2);
	// System.out.println(listDeepCopy2);
	// System.out.println(listDeepCopy2.size());
	// System.out.println(listDeepCopy2.find());
	System.out.println("Remove method: " + listDeepCopy2.remove(1));
	System.out.println(listDeepCopy2);
	System.out.println(listDeepCopy2.size());
	listDeepCopy2.clear();
	System.out.println(listDeepCopy2.size());
    }
}
