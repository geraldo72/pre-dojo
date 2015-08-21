package utils;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Util {

	/**
	 * Realiza a ordenação do mapa com base nos valores, utilizando o comparable do objeto 
	 * @param map Mapa a ser ordenado
	 * @return Set ordenado dos itens do mapa
	 */
	public static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> ordenaMapaPorValor(Map<K, V> map) {
		
		SortedSet<Map.Entry<K, V>> mapaOrdenado = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
			
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				return e1.getValue().compareTo(e2.getValue());
			}
			
		});
		
		mapaOrdenado.addAll(map.entrySet());
		return mapaOrdenado;
	}

}
