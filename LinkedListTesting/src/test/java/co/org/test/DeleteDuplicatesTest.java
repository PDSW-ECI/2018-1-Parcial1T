/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.test;

import co.org2.acme.software.linkedlisttesting.MisteryList;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import org.quicktheories.core.Gen;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

/**
 *
 * Clases de equivalencia, metodo removeDup
 *
 * CF1: Lista [E1]. TIPO: Frontera. Resultado esperado: Lista [E1]
 * CE1: Lista [E1,E2,E3,...,E(N)], donde E1!=E2!=E3...!=E(N). TIPO: Normal. Resultado esperado: Lista [E1,E2,E3,...,E(N)]
 * CE2: Lista [E1,E2,E2,[E3]], donde la sublista E3 no contiene a E2 TIPO: Normal. Resultado esperado: Lista [E1,[E3]]
 *
 */
public class DeleteDuplicatesTest {

    public DeleteDuplicatesTest() {
    }

    @Before
    public void setUp() {
    }

    /*
    @Test
    public void CF1Test() {
        MisteryList list = new MisteryList();
        list.headInsert(20);
        list.deleteDup();

        Iterator<Integer> it=list.elements();

        assertEquals("La eliminacion de duplicados en listas de 1 deja la lista vacia",true,it.hasNext());
        assertEquals("La eliminacion de duplicados de listas con 1 elemento no es consistente.",20,it.next().intValue());
        assertEquals("La eliminacion de duplicados en listas de 1 elemento deja más de 1 elemento ",false,it.hasNext());

    }

    @Test
    public void CE1Test() {
        MisteryList list = new MisteryList();
        list.headInsert(20);
        list.headInsert(30);
        list.headInsert(40);
        list.deleteDup();

        Iterator<Integer> it=list.elements();

        assertEquals("La eliminacio de duplicados en una lista de datos unicos"
                + "es inconsistente",40,it.next().intValue());
        assertEquals("La eliminacio de duplicados en una lista de datos unicos"
                + "es inconsistente",30,it.next().intValue());
        assertEquals("La eliminacio de duplicados en una lista de datos unicos"
                + "es inconsistente",20,it.next().intValue());



    }
    */

    @Test
    public void CE2Test() {
        qt().forAll(misteryLists()
            .describedAs(l -> "List = " + l))
            .check(list -> {
                    List<Integer> original = fromMisteryList(list);
                    list.deleteDup();

                   Iterator<Integer> it=list.elements();

                   /* Property to verify */

                   return false;
                });
    }

    public static List<Integer> fromMisteryList(MisteryList mistery) {
        List<Integer> r = new ArrayList<>();
        for(int x : (Iterable<Integer>)() -> mistery.elements()) {
            r.add(x);
        }
        return r;
    }

    public static Gen<MisteryList> misteryLists() {
        return lists().of(integers().from(-20).upTo(20)).ofSizeBetween(0,100).map((l) -> {
                MisteryList list = new MisteryList();
                for(Integer x : l) {
                    list.headInsert(x);
                }
                return list;
            }); 
    }

    public static Gen<MisteryList> misteryListsBetween(int from, int upTo) {
        return lists().of(integers().from(from).upTo(upTo)).ofSizeBetween(0,100).map((l) -> {
                MisteryList list = new MisteryList();
                for(Integer x : l) {
                    list.headInsert(x);
                }
                return list;
            }); 
    }

}
