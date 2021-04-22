import hollywood.Provider;
import hollywood.model.Actor;
import hollywood.model.DataSearcher;
import hollywood.model.Movie;
import hollywood.model.Sex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataSearcherTest {

    DataSearcher dS = new DataSearcher();
    List<Actor> actors1;
    List<Actor> actors2;
    List<Actor> actors3;
    List<Movie> movies;

    @BeforeEach
    void init() {
        actors1 = List.of(new Actor("actor1", Sex.F), new Actor("Jola", Sex.F), new Actor("actor3", Sex.M));
        actors2 = List.of(new Actor("actor4", Sex.M), new Actor("actor5", Sex.M), new Actor("actor6", Sex.M));
        actors3 = List.of(new Actor("actor7", Sex.M), new Actor("Judyta", Sex.F), new Actor("actor9", Sex.M));
        Movie movie1 = new Movie(1, "movie1", "director1");
        movie1.setCast(actors1);
        Movie movie2 = new Movie(2, "movie2", "director2");
        movie2.setCast(actors2);
        Movie movie3 = new Movie(3, "movie3", "director1");
        movie3.setCast(actors3);
        movies = List.of(movie1, movie2, movie3);
    }

    @Test
    public void shouldReturnNumberOfMaleActors() {
        Assertions.assertEquals(6, dS.getMaleActors(movies).size());
    }

    @Test
    public void shouldReturnNumberOfFemaleActors() {
        Assertions.assertEquals(2, dS.getFemaleActorsByMovieNumber(actors1, movies, 1).size());
    }

    @Test
    public void shouldThrowExceptionWhenMovieNumberIsToHigh() {
        Assertions.assertThrows(NoSuchElementException.class, () -> dS.getFemaleActorsByMovieNumber(actors1, movies, 18));
    }

    @Test
    public void shouldReturnSpecificMovieForDirector() {
        Assertions.assertEquals("movie1", dS.getMovieForDirector(movies, 0, "director1"));
    }

    @Test
    public void shouldThrowExceptionWhenMovieNumIsToHigh() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> dS.getMovieForDirector(movies, 20, "director1"));
    }

    @Test
    public void shouldReturnMoviesWithoutWomen() {
        Assertions.assertEquals(1, dS.getMoviesWithoutWomen(movies).size());
    }

    @Test
    public void shouldReturnNumberOfActorsNamedFromJorK() {
        List<Actor> newList = Stream.of(actors1, actors3)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        Assertions.assertEquals(2, dS.listWithJorKFirstLetter(newList).size());
    }







}
