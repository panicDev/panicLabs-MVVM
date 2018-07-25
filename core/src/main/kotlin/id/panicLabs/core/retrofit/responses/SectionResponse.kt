package id.panicLabs.core.retrofit.responses

data class SectionResponse(
        val page: Page = Page(),
        val posts: List<Post> = listOf(),
        var error: String = ""
) {
    data class Post(
            val id: String = "",
            val title: String = "",
            val url: String = "",
            val img: String = "",
            val votes: String = "",
            val nsfw: Boolean = false
    )

    data class Page(
            val next: String = ""
    )
}