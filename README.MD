# Movie App
Simple movie app written in Kotlin and using MVVM architecture that consults the public API The Movie Database (TMDb).

## Screenshots
<p float="left">
<img src="https://github.com/micaelagimenez/KotlinMovieApp/blob/main/screenshots/RecyclerView.jpg" width="250" height="450">
<img src="https://github.com/micaelagimenez/KotlinMovieApp/blob/main/screenshots/Detail.jpg" width="250" height="450">
<img src="https://github.com/micaelagimenez/KotlinMovieApp/blob/main/screenshots/Search.jpg" width="250" height="450">
</p>

## Structure
### Main Activity
Displays a list of the latest popular movies with their titles and posters. Movies can be selected to access their details in a new fragment.
### Details Fragment
Fragment that shows the clicked movie on main activity's details.
### Search bar
Search bar that allows to filter movies according to the their titles. If movie's title is not found, an error message is displayed for the user to see, otherwise the movie is returned.

 ## Libraries Used
 <ul>
 <li>Data Binding </li>
 <li>Picasso </li>
 <li>Retrofit </li>
 <li>OkHttp3 </li>
 <li>Lifecycle </li>
 </ul>
