package parzival.models;

import parzival.managers.CollectionManager;

import java.util.Date;
import java.util.Objects;

/**
 * Класс студенческой группы
 *
 * @author petrovviacheslav
 */
public class StudyGroup implements Comparable<StudyGroup>, Validatable {

    private static long nextId = 1; // id для новой группы

    private final long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private Long expelledStudents; //Значение поля должно быть больше 0, Поле не может быть null
    private Integer transferredStudents; //Значение поля должно быть больше 0, Поле не может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле не может быть null

    /**
     * Конструктор класса StudyGroup
     *
     * @param name                имя
     * @param coordinates         координаты
     * @param creationDate        дата создания (генерируется автомаитчески при add)
     * @param studentsCount       количество студентов
     * @param expelledStudents    количество отчисленных студентов
     * @param transferredStudents количество переведённых студентов
     * @param semesterEnum        номер семестра в качестве enum'а
     * @param groupAdmin          админ группы
     */
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


    /**
     * Обновить поле id для верного создания новых StudyGroups
     *
     * @param collectionManager менеджер коллекции
     */
    public static void updateNextId(CollectionManager collectionManager) {
        if (collectionManager.getSizeCollection() != 0) {
            long maxId = 0;
            for (StudyGroup elem : collectionManager.getStackCollection()){
                maxId = Math.max(elem.getId(), maxId);
            }
            nextId = maxId + 1;
        }
    }

    /**
     * Получить id
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Получить имя
     *
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Получить координаты
     *
     * @return координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Получить дату создания
     *
     * @return дата создания
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Получить количество студентов
     *
     * @return количество студентов
     */
    public Long getStudentsCount() {
        return studentsCount;
    }

    /**
     * Получить количество отчисленных студентов
     *
     * @return количество отчисленных студентов
     */
    public Long getExpelledStudents() {
        return expelledStudents;
    }

    /**
     * Получить количество переведённых студентов
     *
     * @return количество переведённых студентов
     */
    public Integer getTransferredStudents() {
        return transferredStudents;
    }

    /**
     * Получить номер семестра в качестве enum'а
     *
     * @return номер семестра в качестве enum'а
     */
    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    /**
     * Получить админа группы
     *
     * @return админ группы
     */
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

    /**
     * Обновить состояние всех полей группы
     *
     * @param studyGroup группа, поля которой присваиваются к группе this
     */
    public void update(StudyGroup studyGroup) {
        this.name = studyGroup.getName();
        this.coordinates = studyGroup.getCoordinates();
        this.creationDate = studyGroup.getCreationDate();
        this.studentsCount = studyGroup.getStudentsCount();
        this.expelledStudents = studyGroup.getExpelledStudents();
        this.transferredStudents = studyGroup.getTransferredStudents();
        this.semesterEnum = studyGroup.getSemesterEnum();
        this.groupAdmin = studyGroup.getGroupAdmin();
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

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, creationDate, studentsCount, expelledStudents, transferredStudents, semesterEnum, groupAdmin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup studyGroup = (StudyGroup) o;
        return Objects.equals(name, studyGroup.name) && Objects.equals(coordinates, studyGroup.coordinates) && Objects.equals(creationDate, studyGroup.creationDate) && Objects.equals(studentsCount, studyGroup.studentsCount) && Objects.equals(expelledStudents, studyGroup.expelledStudents) && Objects.equals(transferredStudents, studyGroup.transferredStudents) && Objects.equals(semesterEnum, studyGroup.semesterEnum) && Objects.equals(groupAdmin, studyGroup.groupAdmin);
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", expelledStudents=" + expelledStudents +
                ", transferredStudents=" + transferredStudents +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin +
                "}";
    }
}


