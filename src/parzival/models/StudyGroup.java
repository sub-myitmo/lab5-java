package parzival.models;

import parzival.models.validate.Validatable;
import java.util.Date;
/**
 * Класс студенческой группы
 * @author petrovviacheslav
 */
public class StudyGroup implements Comparable<StudyGroup>, Validatable {

    private static int nextId = 1; // id для новой группы

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private Long expelledStudents; //Значение поля должно быть больше 0, Поле не может быть null
    private Integer transferredStudents; //Значение поля должно быть больше 0, Поле не может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле не может быть null

    public StudyGroup(String name, Coordinates coordinates, Date creationDate, Long studentsCount, Long expelledStudents, Integer transferredStudents, Semester semesterEnum, Person groupAdmin) {
        this.id = nextId++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.transferredStudents = transferredStudents;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public StudyGroup(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public Long getExpelledStudents() {
        return expelledStudents;
    }

    public Integer getTransferredStudents() {
        return transferredStudents;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    @Override
    public boolean validate() {
        if (id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (studentsCount != null && studentsCount <= 0) return false;
        if (expelledStudents != null && expelledStudents <= 0) return false;
        if (transferredStudents != null && transferredStudents <= 0) return false;
        if (semesterEnum == null) return false;
        if (groupAdmin == null) return false;

        return coordinates.validate() && groupAdmin.validate();
    }

    public void update(StudyGroup studyGroup) {
        this.name = studyGroup.name;
        this.coordinates = studyGroup.coordinates;
        this.creationDate = studyGroup.creationDate;
        this.studentsCount = studyGroup.studentsCount;
        this.expelledStudents = studyGroup.expelledStudents;
        this.transferredStudents = studyGroup.transferredStudents;
        this.semesterEnum = studyGroup.semesterEnum;
        this.groupAdmin = studyGroup.groupAdmin;
    }

    @Override
    public int compareTo(StudyGroup s) {
        //сравним группы по количеству людей в них
        if (studentsCount == null && s.getStudentsCount() == null) return 0;
        if (studentsCount == null) return -1;
        if (s.getStudentsCount() == null) return 1;

        long delta = studentsCount - s.getStudentsCount();
        if (delta > 0) return 1;
        if (delta == 0) return 0;
        return -1;
    }

    // дописать hashCode equals

    @Override
    public String toString() {
        return "StudyGroup{" +
                "\nid=" + id +
                ", \nname='" + name + '\'' +
                ", \ncoordinates=" + coordinates +
                ", \ncreationDate=" + creationDate +
                ", \nstudentsCount=" + studentsCount +
                ", \nexpelledStudents=" + expelledStudents +
                ", \ntransferredStudents=" + transferredStudents +
                ", \nsemesterEnum=" + semesterEnum +
                ", \ngroupAdmin=" + groupAdmin +
                "\n}";
    }
}