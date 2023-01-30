using Microsoft.EntityFrameworkCore;
using MovieTrackerAPI.Data.Repositories.Interfaces;
using MovieTrackerAPI.Models;

namespace MovieTrackerAPI.Data.Repositories
{
    public class MovieRepository : IMovieRepository
    {
        private readonly AppDbContext dbContext;

        /// <summary>
        ///     Constructor
        /// </summary>
        public MovieRepository(AppDbContext dbContext)
        {
            this.dbContext = dbContext;
        }

        public async Task AddMovieAsync(Movie movie)
        {
            _ = movie ?? throw new ArgumentNullException(nameof(movie));

            await dbContext.Movies.AddAsync(movie);
            await dbContext.SaveChangesAsync();
        }

        public void AddMovieToUserWatchList(int movieId, int userId)
        {
            if (movieId < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(movieId));
            }

            if (userId < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(userId));
            }

            User? user = dbContext.Users.Where(u => u.Id == userId).FirstOrDefault();
            _ = user ?? throw new ArgumentNullException(nameof(user));
            _ = user.WatchedMovies ?? throw new ArgumentNullException(nameof(user.WatchedMovies));

            Movie? movie = dbContext.Movies.Where(m => m.Id == movieId).FirstOrDefault();
            _ = movie ?? throw new ArgumentNullException(nameof(movie));

            if (!user.WatchedMovies.Contains(movie))
            {
                user.WatchedMovies.Add(movie);
                movie.Users.Add(user);
                dbContext.SaveChanges();
            }
        }

        public Movie GetMovieById(int movieId)
        {
            if (movieId < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(movieId));
            }

            Movie movie = dbContext.Movies.Where(m => m.Id == movieId).FirstOrDefault();
            _ = movie ?? throw new ArgumentNullException("No movie could be found in the database using the provided id!");

            return movie;
        }

        public async Task<List<Movie>> GetAllMoviesAsync()
        {
            return await dbContext.Movies.ToListAsync();
        }

        public List<Movie> GetAllWatchedMoviesOfUser(User user)
        {
            _ = user ?? throw new ArgumentNullException(nameof(user));
            _ = user.WatchedMovies ?? throw new ArgumentNullException(nameof(user));

            List<Movie> movies = dbContext.Users.Where(u => u == user).Select(u => u.WatchedMovies.ToList()).FirstOrDefault();
            _ = movies ?? throw new ArgumentNullException(nameof(movies));
            return movies;
        }

        public List<Movie> GetAllWatchedMoviesOfUserByUserId(int userId)
        {
            if (userId < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(userId));
            }

            List<Movie> movies = dbContext.Users.Where(u => u.Id == userId).Select(u => u.WatchedMovies.ToList()).FirstOrDefault();
            _ = movies ?? throw new ArgumentNullException(nameof(movies));
            return movies;
        }

        public void RemoveMovieFromUsersWatchList(Movie movie, User user)
        {
            _ = movie ?? throw new ArgumentNullException(nameof(movie));
            _ = user ?? throw new ArgumentNullException(nameof(user));

            User? dbUser = dbContext.Users.Where(u => u == user).FirstOrDefault();
            if (dbUser == null)
            {
                throw new Exception("The provided user could not be found in the database!");
            }

            Movie? dbMovie = dbContext.Movies.Where(m => m == movie).FirstOrDefault();
            if (dbMovie == null)
            {
                throw new Exception("The provided movie could not be found in the database!");
            }

            if (!dbUser.WatchedMovies.Contains(movie))
            {
                throw new Exception("The movie cannot be removed from the user's watchlist as it is not marked as watched!");
            }

            RemoveMovieFromUsersWatchListByIds(movie.Id, user.Id);
        }

        public void RemoveMovieFromUsersWatchListByIds(int movieId, int userId)
        {
            if (movieId < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(movieId));
            }

            if (userId < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(userId));
            }

            Movie? movie = dbContext.Movies.Where(m => m.Id == movieId).Include(m => m.Users).FirstOrDefault();
            _ = movie ?? throw new Exception("No movie with the provided id exists in the database!");
            _ = movie.Users ?? throw new Exception(nameof(movie));

            User? user = dbContext.Users.Where(u => u.Id == userId).Include(u => u.WatchedMovies).FirstOrDefault();
            _ = user ?? throw new Exception("No user with the provided id exists in the database!");
            _ = user.WatchedMovies ?? throw new Exception(nameof(user));

            movie.Users.Remove(user);
            user.WatchedMovies.Remove(movie);
            dbContext.SaveChanges();
        }

        public List<Cinema> GetAllCinemasWhereMovieIsRunning(int movieId)
        {
            if (movieId < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(movieId));
            }

            Movie? movie = dbContext.Movies.Where(m => m.Id == movieId).FirstOrDefault();
            _ = movie ?? throw new ArgumentNullException(nameof(movie));
            _ = movie.Cinemas ?? throw new ArgumentNullException(nameof(movie.Cinemas));

            return dbContext.Movies.Where(m => m.Id == movieId).Select(m => m.Cinemas.ToList()).First();
        }

    }
}
