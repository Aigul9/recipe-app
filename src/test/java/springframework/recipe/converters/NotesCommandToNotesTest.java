package springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springframework.recipe.commands.NotesCommand;
import springframework.recipe.domain.Notes;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    public static final String RECIPE_NOTES = "Notes";
    NotesCommandToNotes converter;

    @BeforeEach
    public void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}