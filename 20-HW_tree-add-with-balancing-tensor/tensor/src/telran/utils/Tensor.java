package telran.utils;

import java.util.function.Consumer;

public class Tensor<T> {
	private Tensor<T>[] array; // array of tensor objects on one dimension less
	private T value; // either array or value should be null.
	// For scalar array - null; for vector - value is null
	/*
	 * dimensions - array with dimension values {3, 4, 10} - dimensions for
	 * three-dimensional array 3- two dimensional arrays 4-arrays, 10 - values in
	 * each array.
	 */

	public Tensor(int[] dimensions, T value) {
		Tensor<T> tensor = createTensor(dimensions, 0, value);
		array = tensor.array;
		this.value = tensor.value;
	}

	private Tensor(Tensor<T>[] array, T value) {
		this.array = array;
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	private Tensor<T> createTensor(int[] dimensions, int currentDimension, T value) {
		if (currentDimension == dimensions.length) {
			return new Tensor<T>((Tensor<T>[]) null, value);
		}
		Tensor<T>[] array = new Tensor[dimensions[currentDimension]];
		for (int i = 0; i < array.length; i++) {
			array[i] = createTensor(dimensions, currentDimension + 1, value);
		}
		return new Tensor<T>(array, null);

	}

	/**
	 * Passes over all Tensor's elements and calls consumer.accept for scalars
	 * 
	 * @param consumer
	 */
	public void forEach(Consumer<T> consumer) {
		if (array == null) {
			consumer.accept(value);
			return;
		}
		for (Tensor<T> tensor : array) {
			tensor.forEach(consumer);
		}
	}

	/**
	 * set value to scalar according to indexes
	 * 
	 * @param indexes
	 * @param value
	 */
	public void setValue(int[] indexes, T value) {
		Tensor<T> tensorScalar = getTensorScalar(indexes);
		if (tensorScalar.array == null) {
			tensorScalar.value = value;
		}
		
	}

	/**
	 * get value of a scalar according to indexes
	 * 
	 * @param indexes
	 * @return
	 */
	public T getValue(int[] indexes) {
		Tensor<T> tensorScalar = getTensorScalar(indexes);
		return tensorScalar.value;
	}

	/**
	 * fill the given regular array
	 * 
	 * @param array
	 */
	private int index = 0;
	public void flatMap(T[] array) {
		index = 0;
		forEach(n -> array[index++] = n);
	}

	private Tensor<T> getTensorScalar(int[] indexes) {

		Tensor<T> res = array[indexes[0]];
		for (int i = 1; i < indexes.length; i++) {
			res = res.array[indexes[i]];
		}

		return res;

	}
}
