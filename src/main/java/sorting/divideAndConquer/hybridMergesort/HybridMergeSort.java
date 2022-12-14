package sorting.divideAndConquer.hybridMergesort;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes: - Ter
 * contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 * que essa informação possa ser capturada pelo teste. - A cada chamado do
 * método de sort(T[] array) esses contadores são resetados. E a cada chamada
 * interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 * INSERTIONSORT_APPLICATIONS são incrementados. - O InsertionSort utilizado no
 * algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort algorithm
	 * will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array == null) return;
		if (array.length == 0) return;
		
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		if (array.length > SIZE_LIMIT) {
			mergeSort(array, leftIndex, rightIndex);
			MERGESORT_APPLICATIONS += 1;
		} else {
			insertionSort(array, leftIndex, rightIndex);
			INSERTIONSORT_APPLICATIONS += 1;
		}
	}

	public void mergeSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {

			int middle = leftIndex + (rightIndex - leftIndex) / 2;

			mergeSort(array, leftIndex, middle);
			mergeSort(array, middle + 1, rightIndex);

			merge(array, leftIndex, rightIndex, middle);
		}
	}

	public void merge(T[] array, int leftIndex, int rightIndex, int middle) {

		T[] helper = Arrays.copyOf(array, array.length);
		for (int i = leftIndex; i <= rightIndex; i++) {
			helper[i] = array[i];
		}

		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;

		while (i <= middle && j <= rightIndex) {

			if (helper[i].compareTo(helper[j]) <= 0) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
			k++;

		}

		while (i <= middle) {
			array[k] = helper[i];
			i++;
			k++;
		}

		while (j <= rightIndex) {
			array[k] = helper[j];
			j++;
			k++;
		}
	}

	private void insertionSort(T[] array, int leftIndex, int rightIndex) {
		if (array.length == 0) return;
		if (leftIndex >= rightIndex)
			return;

		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			T current = array[i];
			int j = i - 1;
			while (j >= leftIndex && array[j].compareTo(current) > 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = current;
		}
	}

}
