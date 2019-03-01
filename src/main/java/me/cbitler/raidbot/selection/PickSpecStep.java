package me.cbitler.raidbot.selection;

import me.cbitler.raidbot.raids.Raid;
import me.cbitler.raidbot.utility.ClassesSpecs;
import me.cbitler.raidbot.utility.Reactions;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

/**
 * Step for a user picking a specialization (or core class)
 */
public class PickSpecStep implements SelectionStep {
    Raid raid;
    String coreClass;
    boolean isFlexRole;
    SelectionStep nextStep;
    String[] allSpecs;

    /**
     * Create a new step for this flex role selection with the specified raid and spec
     * that the user chose
     * @param raid The raid
     * @param spec The specialization that the user chose
     */
    public PickSpecStep(Raid raid, String coreclass, boolean isflex) {
        this.raid = raid;
        this.coreClass = coreclass;
        this.isFlexRole = isflex;
        this.nextStep = null;
    	this.allSpecs = ClassesSpecs.getSpecsForCore(coreClass);
    }

    /**
     * Handle the user input - checks to see if the spec they are picking is valid
     * and if so, moving on to next step
     * @param e The private message event
     * @return True if the user chose a valid specialization, false otherwise
     */
    @Override
    public boolean handleDM(PrivateMessageReceivedEvent e) {
    	try {
    		int specId = Integer.parseInt(e.getMessage().getRawContent()) - 1;
    		String spec = allSpecs[specId];
    		if (raid.getRoles().size() == 1) { // if there is only one role, skip PickRoleStep
    			PickRoleStep autoRoleStep = new PickRoleStep(raid, spec, isFlexRole);
    			if (autoRoleStep.pickRole(e.getAuthor().getId(), e.getAuthor().getName(), raid.getRoles().get(0).getName())) {
    			    e.getChannel().sendMessage("Added to event roster" + (isFlexRole ? " as flex role" : "") + ".").queue();
    		    } else {
    		        e.getChannel().sendMessage("Please choose a valid role" + (isFlexRole ? "" : " that is not full") + ".").queue();
    			}
    		} else {
    			nextStep = new PickRoleStep(raid, spec, isFlexRole);
    		}
    		return true;
    	} catch (Exception exp) {
            e.getChannel().sendMessage("Please choose a valid specialization.").queue();
            return false;
    	}
    }

    /**
     * Get the next step - null here as this is a one-step process
     * @return null
     */
    @Override
    public SelectionStep getNextStep() {
        return this.nextStep;
    }

    /**
     * The step text changes the text based on the available roles.
     * @return The step text
     */
    @Override
    public String getStepText() {
    	String text = "Pick a specialization:\n";
        for (int i = 0; i < allSpecs.length; i++) {
        	Emote specEmote = Reactions.getEmoteByName(allSpecs[i]);
        	if (specEmote != null) {
        		text += "`" + (i+1) + "` <:" + specEmote.getName() + ":" + specEmote.getId() + "> " + allSpecs[i] + "\n";
        	} else {
        		text += "`" + (i+1) + "` " + allSpecs[i] + "\n";
        	}
        }
        text += "or type cancel to cancel role selection.";

        return text;
    }
}
