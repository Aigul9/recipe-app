package springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springframework.recipe.commands.NotesCommand;
import springframework.recipe.domain.Notes;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static final String RECIPE_NOTES = "Notes";
    NotesToNotesCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assert notesCommand != null;
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}