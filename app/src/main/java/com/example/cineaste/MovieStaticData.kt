package com.example.cineaste

fun getFavoriteMovies(): List<Movie> {
    return listOf(
        Movie(1,"Pride and prejudice",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "drama"),
        Movie(2,"Shooter",
            "A marksman living in exile is coaxed back into action after hearing of a plot to kill the President. After being-double crossed for the attempt and on the run, he sets out for the real killer and the truth.",
            "08.03.2007.","https://www.imdb.com/title/tt0822854/",
            "action"),
        Movie(3,"No Time to Die",
            "James Bond has left active service. His peace is short-lived when Felix Leiter, an old friend from the CIA, turns up asking for help, leading Bond onto the trail of a mysterious villain armed with dangerous new technology.",
            "28.09.2021.","https://www.imdb.com/title/tt2382320/?ref_=ttls_li_tt",
            "action"),
        Movie(4,"The Dictator",
            "The heroic story of a dictator who risked his life to ensure that democracy would never come to the country he so lovingly oppressed.",
            "16.05.2012.","https://www.imdb.com/title/tt1645170/?ref_=ttls_li_tt",
            "comedy")
    )
}
fun getRecentMovies(): List<Movie> {
    return listOf(
        Movie(1,"Furiosa: A Mad Max Saga",
            "The origin story of renegade warrior Furiosa before her encounter and teamup with Mad Max.",
            "24.05.2024.","https://www.imdb.com/title/tt12037194",
            "action")
    )
}