package work_07_01_26.homework.task2;

import java.util.ArrayList;
import java.util.List;

public class Database implements Writable, Searchable{
    private List<String> records;

    public Database() {
        records = new ArrayList<>();
        System.out.println("подключение к базе прошло успешно");
    }


    // [123, 777] -> 777
    /*
    777
    1) 123.contains(777)
    2) 777.c(777)
     */
    @Override
    public boolean contains(String keyword) {
        for (String record : records) {
            if(record.contains(keyword)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int countOccurrences(String keyword) {
        int count = 0;
        for (String record : records) {
            record.contains(keyword);
            count++;
        }
        return count;
    }

    @Override
    public void write(String content) {
        records.add(content);
        System.out.println("запись в базу данных " + content);
    }

    @Override
    public void save() {
        System.out.println(records.size() + " база данных сохранена");
    }
}
