package graduation_project.demo.controllers;

import graduation_project.demo.models.ShortLinks;
import graduation_project.demo.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private LinkRepository linkRepository;

    @GetMapping({"/", "/index"})
    public String index(Model model, @RequestParam(name = "error", defaultValue = "", required = false) String error) {
        Iterable<ShortLinks> shortLinks = linkRepository.findAll();
        if (error.equals("shortLink"))
            model.addAttribute("error", "короткая ссылка занята!");
        model.addAttribute("links", shortLinks);
        return "index";
    }

    @PostMapping("/add-link")
    public String add(@RequestParam String link, @RequestParam String shortLink) {
        ShortLinks links = linkRepository.findByShortLink(shortLink);
        if (links != null)
            return "redirect:/?error=shortLink";
        ShortLinks newLink = new ShortLinks(link, shortLink);
        linkRepository.save(newLink);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        ShortLinks link = linkRepository.findById(id).orElseGet(ShortLinks::new);
        linkRepository.delete(link);
        return "redirect:/";
    }

    //и тут я решил реализовать настоящий сократитель ссылок
    //нужно просто залить на сервак и привязать коротенький домен)
    //в html файл добавил строчку:
    // <p th:text="'Сокращенная ссылка: http://localhost:8000/' + ${link.shortLink}"/>
    // потом поменять на свое и все
    @GetMapping("/{shortLink}")
    public String shortLink(@PathVariable(value = "shortLink") String shortLink) {
        ShortLinks link = linkRepository.findByShortLink(shortLink);
        return "redirect:" + link.getLink();
    }

    @GetMapping("/chat")
    public String chat() {
        return "/chat";
    }
}
