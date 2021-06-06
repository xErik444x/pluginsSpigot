package me.erik444.loginv1;

import com.mysql.fabric.xmlrpc.base.Array;
import me.erik444.loginv1.comandos.login;
import me.erik444.loginv1.eventos.CheckSiEstaLogeado;
import me.erik444.loginv1.eventos.Entrar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        reloadUsers();
//        restartUsersSesions();
    }
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(nombre + "ha sido desactivado. ");
        reloadUsers();
        List<String> list = new ArrayList<>(users.getConfigurationSection("usuarios").getKeys(false));
        for (int i = 0; i < list.size() ; i++) {
            // list.get(i) es el nombre de usuario
            // System.out.println(list.get(i)+": "+users.get("usuarios."+list.get(i)+".ip"));
            users.set("usuarios."+list.get(i)+".ip","0.0.0.0");
           // System.out.println("ip establecida a: 0.0.0.0");
        }
        saveUsers();
        getLogger().info(nombre+" Ip de usuarios reestablecidas a 0.0.0.0");
        //        Get names of yml
//        List<String> list = new ArrayList<>(users.getConfigurationSection("usuarios").getKeys(false));
//        for (int i = 0; i < list.size() ; i++) {
//            System.out.println(list.get(i)+": "+users.get("usuarios."+list.get(i)));
//            System.out.println(list.get(i)+": "+users.get("usuarios."+list.get(i)+".ip"));
//            users.set("usuarios."+list.get(i),"0.0.0.0");
//        }
    }

    private void registrarComandos(){
        this.getCommand("login").setExecutor(new login(this));
    }

    private void registrarEventos(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Entrar(this),this);
        pm.registerEvents(new CheckSiEstaLogeado(this),this);
    }


    //login users
    private List<String> usersLogeados =new ArrayList<>();

    public List<String> getUsersLogeados() {
        if(usersLogeados == null){
            usersLogeados.add("1351312asdfcvzxcq3123");
            System.out.println("Era null");
        }
        return usersLogeados;
    }

    public void addUsersLogeados(String user) {
        this.usersLogeados.add(user);
    }
    public void delUsersLogeados(String user) {
        for (int i = 0; i < this.usersLogeados.size(); i++) {
            if(this.usersLogeados.get(i).equals(user)){
                this.usersLogeados.remove(i);
            }
        }
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
////        Get names of yml


//        users.getConfigurationSection("usuarios").getKeys(true).forEach(s -> {
//            System.out.println(users.getString("usuarios."+s+"ip"));
//        });
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

//    public void restartUsersSesions(){
//        FileConfiguration users1 = this.getUsers();
//        System.out.println(users.getConfigurationSection("usuarios.Erik444_"));



//        System.out.println(usuarios.getClass());
//        List<String> users1 = new ArrayList<String>();
//        users1 = users.getStringList("usuarios");
//        for (int i = 0; i <  ; i++) {
//
//        }
//    }



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
