import collection_exception.NoElementOverThisIndexInCollection;
import data_structure.*;
import practicle_examples.DataInputCalculatorBuilder;
import practicle_examples.DataInputValidation;
import search.Student;
public class Application {

    public static void main(String[] args) {

//        CustomDoubleLinkedList<Student> cdll = new CustomDoubleLinkedList<Student>();
//        cdll.addNodeToTheFront(new Student("Mihail", 28));
//        cdll.addNodeToTheFront(new Student("Todor", 38));
//        cdll.addNodeToTheFront(new Student("Georgi", 48));

        //System.out.print((Student)(cdll.getHead().data));
        // System.out.println(cdll.getHead().data.); // String: NodeElement
        // Student plainStudent = cdll.getHead().data;

        //System.out.println(cdll.getHead()); // String : Student
        //Student element = (Student) (cdll.getHead().data);
        //System.out.print(element.name);

        //Object student = new Student("Test", 25);

        //element.
        //element.age;

        // Object element = (Student)(cdll.getHead().data);
        // Student element = (Student) (cdll.getHead().data);
        // element.age / eleemnt.name
        // System.out.println(((Student)(cdll.getHead().data)));
        // System.out.println(((Student)cdll.getHead().data).);

        // Object dataStudent  = new Student("Mihail", 10);

//        CustomDoubleLinkedList<Student>.LinkedListNode<Student> first = null;
//        first = cdll.getHead(); // starting point
//        while(first != null) {
//            // peek ();
//            // last = cdll.getHead();
//
//            System.out.println(first.data.name);
//            first = first.next;
//        }
//
//        CustomDoubleLinkedList<Student>.LinkedListNode<Student> last = null;


        // DataInputValidation validationService = new DataInputValidation();
        DataInputCalculatorBuilder postfix = new DataInputCalculatorBuilder("5+6+7");
        System.out.println(postfix.parse());

        Student s = new Student("Mihail", 28);
        System.out.println(s.toString());


        // DataInputCalculatorBuilder postfixPriritty = new DataInputCalculatorBuilder("5 + 8 / 2");
    }
}
