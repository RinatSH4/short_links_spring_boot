package graduation_project.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//для создания геттеров сеттеров дабы укоротить код я использовал библиотеку lobmok
@Entity
@Getter
@Setter
public class ShortLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String link;

    private String shortLink;

    public ShortLinks() {
    }

    public ShortLinks(String link, String shortLink) {
        this.link = link;
        this.shortLink = shortLink;
    }
}
