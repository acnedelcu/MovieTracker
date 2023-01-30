using Microsoft.AspNetCore.Mvc;
using MovieTrackerAPI.Data.Repositories.Interfaces;
using MovieTrackerAPI.Models;

namespace MovieTrackerAPI.Controllers
{
    [ApiController]
    [Route("[controller]/[action]")]
    public class MoviesController : ControllerBase
    {
        private readonly IMovieRepository movieRepository;

        /// <summary>
        ///     Constructor
        /// </summary>
        public MoviesController(IMovieRepository movieRepository)
        {
            this.movieRepository = movieRepository;
        }

        /// <summary>
        ///     Adds s new Movie to the database
        /// </summary>
        [HttpPost]
        public async Task<IActionResult> AddMovie(Movie movie)
        {
            await movieRepository.AddMovieAsync(movie);
            return Ok();
        }

        /// <summary>
        ///     Adds a given movie to a user's watch list
        /// </summary>
        [HttpGet]
        public IActionResult AddMovieToUserWatchList(int movieId, int userId)
        {
            if (movieId < 0)
            {
                return BadRequest("Invalid movieId");
            }

            if (userId < 0)
            {
                return BadRequest();
            }

            movieRepository.AddMovieToUserWatchList(movieId, userId);
            return Ok();
        }

        /// <summary>
        ///     Retrieves a movie from the database based on the id
        /// </summary>
        [HttpGet]
        public IActionResult GetMovieById(int movieId)
        {
            if (movieId < 0)
            {
                return BadRequest("Invalid movie id provided!");
            }

            var movie = movieRepository.GetMovieById(movieId);
            if (movie == null)
            {
                return NotFound();
            }
            return Ok(movie);
        }

        /// <summary>
        ///     Returns all the movies from the database
        /// </summary>
        [HttpGet]
        public async Task<IActionResult> GetAllMovies()
        {
            List<Movie> movies = await movieRepository.GetAllMoviesAsync();
            return Ok(movies);
        }

        /// <summary>
        ///     Returns all the movies marked as watched for a given user
        /// </summary>
        [HttpPost]
        public IActionResult GetAllWatchedMoviesOfUser(User user)
        {
            if (user == null)
            {
                return BadRequest("The provided user cannot be null!");
            }

            var watchedMovies = movieRepository.GetAllWatchedMoviesOfUser(user);
            return Ok(watchedMovies);
        }

        /// <summary>
        ///     Returns all the movies marked as watched for a user, based on its id
        /// </summary>
        [HttpGet]
        public IActionResult GetAllWatchedMoviesByUserId(int userId)
        {
            if (userId < 0)
            {
                return BadRequest("Invalid userId provided!");
            }

            var watchedMovies = movieRepository.GetAllWatchedMoviesOfUserByUserId(userId);
            return Ok(watchedMovies);
        }

        /// <summary>
        ///     Removes a given movie from a user's watched list
        /// </summary>
        [HttpPost]
        public IActionResult RemoveMovieFromWatchList([FromBody] Movie movie, [FromQuery] User user)
        {
            if (movie == null)
            {
                return BadRequest("The provided movie cannot be null!");
            }

            if (user == null)
            {
                return BadRequest("The provided user cannot be null!");
            }
            movieRepository.RemoveMovieFromUsersWatchList(movie, user);
            return Ok();
        }

        /// <summary>
        ///     Removes a given movie from a user's watched list
        /// </summary>
        [HttpGet]
        public IActionResult RemoveMovieFromWatchListByUserId(int movieId, int userId)
        {
            if (movieId < 0)
            {
                return BadRequest("The provided movie id is not valid!");
            }

            if (userId < 0)
            {
                return BadRequest("The provided user id is not valid!");
            }

            movieRepository.RemoveMovieFromUsersWatchListByIds(movieId, userId);
            return Ok();
        }

        /// <summary>
        ///     Returns a list of cinemas where the given movie is running
        /// </summary>
        [HttpGet]
        public IActionResult GetAllCinemasWhereMovieIsRunning(int movieId)
        {
            if (movieId < 0)
            {
                return BadRequest("The provided movie id is invalid!");
            }

            List<Cinema> cinemasWherMovieIsRunning = movieRepository.GetAllCinemasWhereMovieIsRunning(movieId);
            return Ok(cinemasWherMovieIsRunning);
        }
    }
}