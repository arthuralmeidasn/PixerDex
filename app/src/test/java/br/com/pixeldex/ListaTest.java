package br.com.pixeldex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import br.com.pixeldex.structures.ListaEncadeada;
import br.com.pixeldex.model.Pixel;

class ListaTest {

    @Test
    void testInsercoesEDimensoes() {
        ListaEncadeada<Pixel> lista = new ListaEncadeada<>();
        assertTrue(lista.isEmpty());

        lista.addLast(new Pixel(1, "A", "C", 10));
        lista.addFirst(new Pixel(2, "B", "C", 10));

        assertEquals(2, lista.size());
        assertFalse(lista.isEmpty());
    }

    @Test
    void testReverse() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.addLast(1);
        lista.addLast(2);
        lista.addLast(3);
        
        lista.reverse();
        
        assertEquals(3, lista.get(0));
        assertEquals(1, lista.get(2));
    }

    @Test
    void testUnique() {
        ListaEncadeada<Pixel> lista = new ListaEncadeada<>();
        
        lista.addLast(new Pixel(100, "Pikachu", "R", 50));
        lista.addLast(new Pixel(200, "Charmander", "C", 20));
        lista.addLast(new Pixel(100, "Pikachu", "R", 50)); 

        assertEquals(3, lista.size());
        
        lista.unique();
        
        assertEquals(2, lista.size()); 
    }

    @Test
    void testMove() {
        ListaEncadeada<String> lista = new ListaEncadeada<>();
        lista.addLast("A");
        lista.addLast("B");
        lista.addLast("C");

        lista.move(0, 2);

        assertEquals("B", lista.get(0));
        assertEquals("A", lista.get(2));
    }
}