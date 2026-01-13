package work_07_01_26.homework.task5;

public class ThemePlugin implements Plugin{
    private String name;
    private String version;

    public ThemePlugin(String name, String version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return name;
    }

    @Override
    public void initialize() {
        System.out.println("инициализация имени " + name + " версия " + version);
    }

    @Override
    public void execute() {
        if (isCompatible(getVersion())) {
            System.out.println("изменение темы плагином " + name);
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
