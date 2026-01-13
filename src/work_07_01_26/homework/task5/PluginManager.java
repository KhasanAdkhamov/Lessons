package work_07_01_26.homework.task5;

import java.util.*;

public class PluginManager {
    private ArrayList<Plugin> plugins;

    public PluginManager() {
        this.plugins = new ArrayList<>();
    }

    public void registerPlugin(Plugin plugin) {
        plugins.add(plugin);
        System.out.println(plugin + " регистрация плагина");
    }

    public void initializeAll() {
        for (Plugin plugin : plugins) {
            plugin.initialize();
            System.out.println(plugin);
        }
    }

    public void executeAll() {
        for (Plugin plugin : plugins) {
            plugin.execute();
            System.out.println(plugin);
        }
    }

    public void shutdownAll() {
        for (Plugin plugin : plugins) {
            plugin.shutdown();
            System.out.println(plugin);
        }
    }

    //public Plugin findPlugin(String name);

    public List<Plugin> listPlugins() {
        List<Plugin> list = new ArrayList<>();
        for (Plugin plugin : plugins) {
            list.add(plugin);
        }
        return list;
    }
}
