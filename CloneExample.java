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

    public static void main(String[] args) {
        DLL<Car> list = new DLL<Car>();   
        
        //Example 1
	Car honda = new Car("Honda");
        list.addLast(honda);
        list.addLast(new Car("Toyota"));
        list.addLast(new Car("BMW"));

        System.out.println("Example 1: Added Honda, Toyota, BMW using addLast");
        System.out.println("\nExpected:\tnull <-- {Honda} <--> {Toyota} <--> {BMW} --> null");
        System.out.print("Your List:\t");
        System.out.println(list);

        //Example 2
        list.removeLast();
        list.removeLast();

        System.out.println("\nExample 2: Called removeLast two times");
        System.out.println("\nExpected:\tnull <-- {Honda} --> null");
        System.out.print("Your List:\t");
        System.out.println(list);

        //Example 3
        list.addFirst(new Car("Ford"));
	Car kia = new Car("Kia");
        list.addFirst(kia);
        list.addFirst(new Car("Volvo"));

        System.out.println("\nExample 3: Added Ford, Kia, Volvo using addFirst."); 
        System.out.println("\nExpected:\tnull <-- {Volvo} <--> {Kia} <--> {Ford}"
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

	// Example 6: Testing get()
	DLL<Car> listDeepCopy2 = list.deepClone();

	System.out.println("\nExample 6: Using get() to view the second element "
			   + "in the list");
	System.out.println("\nExpected:\t" + "{Ford}");
	System.out.println("Your List:\t" + listDeepCopy2.get(2));

	// Example 7: Testing insert()
	System.out.println("\nExample 7: Inserting \"Lamborghini\" in the middle "
			   + "of the list");
	System.out.println("\nExpected:\t" + "null <-- {Jeep} <--> {Kia} <--> "
			   + "{Lamborghini} <--> {Ford} <--> {Honda} --> null");
	Car lambo = new Car("Lamborghini");
	listDeepCopy2.insert(2, lambo);
	System.out.println("Your List:\t" + listDeepCopy2);

	// Example 8: Testing size()
	System.out.println("\nExample 8: Checking the size of the list");
	System.out.println("\nExpected:\t" + "5");
	System.out.println("Your List:\t" + listDeepCopy2.size());

	// Example 9: Testing isEmpty()
	System.out.println("\nExample 9: Checking to see if the list is empty");
	System.out.println("\nExpected:\t" + "false");
	System.out.println("Your List:\t" + listDeepCopy2.isEmpty());
	
	// Example 10: Testing first() and last()
	System.out.println("\nExample 10: Retrieving the first and last elements "
			   + "in the list");
	System.out.println("\nExpected:\t" + "{Jeep}, {Honda}");
	System.out.println("Your List:\t" + listDeepCopy2.first() + ", "
			   + listDeepCopy2.last());

	// Example 11: Testing removeFirst();
	System.out.println("\nExample 10: Removing the first element from the list");
	System.out.println("\nExpected:\t" + "null <-- {Kia} <--> {Lamborghini} <--> "
			   + "{Ford} <--> {Honda} --> null");
	listDeepCopy2.removeFirst();
	System.out.println("Your List:\t" + listDeepCopy2);

	// Example 12: Changing the second element of the list
	System.out.println("\nExample 12: Changing the second element of the list "
			   + "using the set() method");
	System.out.println("\nExpected:\t" + "null <-- {Kia} <--> {Civic} <--> "
			   + "{Ford} <--> {Honda} --> null");
	Car civic = new Car("Civic");
	listDeepCopy2.set(1, civic);
	System.out.println("Your List:\t" + listDeepCopy2);

	// Example 13: Removing an element from the middle of the list
	System.out.println("\nExample 13: Removing an element from the middle of the "
			   + "list");
	System.out.println("\nExpected:\t" + "null <-- {Kia} <--> {Civic} <--> "
			   + "{Honda} --> null");
	listDeepCopy2.remove(2);
	System.out.println("Your List:\t" + listDeepCopy2);

	// Example 14: Finding an element in the list
	System.out.println("\nExample 14: Finding an element in the list");
	System.out.println("\nExpected:\t" + "{Civic}");
	System.out.println("Your list:\t" + listDeepCopy2.find(civic));
	
	// Example 15: Swapping two elements in the list
	DLL.Node first = listDeepCopy2.find(kia);
	DLL.Node second = listDeepCopy2.find(civic);
	DLL.Node third = listDeepCopy2.find(honda);
	System.out.println("\n" + first);
	System.out.println("\n" + second);
	System.out.println("\n" + third);
	System.out.println("\nExample 15: Swapping the second and third elements in "
			   + "list");
	System.out.println("\nExpected:\t" + "null <-- {Kia} <--> {Honda} <--> "
			   + "{Civic} --> null");
	listDeepCopy2.swap(second, third);
        System.out.println("Your list:\t" + listDeepCopy2);
	
	// Example 16: Clearing the list
	System.out.println("\nExample 14: Clearing all elements from the list");
	listDeepCopy2.clear();
	System.out.println("\nExpected:\t" + "size = 0; list = null");
	System.out.println("Your List:\t" + "size = " + listDeepCopy2.size()
			   + "; list = " + listDeepCopy2);
    }
}
