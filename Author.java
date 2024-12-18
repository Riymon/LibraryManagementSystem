import java.util.Date;

public class Author {
    private String name;
    private String nationality;
    private Date birthdate;

    public Author(String name, String nationality, Date birthdate) {
        this.name = name;
        this.nationality = nationality;
        this.birthdate = birthdate;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
