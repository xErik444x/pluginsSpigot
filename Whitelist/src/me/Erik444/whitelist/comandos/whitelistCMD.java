package me.Erik444.whitelist.comandos;

import me.Erik444.whitelist.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.EventListener;
import java.util.List;

public class whitelistCMD implements CommandExecutor {

    private main plugin;

    public whitelistCMD(main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        // \
         String[] comandos = new String[6];
         comandos[0] =plugin.name + ChatColor.BLUE+"---------------"+ChatColor.GOLD+" Whitelist "+ChatColor.BLUE+"---------------";
         comandos[1] = ChatColor.GOLD+ "- /add-añadir-agregar"+ChatColor.RED+" {Nick} "+ChatColor.LIGHT_PURPLE+"-> Añade el usuario a la whitelist.";
         comandos[2] = ChatColor.GOLD+ "- /del-remove-borrar "+ChatColor.RED+" {Nick} "+ChatColor.LIGHT_PURPLE+"-> Elimina al usuario de la whitelist.";
         comandos[4] = ChatColor.GOLD+ "- /list-ver-view ->"+ChatColor.LIGHT_PURPLE+" Muestra la whitelist.";
         comandos[3] = ChatColor.GOLD+ "- /find-buscar ->"+ChatColor.RED+" {Nick} "+ChatColor.LIGHT_PURPLE+" Busca al usuario.";
         comandos[5] =plugin.name + ChatColor.BLUE+"-----"+ChatColor.GREEN+"Version: "+plugin.version+ChatColor.BLUE+" By Erik444_ -----";

        //Comandos
        //add
        //del/remove
        //view
        if( !(commandSender instanceof Player)){
            FileConfiguration users;
                if(args.length>0){
                    switch (args[0].toLowerCase()){
                        case "add":
                        case"añadir":
                        case"agregar":
                            if(args.length > 1){
                                plugin.reloadWhitelist();
                                users = plugin.getWhitelist();
                                if(users.getString("jugadores").contains(args[1])){
                                    plugin.getLogger().info(plugin.name+" El usuario: "+args[1]+", ya estaba agregado.");
                                }else{
                                    List<String> lista = users.getStringList("jugadores");
                                    lista.add(args[1]);
                                    users.set("jugadores",lista);
                                    plugin.saveWhitelist();
                                    plugin.getLogger().info(plugin.name+" "+args[1]+" agregado correctamente.");
                                }
                            }else{
                                plugin.getLogger().warning("Debes especificar un usuario.");

                            }


                            break;
                        case "del":
                        case"delete":
                        case"borrar":
                        case"remove":
                            if(args.length > 1){
                                plugin.reloadWhitelist();
                                users = plugin.getWhitelist();
                                if(!users.getString("jugadores").contains(args[1])){
                                    plugin.getLogger().info(plugin.name+ ChatColor.RED +" El usuario: "+ChatColor.GOLD +args[1]+ChatColor.RED +", no existe en la whitelist.");
                                }else{
                                    List<String> lista = users.getStringList("jugadores");
                                    lista.remove(args[1]);
                                    users.set("jugadores",lista);
                                    plugin.saveWhitelist();
                                    plugin.getLogger().info(plugin.name+ ChatColor.RED +" El usuario: "+ChatColor.GOLD +args[1]+ChatColor.RED +", fue eliminado de la whitelist.");

                                }
                            }else{
                                plugin.getLogger().warning("Debes especificar un usuario.");

                            }


                            break;
                        case "buscar":
                        case "search":
                        case "find":
                            if(args.length > 1){
                                plugin.reloadWhitelist();
                                users = plugin.getWhitelist();
                                if(!users.getString("jugadores").contains(args[1])){
                                    plugin.getLogger().info(plugin.name+ ChatColor.RED +" El usuario: "+ChatColor.GOLD +args[1]+ChatColor.RED +", no existe en la whitelist.");
                                }else{
                                    plugin.getLogger().info(plugin.name+ ChatColor.RED +" El usuario: "+ChatColor.GOLD +args[1]+ChatColor.YELLOW +", está en la whitelist.");

                                }
                            }else{
                                plugin.getLogger().warning("Debes especificar un usuario.");

                            }


                            break;
                        case "view":
                        case "ver":
                        case "list":
                            users = plugin.getWhitelist();
                            List<String> lista = users.getStringList("jugadores");
                            System.out.println(plugin.name + "Jugadores:");
                            System.out.println(lista);
                            break;

                        default:
                            for (int i = 0; i < comandos.length; i++) {
                                System.out.println(plugin.name + comandos[i]);
                            }
                            break;
                    }
                }else{
                    for (int i = 0; i < comandos.length; i++) {
                        System.out.println(comandos[i]);
                    }

                }


            return true;
            }
        else {
                Player p =(Player) commandSender;
                if(!p.hasPermission("whitelist.admin") || !p.isOp()){
                    p.sendMessage(comandos[5]);
                    return true;
                }
                FileConfiguration users;
                if(args.length>0){
                    switch (args[0].toLowerCase()){
                        case "add":
                        case"añadir":
                        case"agregar":
                            if(args.length > 1){
                                plugin.reloadWhitelist();
                                users = plugin.getWhitelist();
                                if(users.getString("jugadores").contains(args[1])){
                                    p.sendMessage(plugin.name+" El usuario: "+args[1]+", ya estaba agregado.");
                                }else{
                                    List<String> lista = users.getStringList("jugadores");
                                    lista.add(args[1]);
                                    users.set("jugadores",lista);
                                    plugin.saveWhitelist();
                                    p.sendMessage(plugin.name+" "+args[1]+" agregado correctamente.");
                                }
                            }else{
                                p.sendMessage(plugin.name+" Debes especificar un usuario.");

                            }


                            break;
                        case "del":
                        case"delete":
                        case"borrar":
                        case"remove":
                            if(args.length > 1){
                                plugin.reloadWhitelist();
                                users = plugin.getWhitelist();
                                if(!users.getString("jugadores").contains(args[1])){
                                    p.sendMessage(plugin.name+ ChatColor.RED +" El usuario: "+ChatColor.GOLD +args[1]+ChatColor.RED +", no existe en la whitelist.");
                                }else{
                                    List<String> lista = users.getStringList("jugadores");
                                    lista.remove(args[1]);
                                    users.set("jugadores",lista);
                                    plugin.saveWhitelist();
                                    p.sendMessage(plugin.name+ ChatColor.RED +" El usuario: "+ChatColor.GOLD +args[1]+ChatColor.RED +", fue eliminado de la whitelist.");

                                }
                            }else{
                                p.sendMessage(plugin.name+ "Debes especificar un usuario.");

                            }


                            break;
                        case "buscar":
                        case"search":
                        case"find":
                            if(args.length > 1){
                                plugin.reloadWhitelist();
                                users = plugin.getWhitelist();
                                List<String> list = plugin.getWhitelist().getStringList("jugadores");
                                    String jugadorEncontrado ="";
                                    for (int i = 0; i < list.size(); i++) {
                                        if(list.get(i).toLowerCase().equals(args[1].toLowerCase())){
                                            jugadorEncontrado = list.get(i);
                                        }
                                    }
                                if(jugadorEncontrado.isEmpty()){
                                    p.sendMessage(plugin.name+ ChatColor.RED +" El usuario: "+ChatColor.GOLD +args[1]+ChatColor.RED +", no existe en la whitelist.");
                                }else{
                                    p.sendMessage(plugin.name+ ChatColor.RED +" El usuario: "+ChatColor.GOLD +jugadorEncontrado+ChatColor.YELLOW +", está en la whitelist.");

                                }
                            }else{
                                p.sendMessage(plugin.name+ "Debes especificar un usuario.");

                            }


                            break;
                        case "view":
                        case "ver":
                        case "list":
                            users = plugin.getWhitelist();
                            List<String> lista = users.getStringList("jugadores");
                            p.sendMessage(plugin.name + "Jugadores:");
                            String mensaje = "";
                            int i2 = 0;
                            for(String message: lista) {
                                mensaje += ChatColor.BLUE+", "+ChatColor.WHITE +message;
                                i2++;

                            }
                            p.sendMessage(mensaje);
                            break;

                        default:
                            for (int i = 0; i < comandos.length; i++) {
                                p.sendMessage( comandos[i]);
                            }
                            break;
                    }
                }else{
                    for (int i = 0; i < comandos.length; i++) {
                        p.sendMessage( comandos[i]);
                    }

                }



            }


        return false;
    }
}
