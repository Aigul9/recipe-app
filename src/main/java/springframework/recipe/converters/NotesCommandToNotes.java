package springframework.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import springframework.recipe.commands.NotesCommand;
import springframework.recipe.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes>  {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
