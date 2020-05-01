package assignment1tests;

import assignment1.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.time.LocalDate;

public class TestCloningStudents {

    Student student, student2;

    @BeforeEach
    void setup() {
        student = new Student("Mickey", "Mouse", "012345",
                LocalDate.of(1928, 11, 18),
                new Course("0101", "David Beckham Studies"),
                new Address("Burbank", "South Buena Vista Street", "91521", 500));
        try { student2 = student.clone(); } catch (CloneNotSupportedException ignored) {}
    }

    @Test
    void testEquals() {
        assertEquals(student, student2);
    }

    @Test
    void testNotSame() {
        assertNotSame(student, student2);
    }

    @Test
    void testSameClass() {
        assertSame(student.getClass(), student2.getClass());
    }

    @Test
    void testShallowCourse(){
        assertSame(student.getCourse(), student2.getCourse());

    }

    @Test
    void testDeepAddress(){
        assertNotSame(student.getAddress(), student2.getAddress());
        assertEquals(student.getAddress(), student2.getAddress());
    }

    /*@Test
    void testSubclass (){
        class SubStudent extends Student {
            SubStudent (String firstName, String surname, String id, LocalDate dob, Course course, Address address) {
                super(firstName, surname, id, dob, course, address);
            }
        }
        try { SubStudent sub = (SubStudent)student.clone(); } catch (CloneNotSupportedException e) {System.out.println("gotcha");}
    }*/

    @AfterEach
    void teardown(){
        student = null;
    }

}
