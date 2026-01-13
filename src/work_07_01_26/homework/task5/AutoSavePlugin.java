package work_07_01_26.homework.task5;

public class AutoSavePlugin implements Plugin{
    private String name;
    private String version;

    public AutoSavePlugin(String name, String version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void initialize() {
        System.out.println("инициализация имени " + name + " версия " + version);
    }

    @Override
    public void execute() {
        if (isCompatible(getVersion())) {
            System.out.println("автосохранение плагином" + name);
        }
    }

    @Override
    public void shutdown() {
        System.out.println("выключение " + name);
    }

    @Override
    public boolean isCompatible(String appVersion) {
        return Plugin.super.isCompatible(appVersion);
    }
}
