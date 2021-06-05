package me.erik444.loginv1.eventos;

import me.erik444.loginv1.loginv1;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;

public class CheckSiEstaLogeado implements Listener {

    loginv1 plugin;

    public CheckSiEstaLogeado(loginv1 plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void caminar(PlayerMoveEvent event){
        FileConfiguration users = plugin.getUsers();
        Player jugador = event.getPlayer();
        Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");

        if(!logeado){
            jugador.setAllowFlight(true);
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void dormir(PlayerBedEnterEvent event){
        FileConfiguration users = plugin.getUsers();
        Player jugador = event.getPlayer();
        Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");

        if(!logeado){
            event.setCancelled(true);

        }
    }
    @EventHandler
    public void comandos(PlayerCommandPreprocessEvent event){
        FileConfiguration users = plugin.getUsers();
        Player jugador = event.getPlayer();
        Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");

        if(!logeado){
            event.setCancelled(true);

        }
    }
    @EventHandler
    public void interactuar(PlayerInteractEvent event){
        FileConfiguration users = plugin.getUsers();
        Player jugador = event.getPlayer();
        Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");

        if(!logeado){
            event.setCancelled(true);

        }
    }
    @EventHandler
    public void inventoryopen(InventoryOpenEvent event){
        FileConfiguration users = plugin.getUsers();
        Player jugador = (Player) event.getPlayer();
        Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");

        if(!logeado){
            event.setCancelled(true);

        }
    }

    @EventHandler
    public void inventoryclick(InventoryClickEvent event){
        FileConfiguration users = plugin.getUsers();
        Player jugador = (Player) event.getWhoClicked();
        Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");
        if(!logeado){
            event.setCancelled(true);

        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent  event){
        FileConfiguration users = plugin.getUsers();
        Player jugador = event.getPlayer();
        Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");
        if(!logeado){
            event.setCancelled(true);

        }
    }
    @EventHandler
    public void pick(EntityPickupItemEvent event){
        FileConfiguration users = plugin.getUsers();
        if(event.getEntity() instanceof Player){
            Player jugador = (Player) event.getEntity();
            Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");
            if(!logeado){
                event.setCancelled(true);

            }
        }
    }

    @EventHandler
    public void inventory1(EntityDamageEvent event){
        FileConfiguration users = plugin.getUsers();
        if(event.getEntity() instanceof Player){
            Player jugador = (Player) event.getEntity();
            Boolean logeado = users.getBoolean("usuarios."+jugador.getName()+".logeado");
            if(!logeado){
                event.setCancelled(true);

            }
        }
    }
}
