import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class SoftHashMap {
    private Map<String, SoftReference<String>> softHashMap = new HashMap<>();
    private final String path = "./";

    public String get(String key) {
        return softHashMap.containsKey(key) ? softHashMap.get(key).get() : put(key);
    }

    public String put(String key) {
        softHashMap.put(key, new SoftReference<>(readValue(key)));
        return softHashMap.get(key).get();
    }

    private String readValue(String fileName) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path + fileName))) {
            bufferedReader.lines().forEach(s -> sb.append(s).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SoftHashMap softHashMap = new SoftHashMap();
        System.out.println(softHashMap.get("404.txt"));
        System.out.println(softHashMap.put("log.txt"));
        System.out.println(softHashMap.get("log.txt"));
    }
}
