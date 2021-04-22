package hollywood.model;

import hollywood.Provider;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataSearcher {

    public List<Actor> getMaleActors(List<Movie> movies) {
        List<Actor> maleActors = movies.stream()
                .flatMap(movie -> movie.getCast().stream())
                .distinct()
                .filter(actor ->actor.getSex() == Sex.M).collect(Collectors.toList());
        System.out.println("\nMetoda 1");
        displayMaleActors(maleActors);
        return maleActors;
    }

    public List<Actor> getFemaleActorsByMovieNumber(List<Actor> actors, List<Movie> movies, int movieNumber) throws NoSuchElementException {
        Movie findMovie = movies.stream()
                .filter(movie -> movie.getNr() == movieNumber)
                .findFirst()
                .get();
        List<Actor> femaleActorsInMov = findMovie.getCast().stream()
                .filter(actor -> actor.getSex() == Sex.F)
                .collect(Collectors.toList());

        System.out.println("\nMetoda 2");
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

    public List<Movie> getMoviesWithoutWomen(List<Movie> movies){
        List<Movie> listWtWom = movies.stream()
                .filter(movie -> movie.getCast().stream()
                        .noneMatch(actor -> actor.getSex().equals(Sex.F)))
                .collect(Collectors.toList());
        return listWtWom;
    }

    public List<Actor> listWithJorKFirstLetter(List<Actor> actors) {
        List<Actor> lista = actors.stream()
                .filter(actor -> (actor.getName().startsWith("J") || actor.getName().startsWith("K")))
                .sorted(Comparator.comparing(Actor::getSex))
                .collect(Collectors.toList());

        System.out.println("\nMetoda 5");
        System.out.println("Ilość osób których imiona zaczynają się o 'J' lub 'K' to: " + lista.size());
        return lista;
    }

    public Map<String, List<Actor>> mapWithJorKFirstLetter(List<Actor> actors) {

        Map<String, List<Actor>> actorsMap = new HashMap<>();

        List<Actor> males = actors.stream()
                .filter(actor -> (actor.getName().startsWith("J") || actor.getName().startsWith("K")))
                .filter(actor -> actor.getSex() == Sex.M)
                .sorted()
                .collect(Collectors.toList());

        List<Actor> females = actors.stream()
                .filter(actor -> (actor.getName().startsWith("J") || actor.getName().startsWith("K")))
                .filter(actor -> actor.getSex() == Sex.F)
                .sorted()
                .collect(Collectors.toList());
        actorsMap.put("Sex.M", males);
        actorsMap.put("Sex.F", females);

        System.out.println("\nMetoda 6");
        displayMaleActors(males);
        displayFeMaleActors(females);
        return actorsMap;
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
