import java.util.HashMap;
import java.util.Map;

public class Exm {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a" , 1);
        map.get(1);
        DiscoverHypernym.processLine("<np>Programs</np> which is <np>an arrangement</np>", "program", map);
        System.out.println(map);
    }
}
