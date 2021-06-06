package me.Erik444.whitelist;

import me.Erik444.whitelist.comandos.whitelistCMD;
import me.Erik444.whitelist.eventos.onJoin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class main extends JavaPlugin {
    //Whitelist file
    public String whitelistLocation;
    private FileConfiguration whitelist = null;
    private File whitelistFile = null;

    //config file

    public PluginDescriptionFile pluginyml = getDescription();
    public String name = ChatColor.BLUE+"["+ ChatColor.YELLOW+ pluginyml.getName() +ChatColor.BLUE+"]"+ChatColor.WHITE;
    public String version =   ChatColor.BLUE+"["+ChatColor.RED+ pluginyml.getVersion() +ChatColor.BLUE+"]"+ChatColor.WHITE;


    @Override
    public void onEnable() {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String command = "/minecraft:whitelist off";
        Bukkit.getServer().dispatchCommand(console, "minecraft:whitelist off");
        Bukkit.getServer().dispatchCommand(console, command);
        Bukkit.dispatchCommand(console,"minecraft:whitelist off");
        getLogger().info(name + " Inicializado correctamente. Version actual: "+ version);
        getLogger().info(name + ChatColor.BLUE+"---------------"+ChatColor.GOLD+" Whitelist "+ChatColor.BLUE+"---------------");
        getLogger().info(name+ "　　　　　WHITELIST ACTIVADA ");
        getLogger().info(name + ChatColor.BLUE+"---------------"+ChatColor.GOLD+" Whitelist Version:"+version+ChatColor.BLUE+"---------------");

        registerWhitelist();
        registrarEventos();
        registrarComandos();
//        List<String> list = whitelist.getStringList("jugadores");
//        String jugadoresEnWhitelist = "";
//
//        jugadoresEnWhitelist = "";
//        for (int i = 0; i < list.size(); i++) {
//            jugadoresEnWhitelist += list.get(i)+",";
//        }
//        getLogger().info("Whitelist:"+jugadoresEnWhitelist);
    }

    @Override
    public void onDisable() {
        getLogger().warning(name + "Desactivado.");
        getLogger().info(name + ChatColor.BLUE+"---------------"+ChatColor.GOLD+" Whitelist "+ChatColor.BLUE+"---------------");
        getLogger().info(name+ "　　　　　WHITELIST DESACTIVADA ");
        getLogger().info(name + ChatColor.BLUE+"---------------"+ChatColor.GOLD+" Whitelist Version:"+version+ChatColor.BLUE+"---------------");

    }

    private void registrarEventos(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new onJoin(this),this);
    }

    private void registrarComandos(){
        this.getCommand("whitelist").setExecutor(new whitelistCMD(this));
    }


    //Whitelist file yml
    public FileConfiguration getWhitelist(){
        if(whitelist == null){
            reloadWhitelist();
        }
        return whitelist;
    }

    public void reloadWhitelist(){
        if(whitelist == null){
            whitelistFile = new File(getDataFolder(),"whitelist.yml");
        }
        whitelist = YamlConfiguration.loadConfiguration(whitelistFile);
        Reader defConfigStream;
        try {
            defConfigStream = new InputStreamReader(this.getResource("whitelist.yml"),"UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                whitelist.setDefaults(defConfig);
            }
        }catch (Exception ex){
            Bukkit.getConsoleSender().sendMessage("ERRROR AL RECARGAR WHITELIST");
            getLogger().info("Error al generar archivo whitelistCMD");
            getLogger().info(ex.toString());
        }
    }

    public void saveWhitelist(){
        try{
            whitelist.save(whitelistFile);
        }catch(IOException e){
            Bukkit.getConsoleSender().sendMessage("ERRROR AL GUARDAR USUARIOS.");
            e.printStackTrace();
        }
    }

    public void registerWhitelist(){
        whitelistFile = new File(this.getDataFolder(),"users.yml");
        if(!whitelistFile.exists()){
            this.getWhitelist().options().copyDefaults(true);
            saveWhitelist();
        }
        getWhitelist();
    }




}
