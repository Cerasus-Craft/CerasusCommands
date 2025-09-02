package fr.cerasus.cerasuscommands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CallbackInfo {
    public CommandSender sender;
    public Player player = null;
    public World world = null;
    public Command command;
    public String label;
    public String[] args;

    public CallbackInfo(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        if (sender instanceof Player) {
            player = (Player) sender;
            world = player.getWorld();
        }

        this.command = command;
        this.label = label;
        this.args = args;
    }

    public String getArg(int index) {
        if (args.length <= index) return null;
        return args[index];
    }

    public Integer getIntArg(int index) {
        if (args.length <= index) return 0;

        Integer value;
        try {
            value = Integer.parseInt(args[index]);
        } catch (NumberFormatException e) {
            value = 0;
        }

        return value;
    }
}
