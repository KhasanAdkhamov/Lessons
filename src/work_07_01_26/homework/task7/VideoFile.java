package work_07_01_26.homework.task7;

public class VideoFile extends MediaFile{

    private int width;
    private int height;
    private int fps;
    private String codec;


    public VideoFile(String fileName, long sizeInBytes, int duration,
                     int width, int height) {
        super(fileName, sizeInBytes, duration);
        this.width = width;
        this.height = height;
        this.fps = 24;
        this.codec = "HEVC";
    }

    @Override
    public void play() {
        super.play();
        System.out.println("Воспроизведение видео");
    }

    public int getResolution() {
        return width * height;
    }

    public void toggleSubtitles() {
        System.out.println("вкл/выкл субтитров");
    }
}
