package br.com.pixeldex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import br.com.pixeldex.structures.BST;
import br.com.pixeldex.model.Pixel;

class BSTTest {

    @Test
    void testInsertAndSearch() {
        BST<Pixel> bst = new BST<>();
        Pixel p1 = new Pixel(1, "Bulbasaur", "C", 10);
        Pixel p2 = new Pixel(2, "Charmander", "C", 10);

        bst.insert(p1);
        bst.insert(p2);

        Pixel busca = new Pixel(1, "Bulbasaur", "", 0); 
        assertNotNull(bst.search(busca));
        
        Pixel naoExiste = new Pixel(0, "Mewtwo", "", 0);
        assertNull(bst.search(naoExiste));
    }

    @Test
    void testRemoveFolha() {
        BST<Integer> bst = new BST<>();
        bst.insert(50);
        bst.insert(30); 
        bst.remove(30);
        assertNull(bst.search(30));
    }

    @Test
    void testRemoveNoComDoisFilhos() {
        BST<Integer> bst = new BST<>();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40); 
        bst.remove(30);
        
        assertNull(bst.search(30)); 
        assertNotNull(bst.search(20)); 
        assertNotNull(bst.search(40));
    }

    @Test
    void testHeight() {
        BST<Integer> bst = new BST<>();
        assertEquals(-1, bst.height());

        bst.insert(50); 
        assertEquals(0, bst.height());

        bst.insert(30); 
        assertEquals(1, bst.height());
        
        bst.insert(20); 
        assertEquals(2, bst.height());
    }
}