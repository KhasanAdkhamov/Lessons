package work_07_01_26.homework.task7;

public class ImageFile extends MediaFile{

    private int width;
    private int height;
    private String format;

    public ImageFile(String fileName, long sizeInBytes, int duration,
                     int width, int height) {
        super(fileName, sizeInBytes, duration);
        this.width = width;
        this.height = height;
        this.format = "JPG, PNG, etc.";
    }

    @Override
    public void play() {
        super.play();
        System.out.println("Показ изображения");
    }

}
