package work_07_01_26.homework.task5;

public class PluginTest {
    public static void main(String[] args) {
        PluginManager pluginManager = new PluginManager();
        Plugin autoSavePlugin = new AutoSavePlugin("Save", "111");
        Plugin spell = new SpellCheckPlugin("Spell", "222");
        Plugin themePlugin = new ThemePlugin("Theme", "333");

        pluginManager.registerPlugin(autoSavePlugin);
        pluginManager.registerPlugin(spell);
        pluginManager.registerPlugin(themePlugin);

        pluginManager.initializeAll();
        pluginManager.executeAll();
        pluginManager.listPlugins();
    }
}
