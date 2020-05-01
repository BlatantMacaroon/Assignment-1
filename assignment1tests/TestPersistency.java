package assignment1tests;

import assignment1.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestPersistency {

    ArrayList<Student> students, students2;
    Student student;
    static String fileName = "testFile";

    ArrayList<Student> roundTrip (ArrayList<Student> st) {
        try {
            StudentStorage.save(st, fileName);
            return (ArrayList)StudentStorage.load(new File(fileName));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeEach
    void setup() {
        students = new ArrayList<>();
        student = new Student("Mickey", "Mouse", "012345",
                LocalDate.of(1928, 11, 18),
                new Course("0101", "David Beckham Studies"),
                new Address("Burbank", "South Buena Vista Street", "91521", 500));
    }

    @Test
    void testRoundTripNone(){
        students2 = roundTrip(students);
        assertEquals(students2.size(), 0);
    }

    @Test
    void testRoundTripOne(){
        students.add(student);
        students2 = roundTrip(students);
        assertEquals(students, students2);
    }

    @Test
    void testRoundTripMany(){
        for (int i = 0; i < 10; i++) {
            students.add(new Student("fN"+i, "s"+i, "000"+i, LocalDate.of(2019, 1, i+1),
                    new Course("00"+i, "cN"+i),
                    new Address("t"+i, "s"+i, "000"+i, i)));
        }
        students2 = roundTrip(students);
        assertEquals(students, students2);
    }

    @Test
    void testEqualsPersists() throws CloneNotSupportedException {
        students.add(student);
        students.add(student.clone());
        students2 = roundTrip(students);
        assertEquals(students, students2);
    }

    @Test
    void testNotSamePersists() throws CloneNotSupportedException {
        students.add(student);
        students.add(student.clone());
        students2 = roundTrip(students);
        assertNotSame(students2.get(0), students2.get(1));
    }

    @Test
    void testSameClassPersists() throws CloneNotSupportedException {
        students.add(student);
        students.add(student.clone());
        students2 = roundTrip(students);
        assertSame(students2.get(0).getClass(), students2.get(1).getClass());
    }

    @Test
    void testShallow() throws CloneNotSupportedException {
        students.add(student);
        students.add(student.clone());
        students2 = roundTrip(students);
        assertNotSame(students2.get(0).getAddress(), students2.get(1).getAddress());
    }

    @Test
    void testDeep() throws CloneNotSupportedException {
        students.add(student);
        students.add(student.clone());
        students2 = roundTrip(students);
        assertSame(students2.get(0).getCourse(), students2.get(1).getCourse());
    }

    @AfterEach
    void teardown () {
        students = null;
        students2 = null;
        student = null;
    }

    @AfterAll
    public static void cleanup() {
        new File(fileName).delete();
    }

}
