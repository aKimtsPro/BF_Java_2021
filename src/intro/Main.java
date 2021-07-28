package intro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Personne> list = new ArrayList<>();

        list.add( new Personne("luc", 165 , 80 , LocalDate.of(1947, 5, 21) ) );
        list.add( new Personne("marie", 167 , 64 , LocalDate.of(1969, 1, 1) ) );
        list.add( new Personne("dominique", 185 , 78 , LocalDate.of(1987, 11, 29) ) );
        list.add( new Personne("michel", 175 , 90 , LocalDate.of(1970, 8, 15)) );


        // Je souhaite récupérer les poid divisé par 2 de tous les personnes nés après 1960
        // Ne garder que les résultats pairs

        // Version sans Stream
        List<Integer> rslt = new ArrayList<>();

        for (Personne personne : list) {
            if(personne.getDateNaiss().getYear() >= 1960 )
                rslt.add(personne.getPoid()/2);
        }

        List<Integer> trueRslt = new ArrayList<>();

        for (Integer integer : rslt) {
            if( integer % 2 == 0 )
                trueRslt.add(integer);
        }

        trueRslt.forEach(System.out::println);

        // Version avec Stream
        trueRslt = list.stream()
                .filter( (element) -> {
                    System.out.println("filter 1 : " + element.getNom());
                    return element.getDateNaiss().getYear() >= 1960;
                } )
                .map( (element) -> {

                    System.out.println("map      : " + element.getNom());
                    return element.getPoid() / 2;
                } )
                .filter((element) -> {
                    System.out.println("filter 2 : " + element);
                    return element % 2 == 0;
                })
                .limit(1)
                .collect( Collectors.toList() );

        list.forEach(System.out::println);
        trueRslt.forEach(System.out::println);


        //Je souhaite récupérer les 3 premiers nombres pairs au carré

        System.out.println("--------------- Voyons ce qu'il se passe -----------------");

        List<Integer> nbr = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> troisPremierPairCarre = nbr.stream()
                .filter((element) -> {
                    System.out.println("filter :" + element);
                    return element%2 == 0;
                } )
                .map((element) -> {
                    System.out.println("map    :" + element);
                    return element * element;
                })
                .limit(3)
                .collect(Collectors.toList());

        troisPremierPairCarre.forEach(System.out::println);



    }

}
