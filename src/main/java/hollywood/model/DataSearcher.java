package hollywood.model;

import hollywood.Provider;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataSearcher {

    private List<Actor> actors = Provider.getActors();
    private List<Movie> movies = Provider.getMovies();

    public List<Actor> getMaleActors(List<Actor> actors) {
        List<Actor> maleActors = actors.stream().filter(actor ->actor.getSex() == Sex.M).collect(Collectors.toList());
        displayMaleActors(maleActors);
        return maleActors;
    }

    public List<Actor> getFemaleActorsByMovieNumber(List<Actor> actors, List<Movie> movies, int movieNumber) {
        Movie findMovie = movies.stream()
                .filter(movie -> movie.getNr() == movieNumber)
                .findFirst()
                .get();
        List<Actor> femaleActorsInMov = findMovie.getCast().stream()
                .filter(actor -> actor.getSex() == Sex.F)
                .collect(Collectors.toList());

        displayFeMaleActors(femaleActorsInMov);
        return femaleActorsInMov;
    }

    public String getMovieForDirector(List<Movie> movies, int movieNum, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirector() == director)
                .collect(Collectors.toList())
                .get(movieNum)
                .getTitle();
    }

    public List<Actor> listWithJorKFirstLetter(List<Actor> actors) {
        List<Actor> lista = actors.stream()
                .filter(actor -> (actor.getName().startsWith("J") || actor.getName().startsWith("K")))
                .sorted(Comparator.comparing(Actor::getSex))
                .collect(Collectors.toList())
        System.out.println(lista.size());
        return lista;
    }

    private void displayMaleActors(List<Actor> data) {
        data.forEach(System.out::println);
        System.out.println("Liczba męskich aktorów wynosi: " + data.size());
    }

    private void displayFeMaleActors(List<Actor> data) {
        data.stream().sorted().forEach(System.out::println);
        System.out.println("Liczba aktorek wynosi: " + data.size());
    }
}
