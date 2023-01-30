using MovieTrackerAPI.Models;

namespace MovieTrackerAPI.Data.Repositories.Interfaces
{
    public interface IMovieRepository
    {
        /// <summary>
        ///     Adds s new Movie to the database
        /// </summary>
        Task AddMovieAsync(Movie movie);

        /// <summary>
        ///     Adds a given movie to a user's watch list
        /// </summary>
        void AddMovieToUserWatchList(int movieId, int userId);

        /// <summary>
        ///     Retrieves a movie from the database based on the id
        /// </summary>
        Movie GetMovieById(int movieId);

        /// <summary>
        ///     Returns all the movies from the database
        /// </summary>
        Task<List<Movie>> GetAllMoviesAsync();

        /// <summary>
        ///     Returns all the movies marked as watched for a given user
        /// </summary>
        List<Movie> GetAllWatchedMoviesOfUser(User user);

        /// <summary>
        ///     Returns all the movies marked as watched for a user, based on its id
        /// </summary>
        List<Movie> GetAllWatchedMoviesOfUserByUserId(int userId);

        /// <summary>
        ///     Removes a given movie from a user's watched list
        /// </summary>
        void RemoveMovieFromUsersWatchList(Movie movie, User user);

        /// <summary>
        ///     Removes a given movie from a user's watched list, based on their ids
        /// </summary>
        void RemoveMovieFromUsersWatchListByIds(int movieId, int userId);

        /// <summary>
        ///     Returns a list of cinemas where the given movie is running
        /// </summary>
        List<Cinema> GetAllCinemasWhereMovieIsRunning(int movieId);
    }
}