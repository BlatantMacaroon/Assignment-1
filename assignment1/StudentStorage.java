package assignment1;

import java.io.*;
import java.util.Collection;

public class StudentStorage {
    //the save methods should save a collection of students to a binary file with the supplied name.
    //do not copy paste code between the two versions of save - have one call the other.

    //The load method reads student data from the file and returns it (if you save students and
    //then fetch them, you should get the original data back).

    //The above methods should preserve referential integrity. For example if two students share a course (i.e. the
    //courses are at the same memory address), when loading the students back from a file, they should still reference
    //the same course object, not two separate course objects (double check by comparing the memory locations of the
    //courses on the fetched students in your Part 3 tests).

    static void save(Collection<Student> students, File file) throws IOException {
        //saves a list of students to a binary file and to CSV file, depending on what file format a user chooses
        // (see part 4). Note that the data of referenced objects (address and course) should be saved as well.
        // Read the hints at the end of the document!

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(students);
        oos.close();
        fos.close();
    }

    static void save(Collection<Student> students, String fileName) throws IOException {
        //saves a list of students to a binary file with a given name.
        // no code should be replicated between the two versions of save.
        save(students, new File(fileName));
    }

    static Collection<Student> load(File file) throws IOException, ClassNotFoundException {
        //reads student data from a binary file.
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Collection<Student> students = (Collection)ois.readObject();
        ois.close();
        fis.close();
        return students;
    }
}
