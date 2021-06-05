package me.erik444.loginv1;

import me.erik444.loginv1.comandos.login;
import me.erik444.loginv1.eventos.CheckSiEstaLogeado;
import me.erik444.loginv1.eventos.Entrar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class loginv1 extends JavaPlugin {
    public String rutaConfig;

    private FileConfiguration users = null;
    private File usersFile = null;



    PluginDescriptionFile plDesc = getDescription();
    public String nombre = ChatColor.BLUE + "["+ ChatColor.GOLD +plDesc.getName()+ChatColor.BLUE+"]"+ChatColor.WHITE;
    public String version = ChatColor.RED +  plDesc.getVersion();



    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(nombre + "ha sido activado. version actual: "+ version);
        registrarComandos();
        registrarEventos();
        //registrarConfig();
        registerUsers();

    }
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(nombre + "ha sido desactivado. ");
    }

    private void registrarComandos(){
        this.getCommand("login").setExecutor(new login(this));
    }

    private void registrarEventos(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Entrar(this),this);
        pm.registerEvents(new CheckSiEstaLogeado(this),this);
    }

    public void registrarConfig(){
        File config = new File(this.getDataFolder(),"users.yml");
        rutaConfig = config.getPath();
        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }







    public FileConfiguration getUsers(){
        if(users == null){
            reloadUsers();
        }
        return users;
    }

    public void reloadUsers(){
        if(users == null){
            usersFile = new File(getDataFolder(),"users.yml");
        }
        users = YamlConfiguration.loadConfiguration(usersFile);
        Reader defConfigStream;
        try {
            defConfigStream = new InputStreamReader(this.getResource("users.yml"),"UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                users.setDefaults(defConfig);
            }
        }catch (Exception ex){
            Bukkit.getConsoleSender().sendMessage("ERRROR AL RECARGAR USUARIOS.");
            getLogger().info("Error al generar archivo");
            getLogger().info(ex.toString());
        }
    }

    public void saveUsers(){
        try{
            users.save(usersFile);
        }catch(IOException e){
            Bukkit.getConsoleSender().sendMessage("ERRROR AL GUARDAR USUARIOS.");
            e.printStackTrace();
        }
    }

    public void registerUsers(){
        usersFile = new File(this.getDataFolder(),"users.yml");
        if(!usersFile.exists()){
            this.getUsers().options().copyDefaults(true);
            saveUsers();
        }
    }




/*
    private FileConfiguration data = null;
    private File dataFile = null;
    public void reloadData(){
        if(dataFile == null){
            dataFile = new File(getDataFolder(),"users.yml");
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
        InputStream defStream = this.getResource("users.yml");
        
        if(defStream !=null){
            YamlConfiguration defUsuarios = YamlConfiguration.loadConfiguration(dataFile);
            data.setDefaults(defUsuarios);
        }
    }
*/

















}
