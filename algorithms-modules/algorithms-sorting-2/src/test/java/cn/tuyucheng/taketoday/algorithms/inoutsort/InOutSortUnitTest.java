package cn.tuyucheng.taketoday.algorithms.inoutsort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class InOutSortUnitTest {

	@Test
	public void givenArray_whenInPlaceSort_thenReversed() {
		int[] input = {1, 2, 3, 4, 5, 6, 7};
		int[] expected = {7, 6, 5, 4, 3, 2, 1};
		assertArrayEquals("the two arrays are not equal", expected, InOutSort.reverseInPlace(input));
	}

	@Test
	public void givenArray_whenOutOfPlaceSort_thenReversed() {
		int[] input = {1, 2, 3, 4, 5, 6, 7};
		int[] expected = {7, 6, 5, 4, 3, 2, 1};
		assertArrayEquals("the two arrays are not equal", expected, InOutSort.reverseOutOfPlace(input));
	}
}
