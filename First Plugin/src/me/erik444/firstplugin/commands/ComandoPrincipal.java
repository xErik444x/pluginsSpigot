package me.erik444.firstplugin.commands;

import me.erik444.firstplugin.FirstPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoPrincipal implements CommandExecutor {

    private FirstPlugin plugin;

    public ComandoPrincipal(FirstPlugin plugin){
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(plugin.plName+ ChatColor.BLUE+ " No puedes ejecutar el comando desde la consola.");
        }else{
            Player jugador = (Player) sender;
            if(args.length >0){
                switch (args[0].toLowerCase()){
                    case "version":
                        jugador.sendMessage(plugin.plName + "version actual: " + plugin.plVersion);
                        break;
                    default:
                        jugador.sendMessage(plugin.plName + ChatColor.RED+"Ese comando no existe :c" );
                        break;
                }
                return true;
            }else{
                jugador.sendMessage(plugin.plName + "Usa /miplugin version para ver la version del plugin");
            }
        }


        return false;
    }

}
