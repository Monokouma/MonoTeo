package domain.weather

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.isTrue
import com.despaircorp.domain.weather.WeatherSentenceGenerator
import org.junit.jupiter.api.Test

class WeatherSentenceGeneratorUnitTest {

    @Test
    fun `nominal case - returns phrase for Thunderstorm in french`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Thunderstorm",
            temp = 20.0,
            lang = "fr"
        )

        assertThat(result).contains("⚡")
    }

    @Test
    fun `nominal case - returns phrase for Thunderstorm in english`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Thunderstorm",
            temp = 20.0,
            lang = "en"
        )

        assertThat(result).contains("⚡")
    }

    @Test
    fun `nominal case - returns phrase for Rain in french`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Rain",
            temp = 15.0,
            lang = "fr"
        )

        assertThat(result.contains("🐌") || result.contains("🌧️")).isTrue()
    }

    @Test
    fun `nominal case - returns phrase for Clear in french`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Clear",
            temp = 25.0,
            lang = "fr"
        )

        assertThat(result.contains("😎") || result.contains("☀️")).isTrue()
    }

    @Test
    fun `nominal case - returns phrase for Snow in french`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Snow",
            temp = -5.0,
            lang = "fr"
        )

        assertThat(result).contains("☃️")
    }

    @Test
    fun `nominal case - returns phrase for Clouds in english`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Clouds",
            temp = 18.0,
            lang = "en"
        )

        assertThat(result).contains("☁️")
    }

    @Test
    fun `nominal case - returns unknown phrase for invalid condition`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "InvalidCondition",
            temp = 20.0,
            lang = "fr"
        )

        assertThat(result).contains("Météo inconnue 🤷")
    }

    @Test
    fun `nominal case - returns unknown phrase for invalid condition in english`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "InvalidCondition",
            temp = 20.0,
            lang = "en"
        )

        assertThat(result).contains("Unknown weather 🤷")
    }

    @Test
    fun `temperature - returns freezing phrase when temp below 0`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Clear",
            temp = -10.0,
            lang = "fr"
        )

        assertThat(result).contains("🥶")
    }

    @Test
    fun `temperature - returns cold phrase when temp between 0 and 10`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Clear",
            temp = 5.0,
            lang = "fr"
        )

        assertThat(result).contains("🧥")
    }

    @Test
    fun `temperature - returns cool phrase when temp between 10 and 15`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Clear",
            temp = 12.0,
            lang = "fr"
        )

        assertThat(result).contains("🧣")
    }

    @Test
    fun `temperature - returns perfect phrase when temp between 15 and 20`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Clear",
            temp = 18.0,
            lang = "fr"
        )

        assertThat(result).contains("👌")
    }

    @Test
    fun `temperature - returns warm phrase when temp between 20 and 25`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Clear",
            temp = 22.0,
            lang = "fr"
        )

        assertThat(result).contains("☀️")
    }

    @Test
    fun `temperature - returns hot phrase when temp between 25 and 30`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Clear",
            temp = 28.0,
            lang = "fr"
        )

        assertThat(result).contains("🍦")
    }

    @Test
    fun `temperature - returns heatwave phrase when temp above 30`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Clear",
            temp = 35.0,
            lang = "fr"
        )

        assertThat(result).contains("🥵")
    }

    @Test
    fun `result contains two lines`() {
        val result = WeatherSentenceGenerator.getWeatherPhrase(
            condition = "Clear",
            temp = 20.0,
            lang = "fr"
        )

        assertThat(result.split("\n")).hasSize(2)
    }
}