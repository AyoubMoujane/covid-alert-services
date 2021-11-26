package covidnews.service;

import covidnews.models.News;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class NewsService {

    private ArrayList<News> preparedNews = new ArrayList<>(Arrays.asList(
            new News("26/11/2021", "Le nouveau variant Omicron", "contenu de l'article"),
            new News("26/11/2021", "Le nouveau variant Omicron", "contenu de l'article"),
            new News("26/11/2021", "Le nouveau variant Omicron", "contenu de l'article"),
            new News("26/11/2021", "Le nouveau variant Omicron", "contenu de l'article"),
            new News("26/11/2021", "Le nouveau variant Omicron", "contenu de l'article"),
            new News("26/11/2021", "Le nouveau variant Omicron", "contenu de l'article")
    ));

    public ArrayList<News> getNews() {
        return this.preparedNews;
    }
}
