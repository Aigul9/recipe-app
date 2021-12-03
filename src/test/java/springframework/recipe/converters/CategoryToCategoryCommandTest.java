package springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springframework.recipe.commands.CategoryCommand;
import springframework.recipe.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final String DESCRIPTION = "description";
    CategoryToCategoryCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        //given
        Category category = new Category();
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assert categoryCommand != null;
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}