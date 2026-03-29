package GENERICS.classWork.hw.task8;

public class ExampleRepos {
    public static void main(String[] args) {
        MapRepos<String, Integer> mapRepos = new MapRepos<>();
        mapRepos.save("anton", 111);
        mapRepos.save("lera", 222);
        mapRepos.findAll();
        System.out.println(mapRepos.findById("anton"));
        mapRepos.delete("anton");
        mapRepos.findAll();
    }
}
