package me.cbitler.raidbot.server_settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

/**
 * Add a role to an event
 * @author Franziska Mueller
 */
public class RoleGroupsAddStep implements RoleGroupsEditStep {

    private String serverId;
	private Set<String> availableRoleGroups;

    public RoleGroupsAddStep(String serverId, Set<String> availableRoleGroups) {
        this.serverId = serverId;
        this.availableRoleGroups = availableRoleGroups;
    }

    /**
     * Handle adding a role to the event
     * @param e The direct message event
     * @return True if a role is added, false otherwise
     */
    public boolean handleDM(PrivateMessageReceivedEvent e) {
        boolean valid = true;
        
        String[] parts = e.getMessage().getRawContent().split(":");
        if(parts.length != 2) 
        {
        	e.getChannel().sendMessage("Invalid input: Make sure it's in the correct format. Did you forget the colon (:)?").queue();
            return false;
        }
        
        String groupName = parts[0].trim();
        if (availableRoleGroups.contains(groupName))
        {
        	e.getChannel().sendMessage("This server already has a role group with this name. Choose a different name or delete the existing group first.").queue();
            return false;
        }
        
        List<String> roleNames = new ArrayList<>();
        
        try {
           	String[] roles = parts[1].trim().split("\\s*,\\s*");
           	roleNames = Arrays.asList(roles);	
            boolean success = ServerSettings.addRoleGroup(serverId, groupName, roleNames);
            if (success == false)
            {
            	e.getChannel().sendMessage("Invalid input: One or more of these discord roles do not exist on this server. Try again.").queue();
                valid = false;
            }
            else
            	e.getChannel().sendMessage("Added group `"+groupName+"` which corresponds to "+roleNames.toString()+".").queue();
        } catch (Exception ex) {
            e.getChannel().sendMessage("Invalid input: Could not parse the list of roles. Make sure it's in the correct format.").queue();
            valid = false;
        }

        return valid;
    }

    /**
     * {@inheritDoc}
     */
    public String getStepText() {
        return "Enter a new role group for the server\n" 
        		+ "__format:__ `[group name]:[role 1],[role 2],...,[role n]`, e.g., `Experts+: Expert Raider, Master Raider`\n"
        		+ "(this means you cannot use colon `:` in your group name!)\n";
    }

    /**
     * {@inheritDoc}
     */
    public RoleGroupsEditStep getNextStep() {
        return new RoleGroupsIdleStep(serverId);
    }

	@Override
	public String getServerID() {
		return serverId;
	}
}
