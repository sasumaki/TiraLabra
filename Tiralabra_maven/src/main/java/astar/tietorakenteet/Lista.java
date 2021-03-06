/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.tietorakenteet;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *Lista tietorakenne. Lisätään tavaraa ja poistetaan tavaraa
 * @author sasumaki
 * @param <E>
 */
public class Lista<E> implements Iterable<E> {

    private Object[] taulu;
    private int size;

    public Lista() {
        this.size = 0;
        this.taulu = new Object[10];
    }
/**
 * Lisätään taulukkoon tavaraa, jos ei mahdu niin kopioidaan taulukko uuteen tuplasti isompaan taulukkoon
 * @param kama 
 */
    public void add(Object kama) {
        if (size == taulu.length) {
            Object[] taulu2 = new Object[size * 2];
            System.arraycopy(taulu, 0, taulu2, 0, size);
            this.taulu = taulu2;
        }
        
        taulu[size] = kama;
        size = size + 1;
    }
/**
 * palauttaa indeksissä i olevan elementin taulukosta
 * @param i
 * @return 
 */
    public E get(int i) {

        return (E) taulu[i];
    }
/**
 * taulukon koko
 * @return 
 */
    public int size() {
        return size;
    }
/**
 * tarkistaa onko taulukko tyhjä
 * @return 
 */
    public boolean isEmpty() {
        return size == 0;
    }
/**
 * poistaa taulukosta indeksissä i olevan alkion
 * @param i 
 */
    public void remove(int i) {
        int siirrettavat = size - i - 1;
        if (siirrettavat > 0) {
            System.arraycopy(taulu, i + 1, taulu, i, siirrettavat);
        }
        size--;
        taulu[size] = null;
    }
/**
 * Iteraattori, for toimintoa varten luotu
 * @return 
 */
    @Override
    public Iterator<E> iterator() {
        return new iteraattori();
    }

    private class iteraattori implements Iterator<E> {

        private int indeksi;
        private int edellinen;

        @Override
        public boolean hasNext() {
            return indeksi < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) taulu[edellinen = indeksi++];
        }

        @Override
        public void remove() {
            Lista.this.remove(edellinen);
            indeksi = edellinen;
            edellinen = -1;
        }
    }

}
