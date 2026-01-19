package work_16_01_JAVA_CONCEPTS;

import java.util.Objects;

public class Person {

    String name;
    int age;
    String pasport;

    public Person(String name, int age, String pasport) {
        this.name = name;
        this.age = age;
        this.pasport = pasport;
    }

    @Override
    public String toString() {

        return String.format("Имя: %s, возраст: %d ", name, age);
//        return "Person{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
    }

    @Override
    public boolean equals(Object obj) {

        // Проверка на null
        if (obj == null) return false;

        // проверка на тот же объект
        if(this == obj) return true;

        // проверка типа
        if(getClass() != obj.getClass()) return false;

        // Приведение типа и сравнить поля
        Person other = (Person) obj;
        return this.age ==  other.age
                && Objects.equals(name, other.name)
                && Objects.equals(pasport, other.pasport);

        // Вариант среды
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return age == person.age && Objects.equals(name, person.name) && Objects.equals(pasport, person.pasport);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31*result +(name!=null?name.hashCode():0);
        result = 31*result +(pasport!=null?pasport.hashCode():0);
        result = 31*result +age;

        return result;

//        return Objects.hash(name, age, pasport);
    }
}
