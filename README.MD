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
### Search bar
Search bar that allows to filter movies according to the their titles. If movie's title is not found, an error message is displayed for the user to see, otherwise the movie is returned.
### Movie details
Fragment that shows the clicked movie on main activity's details.
### Rating a movie
Movies can be rated in the movie's detail's fragment from 1 to 10 stars. The post request with the rating is sent to TMDB. The request is possible through a guest session the user initiates when the app is opened for the first time.

 ## Libraries Used
 <ul>
 <li> <a href="https://developer.android.com/topic/libraries/data-binding/">Data Binding</a> </li>
 <li> <a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel</a> </li>
 <li> <a href="https://developer.android.com/topic/libraries/architecture/livedata">LiveData</a> </li>
 <li> <a href="http://square.github.io/retrofit/">Retrofit</a> </li>
 <li> <a href="http://square.github.io/picasso/">Picasso</a> </li>
 <li> <a href="http://square.github.io/okhttp/">OkHttp3</a> </li>
 <li> <a href="https://github.com/zhanghai/MaterialRatingBar">MaterialRatingBar</a> </li>
 </ul>
