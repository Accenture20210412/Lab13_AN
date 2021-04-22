package hollywood;

import hollywood.model.Actor;
import hollywood.model.DataSearcher;
import hollywood.model.Movie;

import java.util.List;

public class HollywoodMain {
    public static void main(String[] args) {
        List<Actor> actors = Provider.getActors();
        List<Movie> movies = Provider.getMovies();

        DataSearcher dataSearcher = new DataSearcher();

        dataSearcher.getMaleActors(actors);
        dataSearcher.getFemaleActorsByMovieNumber(actors, movies, 1);
        System.out.println("\nMetoda 3: " + dataSearcher.getMovieForDirector(movies, 1, "Nancy Meyers"));
        dataSearcher.listWithJorKFirstLetter(actors);
        dataSearcher.mapWithJorKFirstLetter(actors);


    }
}
