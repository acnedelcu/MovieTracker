using MovieTrackerAPI.Models;

namespace MovieTrackerAPI.Data.Repositories.Interfaces
{
    public interface ICinemaRepository
    {
        /// <summary>
        ///     Inserts a new Cinema into the database
        /// </summary>
        Task InsertAsync(Cinema cinema);

        /// <summary>
        ///     Gets the cinema by id
        /// </summary>
        Cinema? GetCinemaById(int id);

        /// <summary>
        ///     Gets all the cinemas from the database
        /// </summary>
        Task<List<Cinema>> GetAllCinemasAsync();

        /// <summary>
        ///     Gets all the movies from a given cinema
        /// </summary>
        List<Movie> GetAllMoviesInCinema(int cinemaId);

        /// <summary>
        ///     Adds an existing movie to a cinema
        /// </summary>
        void AddMovieToCinema(int movieId, int cinemaId);

        /// <summary>
        ///     Removes a given movie from a cinema
        /// </summary>
        void RemoveMovieFromCinema(int movieId, int cinemaId);
    }
}