package cn.tuyucheng.taketoday.csv.pure

import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.time.Year
import kotlin.test.assertEquals

internal class PureKotlinCsvOperationsUnitTest {

	@Test
	fun when_file_is_read_then_domain_objects_are_populated() {
		val movies = cn.tuyucheng.taketoday.csv.pure.readCsv(javaClass.getResourceAsStream("deniro.csv")!!)
		assertEquals(87, movies.size)
		assertEquals("Dear America: Letters Home From Vietnam", movies.maxBy { it.score }.title)
	}

	@Test
	fun write_csv() {
		val movies = listOf(
			cn.tuyucheng.taketoday.csv.model.Movie(1996.toYear(), 74, "Sleepers"),
			cn.tuyucheng.taketoday.csv.model.Movie(1996.toYear(), 38, "The Fan"),
			cn.tuyucheng.taketoday.csv.model.Movie(1996.toYear(), 80, "Marvin's Room"),
			cn.tuyucheng.taketoday.csv.model.Movie(1997.toYear(), 85, "Wag the Dog"),
			cn.tuyucheng.taketoday.csv.model.Movie(1997.toYear(), 87, "Jackie Brown"),
			cn.tuyucheng.taketoday.csv.model.Movie(1997.toYear(), 72, "Cop Land"),
			cn.tuyucheng.taketoday.csv.model.Movie(1998.toYear(), 68, "Ronin")
		)
		val csv = ByteArrayOutputStream().apply { writeCsv(movies) }
			.toByteArray().let { String(it) }
		assertEquals(
			"""
                "Year", "Score", "Title"
                1996, 74, "Sleepers"
                1996, 38, "The Fan"
                1996, 80, "Marvin's Room"
                1997, 85, "Wag the Dog"
                1997, 87, "Jackie Brown"
                1997, 72, "Cop Land"
                1998, 68, "Ronin"

                """.trimIndent(), csv
		)
	}

	fun Int.toYear(): Year = Year.of(this)
}