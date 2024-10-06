package org.example.author;

public class Author {

    private Integer id;
    private String firstName;
    private String lastNAme;

    public Author() {

    }

    public Author( String firstName, String lastNAme) {
        this.firstName = firstName;
        this.lastNAme = lastNAme;
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
        return lastNAme;
    }

    public void setLastNAme(String lastNAme) {
        this.lastNAme = lastNAme;
    }

    @Override
    public String toString() {
        return "Author{" +
                ", ID: '" + id + '\'' +
                ", FIRST NAME: '" + firstName + '\'' +
                ", LAST NAME: '" + lastNAme + '\'' +
                '}';
    }

}
