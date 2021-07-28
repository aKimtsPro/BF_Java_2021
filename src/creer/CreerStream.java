package creer;

import intro.Personne;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CreerStream {

    public static void main(String[] args) {


        List<Personne> list = new ArrayList<>();

        list.add( new Personne("luc", 165 , 80 , LocalDate.of(1947, 5, 21) ) );
        list.add( new Personne("marie", 167 , 64 , LocalDate.of(1969, 1, 1) ) );
        list.add( new Personne("dominique", 185 , 78 , LocalDate.of(1987, 11, 29) ) );
        list.add( new Personne("michel", 175 , 90 , LocalDate.of(1970, 8, 15)) );

        // Différentes facon de créer un stream

        Stream<Personne> mon_stream = list.stream();

        mon_stream = Stream.empty();

        mon_stream = Stream.of(list.get(0), list.get(1));
        mon_stream = Stream.of(list.get(0));
        mon_stream = Stream.ofNullable(null); // renvoie un stream vide

        mon_stream = Stream.concat(list.stream(), mon_stream);

        mon_stream = Stream.iterate(new Personne("a", 0, 0, null),
                personne -> new Personne(personne.getNom()+"a", personne.getTaille()+1, personne.getPoid()+1, null));
        mon_stream = Stream.iterate(new Personne("a", 0, 0, null),
                personne -> personne.getTaille() < 10,
                personne -> new Personne(personne.getNom()+"a", personne.getTaille()+1, personne.getPoid()+1, null));


        mon_stream = Stream.generate( () -> new Personne("luc", new Random().nextInt(20)+1, 0, null) );

        mon_stream.limit(5)
            .forEach(System.out::println);


        Object[] entier = Stream.generate(() -> new Random().nextInt(20)+1)
                            .limit(100)
                            .toArray();

        for (Object o : entier) {
            System.out.println(o);
        }

    }
}
