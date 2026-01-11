package work_07_01_26.homework.task2;

public class ReadOnlyFile implements Readable, Searchable{
    private String filename;
    private String content;
    private boolean isOpen;

    public ReadOnlyFile(String filename) {
        this.filename = filename;
        this.content = "";
        this.isOpen = false;
    }

    @Override
    public void open() {
        if (!isOpen) {
            isOpen = true;
            System.out.println(filename + " файл открыт");
        }
    }

    @Override
    public String readContent() {
        if (!isOpen) {
            throw new RuntimeException(" файл должен быть открыт");
        }
        System.out.println("файл только чтения");
        return content;
    }

    @Override
    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println(filename + " файл закрыт");
        }
    }

    @Override
    public boolean contains(String keyword) {
        return content.contains(keyword);
    }

    @Override
    public int countOccurrences(String keyword) {
        return 0;
    }
}
