package cn.tuyucheng.taketoday.mockito.spy;

import org.junit.jupiter.api.Test;
import org.mockito.exceptions.misusing.NotAMockException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class MockitoMisusingMockOrSpyUnitTest {

	@Test
	void givenNotASpy_whenDoReturn_thenThrowNotAMock() {
		List<String> list = new ArrayList<>();

		assertThatThrownBy(() -> doReturn(100).when(list).size())
			.isInstanceOf(NotAMockException.class)
			.hasMessageContaining("Argument passed to when() is not a mock!");
	}

	@Test
	void givenASpy_whenDoReturn_thenNoError() {
		final List<String> spyList = spy(new ArrayList<>());

		assertThatNoException().isThrownBy(() -> doReturn(100).when(spyList).size());
	}
}