package job.category;

import factory.CategoryFactory;

public class DeleteCategory {

    public DeleteCategory(Long id) {
        CategoryFactory.getInstance().removeCategory(id);
    }
}
