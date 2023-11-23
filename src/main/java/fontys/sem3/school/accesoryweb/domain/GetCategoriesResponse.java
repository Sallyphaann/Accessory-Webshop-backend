package fontys.sem3.school.accesoryweb.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetCategoriesResponse
{
    private List<Category> categories;
}
