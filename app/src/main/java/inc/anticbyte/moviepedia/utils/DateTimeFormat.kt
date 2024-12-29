package inc.anticbyte.moviepedia.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun toDate(dateString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val localDate = inputFormatter.parse(dateString)
    return outputFormatter.format(localDate)
}

fun calculateAge(birthDay: String?): String {
    val localDate = LocalDate.now()
    return if (birthDay.isNullOrEmpty()) {
        "-"
    } else {
        val age = localDate.year - birthDay.substring(0, 4).toInt()
        "  (${age.toString().plus("Years")})"
    }
}