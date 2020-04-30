package assignment1;

import java.time.LocalDate;
import java.util.Objects;

public class Student implements Cloneable {
    private String firstName;
    private String surname;
    private String id;
    private LocalDate dob;
    private Course course;
    private Address address;

    public Student(String firstName, String surname, String id, LocalDate dob, Course course, Address address) {
        this.firstName = firstName;
        this.surname = surname;
        this.id = id;
        this.dob = dob;
        this.course = course;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(id, student.id) &&
                Objects.equals(dob, student.dob) &&
                Objects.equals(course, student.course) &&
                Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname, id, dob, course, address);
    }

    public Student clone() throws CloneNotSupportedException { // need to check that address is deepcopy and course shallow
        Student cloned = (Student)super.clone();
        cloned.setAddress(new Address(address.getTown(), address.getStreet(), address.getPostCode(), address.getHouseNumber()));
        return cloned;
    }
}
