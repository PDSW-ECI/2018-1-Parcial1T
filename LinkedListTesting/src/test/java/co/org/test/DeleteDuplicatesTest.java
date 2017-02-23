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


    @Test
    public void CE2Test() {
        MisteryList list = new MisteryList();
        list.headInsert(20);
        list.headInsert(30);
        list.headInsert(30);
        list.headInsert(10);
        list.deleteDup();

        Iterator<Integer> it=list.elements();
        
        assertEquals("La eliminacio de duplicados en una lista con algunos elementos"
                + "repetidos es inconsistente",10,it.next().intValue());
        assertEquals("La eliminacio de duplicados en una lista con algunos elementos"
                + "repetidos es inconsistente",30,it.next().intValue());
        assertEquals("La eliminacio de duplicados en una lista con algunos elementos"
                + "repetidos es inconsistente",20,it.next().intValue());
        assertEquals("La eliminacio de duplicados en una lista con algunos elementos"
                + "repetidos deja elementos de mas",false, it.hasNext());
        
        
        
    }


    
    
}
