package logko.BroadcastPSRT;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandPasha implements CommandExecutor
{
	private JavaPlugin plugin;

    public CommandPasha(JavaPlugin plugin) 
    {
        this.plugin = plugin;
    }
	
	private boolean SayPasha() 
	{	
		try 
		{
			Random rnd = new Random();
			
	        File TextFile = new File(plugin.getDataFolder(), "textline.psrt");     
	        if (!TextFile.exists())
	        {
	            Bukkit.getLogger().warning("File textline.psrt not found!");
	            return false;
	        }
	        
            List<String> lines = Files.readAllLines(TextFile.toPath());
            if (lines.isEmpty()) 
            {
                plugin.getLogger().warning("File textline.psrt empty!");
                return false;
            }
            
	        File NameFile = new File(plugin.getDataFolder(), "BotNickname.psrt");     
	        if (!NameFile.exists())
	        {
	            Bukkit.getLogger().warning("File BotNickname.psrt not found!");
	            return false;
	        }
	        
            List<String> linesname = Files.readAllLines(NameFile.toPath());
            if (lines.isEmpty()) 
            {
                plugin.getLogger().warning("File BotNickname.psrt empty!");
                return false;
            }
	        
            int randline = rnd.nextInt(lines.size()); 
            int randNameIndex = rnd.nextInt(linesname.size());
            String str = lines.get(randline);
            String Name = linesname.get(randNameIndex);
            String endstr = "<BOT " + Name + "> " + str;
            
	        Bukkit.broadcastMessage(endstr);
	      	return true;
	    } 
		catch (IOException e) 
		{
			e.printStackTrace();
	      	return false;
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(args.length > 0)
			return false;
		
		if(!SayPasha())
			return false;
		else
			return true;
	}

}
