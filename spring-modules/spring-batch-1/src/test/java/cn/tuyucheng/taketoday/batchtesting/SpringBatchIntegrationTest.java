package cn.tuyucheng.taketoday.batchtesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
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

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = {SpringBatchConfiguration.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
class SpringBatchIntegrationTest {

	private static final String TEST_OUTPUT = "src/test/resources/output/actual-output.json";

	private static final String EXPECTED_OUTPUT = "src/test/resources/output/expected-output.json";

	private static final String TEST_INPUT = "src/test/resources/input/test-input.csv";

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@AfterEach
	void cleanUp() {
		jobRepositoryTestUtils.removeJobExecutions();
	}

	private JobParameters defaultJobParameters() {
		JobParametersBuilder paramsBuilder = new JobParametersBuilder();
		paramsBuilder.addString("file.input", TEST_INPUT);
		paramsBuilder.addString("file.output", TEST_OUTPUT);
		return paramsBuilder.toJobParameters();
	}

	@Test
	void givenReferenceOutput_whenJobExecuted_thenSuccess() throws Exception {
		// given
		FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT);
		FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT);

		// when
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());
		JobInstance actualJobInstance = jobExecution.getJobInstance();
		ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

		// then
		assertThat(actualJobInstance.getJobName(), is("transformBooksRecords"));
		assertThat(actualJobExitStatus.getExitCode(), is("COMPLETED"));
		AssertFile.assertFileEquals(expectedResult, actualResult);
	}

	@Test
	void givenReferenceOutput_whenStep1Executed_thenSuccess() throws Exception {

		// given
		FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT);
		FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT);

		// when
		JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1", defaultJobParameters());
		Collection<StepExecution> actualStepExecutions = jobExecution.getStepExecutions();
		ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

		// then
		assertThat(actualStepExecutions.size(), is(1));
		assertThat(actualJobExitStatus.getExitCode(), is("COMPLETED"));
		AssertFile.assertFileEquals(expectedResult, actualResult);
	}

	@Test
	void whenStep2Executed_thenSuccess() {

		// when
		JobExecution jobExecution = jobLauncherTestUtils.launchStep("step2", defaultJobParameters());
		Collection<StepExecution> actualStepExecutions = jobExecution.getStepExecutions();
		ExitStatus actualExitStatus = jobExecution.getExitStatus();

		// then
		assertThat(actualStepExecutions.size(), is(1));
		assertThat(actualExitStatus.getExitCode(), is("COMPLETED"));
		actualStepExecutions.forEach(stepExecution -> {
			assertThat(stepExecution.getWriteCount(), is(8));
		});
	}
}