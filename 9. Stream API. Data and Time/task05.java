//Stream


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

class MyUtils {
    public Stream<String> nameList(Map<String, Stream<String>> map) {
        if(isNull(map)) throw  new NullPointerException();
        return map.values().stream()
                .filter(s->!isNull(s))
                .flatMap(s->s)
                .filter(s -> !isNull(s) && !s.isBlank())
                .map(s -> s.toLowerCase())
                .map(s -> s.replace(" ", ""))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .distinct()
                .sorted();
    }
}
