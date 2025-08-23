package logko.BroadcastPSRT;

import org.bukkit.plugin.java.JavaPlugin;

public class SugMain extends JavaPlugin 
{
	@Override
    public void onEnable()
    {
    	getLogger().info("Plugin BroadcastPSRT Enable!");	
    	
    	if (getCommand("pasha") == null)
    		getLogger().info("Unknow command pasha!");
  
    	getCommand("pasha").setExecutor(new CommandPasha(this));
    }
    
    @Override
    public void onDisable() 
    {
    	getLogger().info("Plugin BroadcastPSRT Disable!");
    }
	
}
