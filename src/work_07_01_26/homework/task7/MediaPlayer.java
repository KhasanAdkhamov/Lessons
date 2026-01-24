package work_07_01_26.homework.task7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaPlayer {
    private MediaFile currentMedia;
    private List<MediaFile> playList;

    public MediaPlayer() {
        this.currentMedia = null;
        playList = new ArrayList<>();
    }

    public void loadMedia(MediaFile media) {
        playList.add(media);
        System.out.println("медиа файл добавлен " + media.fileName);
    }

    public void playNext() {
        if (playList.isEmpty()) {
            System.out.println("плейлист пустой");
            return;
        }
        if (currentMedia == null) {
            currentMedia = playList.get(0);
        } else {
            int currentIndex = playList.indexOf(currentMedia);
            int nextIndex = (currentIndex + 1) % playList.size();
            currentMedia = playList.get(nextIndex);
        }
        System.out.println("воспроизведение следующего файла");
        currentMedia.play();
    }

    public void playAll() {
        for (MediaFile mediaFile : playList) {
            mediaFile.play();
        }
    }

    public int getPlayListDuration() {
        int totalDuration = 0;
        for (MediaFile mediaFile : playList) {
            totalDuration += mediaFile.duration;
        }
        return totalDuration;
    }

}
