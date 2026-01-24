package work_07_01_26.homework.task7;

public class AudioFile  extends MediaFile{

    private String artist;
    private String album;
    private int bitrate;

    public AudioFile(String fileName, long sizeInBytes, int duration,
                     String artist, String album) {
        super(fileName, sizeInBytes, duration);
        this.artist = artist;
        this.album = album;
        this.bitrate = 320;
    }

    @Override
    public void play() {
        super.play();
        System.out.println("исполнитель " + artist + " альбом " + album);
    }

    public void adjustVolume(int level) {
        System.out.println("громкость " + level);
    }
}
