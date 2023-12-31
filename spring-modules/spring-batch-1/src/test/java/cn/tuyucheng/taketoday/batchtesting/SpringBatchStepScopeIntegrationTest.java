package cn.tuyucheng.taketoday.batchtesting;

import cn.tuyucheng.taketoday.batchtesting.model.Book;
import cn.tuyucheng.taketoday.batchtesting.model.BookRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = {SpringBatchConfiguration.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
class SpringBatchStepScopeIntegrationTest {

	private static final String TEST_OUTPUT = "src/test/resources/output/actual-output.json";

	private static final String EXPECTED_OUTPUT_ONE = "src/test/resources/output/expected-output-one.json";

	private static final String TEST_INPUT_ONE = "src/test/resources/input/test-input-one.csv";
	@Autowired
	private JsonFileItemWriter<Book> jsonItemWriter;

	@Autowired
	private FlatFileItemReader<BookRecord> itemReader;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	private JobParameters defaultJobParameters() {
		JobParametersBuilder paramsBuilder = new JobParametersBuilder();
		paramsBuilder.addString("file.input", TEST_INPUT_ONE);
		paramsBuilder.addString("file.output", TEST_OUTPUT);
		return paramsBuilder.toJobParameters();
	}

	@AfterEach
	void cleanUp() {
		jobRepositoryTestUtils.removeJobExecutions();
	}

	@Test
	void givenMockedStep_whenReaderCalled_thenSuccess() throws Exception {
		// given
		StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution(defaultJobParameters());

		// when
		StepScopeTestUtils.doInStepScope(stepExecution, () -> {
			BookRecord bookRecord;
			itemReader.open(stepExecution.getExecutionContext());
			while ((bookRecord = itemReader.read()) != null) {

				// then
				assertThat(bookRecord.getBookName(), is("Foundation"));
				assertThat(bookRecord.getBookAuthor(), is("Asimov I."));
				assertThat(bookRecord.getBookISBN(), is("ISBN 12839"));
				assertThat(bookRecord.getBookFormat(), is("hardcover"));
				assertThat(bookRecord.getPublishingYear(), is("2018"));
			}
			itemReader.close();
			return null;
		});
	}

	@Test
	void givenMockedStep_whenWriterCalled_thenSuccess() throws Exception {
		// given
		FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT_ONE);
		FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT);
		Book demoBook = new Book();
		demoBook.setAuthor("Grisham J.");
		demoBook.setName("The Firm");
		StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution(defaultJobParameters());

		// when
		StepScopeTestUtils.doInStepScope(stepExecution, () -> {

			jsonItemWriter.open(stepExecution.getExecutionContext());
			jsonItemWriter.write(List.of(demoBook));
			jsonItemWriter.close();
			return null;
		});

		// then
		AssertFile.assertFileEquals(expectedResult, actualResult);
	}
}