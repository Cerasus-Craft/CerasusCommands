package fr.cerasus.cerasuscommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public interface CCommand<T extends Enum<T> & SubCommandEnum> extends CommandExecutor, TabCompleter {
    Class<T> getClazz();

    @Override
    default boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CallbackInfo ci = new CallbackInfo(sender, command, label, Arrays.copyOfRange(args, 1, args.length));
        if (args.length == 0) return emptyCallback(ci);

        String subCommandName = args[0];

        for (T t : values()) {
            if (t.name().equalsIgnoreCase(subCommandName)) {
                t.getCallback().callback(ci);
                return true;
            }
        }

        return true;
    }

    default boolean emptyCallback(CallbackInfo ci) {
        return true;
    }

    default List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        List<String> subCommands = Arrays.asList(values()).stream().map(obj -> obj.toString().toLowerCase(Locale.ROOT)).collect(Collectors.toList());
        return (List<String>) (args.length == 1 ? (List) StringUtil.copyPartialMatches(args[0], subCommands, new ArrayList(subCommands.size())) : new ArrayList<>());
    }

    default T[] values() {
        return getClazz().getEnumConstants();
    }
}
