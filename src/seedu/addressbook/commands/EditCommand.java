package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Adds a person to the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edits the person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX [DETAIL TYPE] [NEW DETAIL]\n\t"
            + "Example: " + COMMAND_WORD
            + " 1 phone 98756433";

    public static final String MESSAGE_SUCCESS = "Person updated: %1$s";
    
    private String detailType;
    private String newDetail;

    public EditCommand(int targetVisibleIndex, String detailType, String newDetail) {
        super(targetVisibleIndex);
        this.detailType = detailType;
        this.newDetail = newDetail;
    }
    

    @Override
    public CommandResult execute() {
    	try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.editPerson(target, detailType, newDetail);
            return new CommandResult(String.format(MESSAGE_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (IllegalValueException e) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
		}
    }

}
