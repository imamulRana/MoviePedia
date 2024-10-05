package inc.anticbyte.moviepedia.utils

import java.time.format.DateTimeFormatter

fun toDate(dateString: String): String {
    val date = dateString.split("-")
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val localDate = inputFormatter.parse(dateString)
    return outputFormatter.format(localDate)
}