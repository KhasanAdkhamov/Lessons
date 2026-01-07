package lessonTest.samples.person;

public class Person {
    private String name;
    private int birthYear;
    private int birthMonth;
    private int birthDay;

    public Person(String name, int birthYear, int birthMonth, int birthDay) {
        setName(name);
        setBirthYear(birthYear);
        setBirthMonth(birthMonth);
        setBirthDay(birthDay);
    }

    public boolean isMinor() {
        if (getAge() < 18) return true;
        return false;
    }

    public boolean isAdult() {
        if (getAge() >= 18) return true;
        return false;
    }

    public String getBirthDate() {
        return String.format("%02d.%02d.%04d", getBirthDay(), getBirthMonth(), getBirthYear());
    }

    public int getAge() {
        return 2026 - birthYear;
    }

    public void setName(String name) {
        if (name.isEmpty() || name == null) {
            throw new NullPointerException("name can not be empty");
        }
        this.name = name;
    }

    public void setBirthYear(int birthYear) {
        if (birthYear < 1900 || birthYear > 2026) {
            throw new RuntimeException("from 1900 to 2026");
        }
        this.birthYear = birthYear;
    }

    public void setBirthMonth(int birthMonth) {
        if (birthMonth > 12) {
            throw new RuntimeException("1-12");
        }
        this.birthMonth = birthMonth;
    }

    public void setBirthDay(int birthDay) {
        if (birthDay > 31) {
            throw new RuntimeException("1-31");
        }
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

}
