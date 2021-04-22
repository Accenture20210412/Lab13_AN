import hollywood.Provider;
import hollywood.model.Actor;
import hollywood.model.DataSearcher;
import hollywood.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DataSearcherTest {

    DataSearcher dataSearcher = new DataSearcher();
    List<Actor> actors = Provider.getActors();
    List<Movie> movies;

    @BeforeEach
    void init() {
        actors = Provider.getActors();
        movies = Provider.getMovies();
    }

    @Test
    public void checkThereAreNoFemales() {

    }
}
