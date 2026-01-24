package work_07_01_26.homework.task7;

public class MediaTest {
    public static void main(String[] args) {
        MediaFile mediaFile = new AudioFile("Audio", 123L, 100, "Timati", "Moscow");
        MediaFile mediaFile2 = new ImageFile("Image", 1L, 1, 20, 10);
        MediaFile mediaFile3 = new VideoFile("Video", 505L, 500, 100, 80);
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.loadMedia(mediaFile2);
        mediaPlayer.loadMedia(mediaFile3);mediaPlayer.loadMedia(mediaFile);
        mediaPlayer.playNext();
        mediaPlayer.playNext();
        mediaPlayer.playNext();
        System.out.println(mediaPlayer.getPlayListDuration());
        mediaPlayer.playAll();
    }
}
