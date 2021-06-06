package me.erik444.loginv1.eventos;

import me.erik444.loginv1.loginv1;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.bukkit.Bukkit.getLogger;

public class Entrar implements Listener {

    loginv1 plugin;

    public Entrar(loginv1 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void alSalir(PlayerQuitEvent event){
        plugin.reloadUsers();
        FileConfiguration users = plugin.getUsers();
        Player jugador = event.getPlayer();
        Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");
        if(logeado){
            users.set("usuarios."+jugador.getName()+".logeado",false);
            plugin.saveUsers();
        }
    }

    @EventHandler
    public void alEntrar(PlayerJoinEvent event){
        try {
            plugin.reloadUsers();
            Bukkit.getConsoleSender().sendMessage("45");
            FileConfiguration users = plugin.getUsers();
            Player jugador = event.getPlayer();
            String ipP = users.getString("usuarios."+jugador.getName()+".ip");
            getLogger().info(ipP);
            if(ipP == null) {
                jugador.sendMessage(plugin.nombre + ChatColor.RED + "Ingresa una contraseña: (debe ir toda junta, sin espacios)");
            }else{
                jugador.sendMessage(plugin.nombre + ChatColor.RED + "Ingresa tu contraseña:");

            }

            Bukkit.getConsoleSender().sendMessage("57");
            String list = users.getString("usuarios."+jugador.getName()+".pass");
            String ip = users.getString("usuarios."+jugador.getName()+".ip");
            if(ip != null && !ip.isEmpty() && !ip.equals("0.0.0.0")){
                if(jugador.getAddress().getAddress().toString().equals(ip)){
                    users.set("usuarios."+jugador.getName()+".logeado",true);
                    plugin.saveUsers();
                    plugin.reloadUsers();
                    users = plugin.getUsers();
                    if(jugador.getGameMode().equals(GameMode.SURVIVAL)){
                        jugador.setAllowFlight(false);
                    }
                    jugador.sendMessage(ChatColor.RED + "AUTOLOGEADO, NO HACE FALTA QUE PONGAS TU CONTRASEÑA.");
                }else{
                    Bukkit.getConsoleSender().sendMessage(plugin.nombre+  ChatColor.RED +" ALERTA "+ jugador.getName() +" Entró con otra ip: "+ jugador.getAddress().getAddress().toString());
                }
            }
        }catch (Exception ex){
            Bukkit.getConsoleSender().sendMessage(ex.toString());
        }




    }


    @EventHandler
    public void ponerPass(AsyncPlayerChatEvent event){
        try {
            //Bukkit.getConsoleSender().sendMessage("ejecutao");
            FileConfiguration users = plugin.getUsers();
            //Bukkit.getConsoleSender().sendMessage("84");
            Player jugador = event.getPlayer();
            String passchat = event.getMessage();
            //Bukkit.getConsoleSender().sendMessage("87");
            String contraseña = users.getString("usuarios."+jugador.getName()+".pass");
            //Bukkit.getConsoleSender().sendMessage("89");
            Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");
            //Bukkit.getConsoleSender().sendMessage("91");
            if(!logeado){
                    if(contraseña == null){
                        if(jugador.getGameMode().equals(GameMode.SURVIVAL)){
                            jugador.setAllowFlight(false);
                        }
                        event.setCancelled(true);
                        jugador.sendMessage(ChatColor.BLUE + "Logeado Correctamente!");
                        users.set("usuarios."+jugador.getName()+".pass",passchat);
                        users.set("usuarios."+jugador.getName()+".logeado",true);
                        users.set("usuarios."+jugador.getName()+".ip",jugador.getAddress().getAddress().toString());
                        plugin.saveUsers();
                    }else{
                        //Bukkit.getConsoleSender().sendMessage("103");
                        if(passchat.equals(contraseña)){
                            //Bukkit.getConsoleSender().sendMessage("105");
                            if(jugador.getGameMode().equals(GameMode.SURVIVAL)){
                                jugador.setAllowFlight(false);
                            }
                            event.setCancelled(true);
                            jugador.sendMessage(ChatColor.BLUE + "Logeado Correctamente!");
                            users.set("usuarios."+jugador.getName()+".logeado",true);
                            users.set("usuarios."+jugador.getName()+".ip",jugador.getAddress().getAddress().toString());
                            plugin.saveUsers();

                        }else{
                            //Bukkit.getConsoleSender().sendMessage("114");
                            event.setCancelled(true);
                            jugador.sendMessage(ChatColor.RED + "CONTRASEÑA INCORRECTA!");

                        }
                    }

            }else{
                //Bukkit.getConsoleSender().sendMessage("121");
                if(passchat.equalsIgnoreCase(contraseña)){
                    //Bukkit.getConsoleSender().sendMessage("123");
                    event.setCancelled(true);
                    jugador.sendMessage(plugin.nombre+ ChatColor.RED + " Ya estabas logeado, tu contraseña no se mandará al chat.");


                }

            }
        }catch (Exception ex){
            Bukkit.getConsoleSender().sendMessage(ex.toString());
        }



    }






















}
