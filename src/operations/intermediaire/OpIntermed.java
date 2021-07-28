package operations.intermediaire;

import intro.Personne;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OpIntermed {

    public static void main(String[] args) {

        List<Personne> list = new ArrayList<>();

        list.add( new Personne("luc", 165 , 80 , LocalDate.of(1947, 5, 21) ) );
        list.add( new Personne("marie", 167 , 64 , LocalDate.of(1969, 1, 1) ) );
        list.add( new Personne("dominique", 185 , 78 , LocalDate.of(1987, 11, 29) ) );
        list.add( new Personne("michel", 175 , 90 , LocalDate.of(1970, 8, 15)) );

        // Les opérations intermédiaires
        List<Object> rslt = new ArrayList<>();

        // filter
        System.out.println("\n-------- filter --------\n");
        rslt = list.stream()
                .filter( (personne) -> personne.getNom().charAt(0) == 'm' )
                .collect(Collectors.toList());

        rslt.forEach(System.out::println);

        // map
        System.out.println("\n--------   map   --------\n");
        rslt = list.stream()
                .map( (personne) -> new Personne(personne.getNom()+"as",
                        personne.getTaille(),
                        personne.getPoid(),
                        personne.getDateNaiss())
                )
                .collect(Collectors.toList());

        rslt.forEach(System.out::println);

        //flatMap
        System.out.println("\n-------- flatMap --------\n");

        // Je souhaite récupérer toutes les lettres présentes dans les noms des personnes
        rslt = list.stream()
                .flatMap( personne -> Stream.of(personne.getNom().toCharArray())) // Character
                .collect(Collectors.toList());

//        List<Integer> entier = Arrays.asList(1,3,5);
//        rslt = entier.stream()
//                .flatMap((nbr) -> Stream.of(nbr, nbr+1))
//                .collect(Collectors.toList());
//        System.out.println(rslt);

        // Je considère mes personnes comme des cellules, à partir de 80kilo elle se coupe en 2 mais
        // sous 70kilo, elles meurent

        rslt = list.stream()
                .flatMap(p -> {
                    if(p.getPoid() <= 70)
                        return Stream.empty();

                    if( p.getPoid() < 80)
                        return Stream.of(p);

                    return Stream.of(
                            new Personne(
                                    p.getNom().substring(0, p.getNom().length()/2),
                                    p.getTaille()/2,
                                    p.getPoid()/2,
                                    p.getDateNaiss()),
                            new Personne(
                                    p.getNom().substring(p.getNom().length()/2),
                                    p.getTaille()/2,
                                    p.getPoid()/2,
                                    p.getDateNaiss())
                            );
                })
                .collect(Collectors.toList());

        rslt.forEach(System.out::println);

        // distinct
        // Je souhaite obtenir les différentes lettres parmi les premières lettres de prénoms

        System.out.println("\n-------- distinct --------\n");

        rslt = list.stream()
                .distinct()
                .collect(Collectors.toList());

        rslt.forEach(System.out::println);

        System.out.println( list.get(1).equals(list.get(3)));


        // limit - peut-etre utilisée pour court-circuiter un stream infini
        System.out.println("\n------ limit ------\n");
        Stream.iterate(0, (n) -> n)
                .limit(20)
                .forEach(System.out::print);

        // skip
        System.out.println("\n\n------  skip  ------\n");
        Stream.iterate(0, n -> n+1)
                .skip(5)
                .limit(5)
                .forEach(System.out::println);

        // sorted - du plus petit au plus grand (on défini ce que signifie "plus grand")

        System.out.println("\n------ sorted ------\n");

        rslt = list.stream()
                .sorted( (p1, p2) -> p1.getPoid() - p2.getPoid() )
                .collect(Collectors.toList());
        rslt.forEach(System.out::println);


        rslt = list.stream()
                .sorted( Comparator.comparingInt(Personne::getPoid) )
                .collect(Collectors.toList());
        rslt.forEach(System.out::println);


        rslt = list.stream()
                .sorted() // seulement si les elements sont comparable
                .collect(Collectors.toList());
        rslt.forEach(System.out::println);

        // peek

        System.out.println("\n------  peek  ------\n");
        rslt = list.stream()
                .peek(p -> System.out.println(p.getNom()))
                .map(Personne::getDateNaiss)
                .limit(3)
                .collect(Collectors.toList());
        afficherRslt(rslt);

    }

    public static void afficherRslt(List<Object> rslt){
        rslt.forEach(System.out::println);
    }
}
