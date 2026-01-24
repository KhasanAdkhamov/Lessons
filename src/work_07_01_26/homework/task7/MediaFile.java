package work_07_01_26.homework.task7;

public class MediaFile {
    protected String fileName;
    protected long sizeInBytes;
    protected int duration;

    public MediaFile(String fileName, long sizeInBytes, int duration) {
        this.fileName = fileName;
        this.sizeInBytes = sizeInBytes;
        this.duration = duration;
    }

    public void play() {
        System.out.println("проигрывается " + fileName);

    }

    public void pause() {
        System.out.println("пауза " + fileName);
    }

    public void stop() {
        System.out.println("стоп " + fileName);
    }

    public void getInfo() {
        System.out.printf("название медиа файла %s, размер в байтах %,d, продолжительность в секундах %d",
                fileName, sizeInBytes, duration);
    }

    public int getSizeInMB() {
        return (int) (sizeInBytes / 100000);
    }
}
