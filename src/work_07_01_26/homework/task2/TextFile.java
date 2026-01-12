package work_07_01_26.homework.task2;

public class TextFile implements Readable, Writable, Searchable{
    private String filename;
    private String content;
    private boolean isOpen;

    public TextFile(String filename) {
        this.filename = filename;
        this.content = "";
        this.isOpen = false;
    }


    @Override
    public void open() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("файл " + filename + " открыт");
        }
    }

    @Override
    public String readContent() {
        if (!isOpen) {
            throw new RuntimeException("файл должен быть открыт");
        }
        System.out.println(filename + " чтение из файла");
        return content;
    }

    @Override
    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println(filename + " закрыт");
        }
    }

    @Override
    public boolean contains(String keyword) {
        return content.contains(keyword);
    }

    @Override
    public int countOccurrences(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while (content.indexOf(keyword, index) != -1){
            count++;
            index += keyword.length();
        }
        return count;
    }

    @Override
    public void write(String content) {
        if (!isOpen) {
            throw new RuntimeException("сначала открой файл");
        }
        this.content = content;
        System.out.println(filename + " записан новый контент");
    }

    @Override
    public void save() {
        System.out.println(filename + " сохранен");
    }
}
