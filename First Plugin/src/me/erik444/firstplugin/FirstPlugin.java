package me.erik444.firstplugin;

import me.erik444.firstplugin.commands.ComandoPrincipal;
import me.erik444.firstplugin.commands.ComandoYoutube;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class FirstPlugin extends JavaPlugin {
    PluginDescriptionFile plDesc = getDescription();
    public String plVersion = plDesc.getVersion();
    public String plName = ChatColor.BLUE+"{"+ ChatColor.WHITE+plDesc.getName()+ChatColor.BLUE+"}";

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW +plName + " ha sido activado(version: "+plVersion+")");
        registerCommands();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW +plName + " ha sido desactivado(version: "+plVersion+")");
    }
    
    public void registerCommands(){
        this.getCommand("youtube").setExecutor(new ComandoYoutube(this));
        this.getCommand("miplugin").setExecutor(new ComandoPrincipal(this));
    }
}
