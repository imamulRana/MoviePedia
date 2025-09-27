package inc.anticbyte.moviepedia.utils

fun ratingFormat() {

}

fun voteCountFormat(voteCount: Int): String {
    return if (voteCount < 1000) {
        voteCount.toString()
    } else if (voteCount < 1000000) {
        if (voteCount > 1000) {
            (voteCount / 1000).toString() + " K+"
        } else {
            (voteCount / 1000).toString() + " K"
        }
    } else {
        (voteCount / 1000000).toString() + "M"
    }
}