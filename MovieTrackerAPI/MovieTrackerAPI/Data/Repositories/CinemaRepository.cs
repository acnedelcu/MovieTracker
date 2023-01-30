using Microsoft.EntityFrameworkCore;
using MovieTrackerAPI.Data.Repositories.Interfaces;
using MovieTrackerAPI.Models;

namespace MovieTrackerAPI.Data.Repositories
{
    public class CinemaRepository : ICinemaRepository
    {
        private readonly AppDbContext dbContext;

        /// <summary>
        ///     Constructor
        /// </summary>
        public CinemaRepository(AppDbContext dbContext)
        {
            this.dbContext = dbContext;
        }

        public async Task InsertAsync(Cinema cinema)
        {
            if (cinema == null)
            {
                throw new ArgumentNullException(nameof(cinema));
            }
            await dbContext.AddAsync(cinema);
            await dbContext.SaveChangesAsync();
        }

        public Cinema? GetCinemaById(int id)
        {
            if (id < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(id));
            }

            Cinema? cinema = dbContext.Cinemas.Where(c => c.Id == id).FirstOrDefault();
            return cinema;
        }

        public async Task<List<Cinema>> GetAllCinemasAsync()
        {
            return await dbContext.Cinemas.ToListAsync();
        }

        public List<Movie> GetAllMoviesInCinema(int cinemaId)
        {
            if (cinemaId < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(cinemaId));
            }

            List<Movie> movies = dbContext.Cinemas.Where(c => c.Id == cinemaId).Select(c => c.Movies.ToList()).FirstOrDefault();
            _ = movies ?? throw new Exception(nameof(movies));

            return movies;
        }

        public void AddMovieToCinema(int movieId, int cinemaId)
        {
            if (movieId < 0) { throw new ArgumentNullException(nameof(movieId)); }
            if (cinemaId < 0) { throw new ArgumentNullException(nameof(cinemaId)); }

            Movie? movie = dbContext.Movies.Where(m => m.Id == movieId).FirstOrDefault();
            _ = movie ?? throw new Exception($"No movie could be found using the provided id {movieId}");
            _ = movie.Cinemas ?? throw new Exception("Initalization failed!");

            Cinema? cinema = dbContext.Cinemas.Where(c => c.Id == movieId).FirstOrDefault();
            _ = cinema ?? throw new Exception($"No cinema could be found with the provided id {cinemaId}");
            cinema.Movies.Add(movie);
            movie.Cinemas.Add(cinema);

            dbContext.Movies.Update(movie);
            dbContext.Cinemas.Update(cinema);
            dbContext.SaveChanges();
        }

        public void RemoveMovieFromCinema(int movieId, int cinemaId)
        {
            if(movieId < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(movieId));
            }

            if(cinemaId < 0)
            {
                throw new ArgumentOutOfRangeException(nameof(cinemaId));
            }

            Movie? movie = dbContext.Movies.Where(m => m.Id == movieId).Include(c =>c.Cinemas).FirstOrDefault();
            _ = movie ?? throw new ArgumentNullException(nameof(movie));
            _ = movie.Cinemas ?? throw new ArgumentNullException(nameof(movie.Cinemas));

            Cinema? cinema = dbContext.Cinemas.Where(c => c.Id == cinemaId).Include(m => m.Movies).FirstOrDefault();
            _ = cinema ?? throw new ArgumentNullException(nameof(cinema));
            _ = cinema.Movies ?? throw new ArgumentNullException(nameof(cinema.Movies));

            if (!cinema.Movies.Contains(movie))
            {
                throw new Exception($"Cannot remove the movie with id {movieId} from the cinema with id {movieId} as it is not running in the given cinema!");
            }

            cinema.Movies.Remove(movie);
            movie.Cinemas.Remove(cinema);
            dbContext.SaveChanges();
        }
    }
}