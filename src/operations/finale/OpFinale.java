package operations.finale;

import intro.Personne;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OpFinale {

    public static void main(String[] args) {

        List<Personne> list = new ArrayList<>();

        list.add( new Personne("luc", 165 , 80 , LocalDate.of(1947, 5, 21) ) );
        list.add( new Personne("marie", 167 , 64 , LocalDate.of(1969, 1, 1) ) );
        list.add( new Personne("dominique", 185 , 78 , LocalDate.of(1987, 11, 29) ) );
        list.add( new Personne("michel", 175 , 90 , LocalDate.of(1970, 8, 15)) );


        // forEach

        list.stream()
                .filter((p) -> p.getTaille() < 180)
                .forEach( System.out::println );

        // toArray

        Object[] tab0 = list.stream()
                .map( Personne::getNom )
                .toArray();

        String[] tab1 = list.stream()
                .map( Personne::getNom )
                .toArray( taille -> new String[taille] );

        String[] tab2 = list.stream()
                .map( Personne::getNom )
                .toArray( String[]::new );

        // reduce

        String concat = list.stream()
                .map(Personne::getNom)
                .reduce((accumulator, toAdd) -> accumulator+'-'+toAdd).get();

        concat = list.stream()
                .map(Personne::getNom)
                .reduce("BASE", (accumulator, toAdd) -> accumulator+'-'+toAdd);

        System.out.println(concat);

        // min/max

        System.out.println("--------- Min/Max ----------");

        Personne minMax = list.stream()
                .min( Personne::compareTo )
                .get();

        System.out.println(minMax);

        minMax = list.stream()
                .max(( p1, p2) -> {

                    int pt1=0, pt2=0;

                    if( p1.getNom().contains("a") )
                        pt1++;
                    if( p2.getNom().contains("a") )
                        pt2++;

                    if( p1.getNom().startsWith("m") )
                        pt1++;
                    if( p2.getNom().startsWith("m") )
                        pt2++;

                    return pt1 - pt2;
                }).get();

        System.out.println(minMax);

        // count

        System.out.println("\n----------  count  ----------\n");

        long size = list.stream()
                .filter((p) -> p.getDateNaiss().getDayOfMonth() % 2 == 0 )
                .count();

        System.out.println("size : " + size);

        // anyMatch / allMatch / noneMatch

        System.out.println("--------- Les MATCH ----------");

        boolean rslt = list.stream()
                .anyMatch( (p) -> p.getDateNaiss().getDayOfMonth() % 2 == 0 );
        System.out.println("anyMatch : " + rslt); // false

        rslt = list.stream()
                .allMatch((p) -> p.getDateNaiss().getDayOfMonth() % 2 == 0 );
        System.out.println("allMatch : " + rslt); // false

        rslt = list.stream()
                .noneMatch((p) -> p.getDateNaiss().getDayOfMonth() % 2 == 0 );
        System.out.println("noneMatch : " + rslt); //true

        // findFirst, findAny

        System.out.println("--------- Les FIND ----------");

        Personne pers = list.stream()
                .findAny().get();
        System.out.println(pers);

        pers = list.stream()
                .findFirst().get();
        System.out.println(pers);

        // Une autre facon de trouver le min
        pers = list.stream()
                .sorted()
                .findFirst().get();
        System.out.println(pers);

        // collect

        Map<String, LocalDate> dates = list.stream()
                .filter((e) -> e.getTaille() != e.getPoid())
                .collect( Collectors.toMap(Personne::getNom, Personne::getDateNaiss) );
    }

}
