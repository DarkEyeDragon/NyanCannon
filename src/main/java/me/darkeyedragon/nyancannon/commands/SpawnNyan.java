package me.darkeyedragon.nyancannon.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class SpawnNyan extends BaseCommand {

    private static JavaPlugin plugin;

    public SpawnNyan(JavaPlugin plugin) {
        SpawnNyan.plugin = plugin;
    }

    @CommandAlias("nc|nyan|nyancannon|ncannon|nyanc")
    public static void spawnNyan(Player player) {
        Location loc = player.getLocation();
        Vector velLoc = loc.getDirection().add(loc.getDirection());
        loc.add(0, 1, 0);
        Entity entity = player.getWorld().spawnEntity(loc, EntityType.OCELOT);
        entity.setInvulnerable(false);
        entity.setVelocity(velLoc.normalize());

        ParticleBuilder particleBuilder = new ParticleBuilder(Particle.REDSTONE);
        Random random = new Random();
        new BukkitRunnable(){
            int count = 0;
            @Override
            public void run (){
                Vector newLoc = entity.getLocation().getDirection().multiply(0.6);
                entity.setVelocity(newLoc);
                Location particleLoc = entity.getLocation().subtract(entity.getLocation().getDirection().normalize()).add(new Vector(0,0.7,0));
                particleBuilder.count(0);
                particleBuilder.location(particleLoc);
                drawColor(Color.RED, particleBuilder);
                drawColor(Color.ORANGE, particleBuilder);
                drawColor(Color.YELLOW, particleBuilder);
                drawColor(Color.BLUE, particleBuilder);
                drawColor(Color.GREEN, particleBuilder);
                drawColor(Color.PURPLE, particleBuilder);
                if(count++ > 80){

                    particleBuilder.count(0);
                    for (int i = 0; i < 100; i++) {
                        double x = Math.sin(i)*6;
                        double y =  Math.cos(i)*6;
                        double z =  Math.sin(i)*6;
                        particleBuilder.location(particleLoc.clone().add(x-(x/2),y-(y/2),z-(z/2)));
                        particleBuilder.color(Color.fromRGB(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
                        particleBuilder.spawn();
                    }
                    entity.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 1, 1);
    }

    private static void drawColor(Color color, ParticleBuilder particleBuilder){
        particleBuilder.color(color);
        particleBuilder.spawn();
        particleBuilder.location(particleBuilder.location().subtract(0,0.1,0));
    }
}
