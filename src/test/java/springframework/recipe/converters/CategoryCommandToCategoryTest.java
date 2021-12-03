package springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springframework.recipe.commands.CategoryCommand;
import springframework.recipe.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assert category != null;
        assertEquals(DESCRIPTION, category.getDescription());
    }
}