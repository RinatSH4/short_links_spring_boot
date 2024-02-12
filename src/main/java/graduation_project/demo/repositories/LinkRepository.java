package graduation_project.demo.repositories;

import graduation_project.demo.models.ShortLinks;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends CrudRepository<ShortLinks, Long> {
    ShortLinks findByShortLink(String shortLink);
}
