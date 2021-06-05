package me.erik444.firstplugin.commands;

import me.erik444.firstplugin.FirstPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoYoutube implements CommandExecutor {

    private FirstPlugin plugin;

    public ComandoYoutube(FirstPlugin plugin){
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(plugin.plName+ ChatColor.BLUE+ " No puedes ejecutar el comando desde la consola.");
        }else{
            Player jugador = (Player) sender;
            jugador.sendMessage("YOUTUBE: ERIK444_");
        }


        return false;
    }
}
