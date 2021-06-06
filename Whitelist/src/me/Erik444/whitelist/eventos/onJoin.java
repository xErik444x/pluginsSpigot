package me.Erik444.whitelist.eventos;

import me.Erik444.whitelist.main;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.UUID;

public class onJoin implements Listener {

    main plugin;

    public onJoin(main plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority= EventPriority.LOWEST)
    public void onPlayerConnect(AsyncPlayerPreLoginEvent e){
        plugin.reloadWhitelist();
        FileConfiguration users = plugin.getWhitelist();
        if(!users.getString("jugadores").contains(e.getName())){
            String reason = plugin.name+ ChatColor.RED+" "+ e.getName()+" No estás en la whitelist.";
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL,reason);
        }
    }
    @EventHandler(priority= EventPriority.LOWEST)
    public void onPlayerDisconnect(PlayerQuitEvent event){
        if(event.getPlayer().isBanned()){
            FileConfiguration users = plugin.getWhitelist();
            Player e = (Player) event.getPlayer();
            List<String> lista = users.getStringList("jugadores");
            lista.remove(e.getName());
            users.set("jugadores",lista);
            plugin.saveWhitelist();
            plugin.getLogger().info(e.getName()+" Eliminado de la whitelist.");
            plugin.reloadWhitelist();
        }

    }

}
