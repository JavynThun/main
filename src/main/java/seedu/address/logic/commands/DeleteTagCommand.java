package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.*;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.Model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.*;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_PERSON;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class DeleteTagCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "deletetag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes tag\n"
            + "Parameters: valid tag name used\n"
            + "Example: " + COMMAND_WORD + " family";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted Tag: %1$s";
    private final Tag toDelete;

    public DeleteTagCommand(Tag tag) { toDelete = tag; }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException, DuplicatePersonException, PersonNotFoundException {
        requireNonNull(toDelete);
        model.deleteTag(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS) + toDelete);
    }


}
