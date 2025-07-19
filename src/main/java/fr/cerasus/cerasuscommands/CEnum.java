package fr.cerasus.cerasuscommands;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public enum CEnum {
    ON,
    OFF;

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static CEnum of(String name) {
        return valueOf(name.toUpperCase(Locale.ROOT));
    }

    public static List<String> list() {
        return Arrays.asList(CEnum.values()).stream().map(obj -> obj.toString()).collect(Collectors.toList());
    }
}
