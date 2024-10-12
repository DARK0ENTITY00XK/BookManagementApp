package org.example.author;

public class Author {

    private Integer id;
    private String firstName;
    private String lastName;

    public Author() {

    }

    public Author( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNAme() {
        return lastName;
    }

    public void setLastName(String lastNAme) {
        this.lastName = lastNAme;
    }

    @Override
    public String toString() {
        return "Author{" +
                ", ID: '" + id + '\'' +
                ", FIRST NAME: '" + firstName + '\'' +
                ", LAST NAME: '" + lastName + '\'' +
                '}';
    }

}
