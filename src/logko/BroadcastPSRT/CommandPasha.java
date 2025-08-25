package logko.BroadcastPSRT;

import java.io.File;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.List;

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
 
    SecureRandom rnd = new SecureRandom();
    
    private String getTextLine() throws Exception
    {
        File File = new File(plugin.getDataFolder(), "textline.psrt");     
        if (!File.exists())
           	throw new Exception("File textline.psrt not found!");
        
        List<String> line = Files.readAllLines(File.toPath());
        if (line.isEmpty()) 
           	throw new Exception("File textline.psrt empty!");
        
		return line.get(rnd.nextInt(line.size())).toString();
    }
    
    private String getBotNickname() throws Exception
    {
    	File File = new File(plugin.getDataFolder(), "BotNickname.psrt");     
        if (!File.exists())
        	throw new Exception("File BotNickname.psrt not found!");
        
        List<String> line = Files.readAllLines(File.toPath());
        if (line.isEmpty()) 
        	throw new Exception("File BotNickname.psrt empty!");
        
		return line.get(rnd.nextInt(line.size())).toString();
    }
	
	private String SayPasha() throws Exception 
	{	
        String str = getTextLine();
        String Name = getBotNickname();
        String endstr = "<BOT " + Name + "> " + str;
 
      	return endstr.toString();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(args.length != 0)
			return false;
		
		try 
		{
			Bukkit.broadcastMessage(SayPasha().toString());
			return true;
		} 
		catch (Exception error) 
		{
			error.printStackTrace();
			plugin.getLogger().warning(error.toString());
			sender.sendMessage("[BroadcastPSRT]: " + error.toString());
	      	return true;
		}
	}

}
