package GENERICS.classWork.hw;

public class CacheTest {
    public static void main(String[] args) {
        Cache<Integer, String> cache = new Cache<>();
        System.out.println(cache.size());
        cache.put(1, "anton");
        cache.put(2, "Andrey");
        System.out.println(cache.containsKey(2));
        System.out.println(cache.size());
        //cache.clear();
        System.out.println(cache.get(1));

        Pair<Integer, String> pair = new Pair<>(1, "olya");
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
    }
}
