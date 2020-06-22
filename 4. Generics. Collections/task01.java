import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

class MyUtils {
    public Map<String, List<String>> createNotebook(Map<String, String> phones) {
        Map<String, List<String>> map = new HashMap<>();
        if (phones != null) {
            Set<String> set = new HashSet<>(phones.values());
            set.forEach(i -> {
                List<String> list = phones.entrySet()
                        .stream()
                        .filter(e ->(Objects.nonNull(e.getValue()) && e.getValue().equals(i)) || e.getValue() == i)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                map.put(i, list);
            });
        }
        return map;
    }
}
