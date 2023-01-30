using Microsoft.AspNetCore.Mvc;
using MovieTrackerAPI.Data.Repositories.Interfaces;
using MovieTrackerAPI.Models;

namespace MovieTrackerAPI.Controllers
{
    [ApiController]
    [Route("[controller]/[action]")]
    public class CinemasController : ControllerBase
    {
        private readonly ICinemaRepository cinemaRepository;

        /// <summary>
        ///     Constructor
        /// </summary>
        public CinemasController(ICinemaRepository cinemaRepository)
        {
            this.cinemaRepository = cinemaRepository;
        }

        /// <summary>
        ///     Adds a new cinema to the database
        /// </summary>
        [HttpPost]
        public async Task<IActionResult> AddCinema(Cinema cinema)
        {
            if (cinema == null)
            {
                return BadRequest("The provided cinema cannot be null!");
            }
            await cinemaRepository.InsertAsync(cinema);
            return Ok();
        }

        /// <summary>
        ///     Gets the cinema by id
        /// </summary>
        [HttpGet]
        public IActionResult GetCinemaById(int cinemaId)
        {
            if (cinemaId < 0)
            {
                return BadRequest("The provided cinema id is not valid!");
            }

            var cinema = cinemaRepository.GetCinemaById(cinemaId);
            return Ok(cinema);
        }

        /// <summary>
        ///     Gets all the cinemas from the database
        /// </summary>
        [HttpGet]
        public async Task<IActionResult> GetAllCinemas()
        {
            return Ok(await cinemaRepository.GetAllCinemasAsync());
        }

        /// <summary>
        ///     Gets all the movies from a given cinema
        /// </summary>
        [HttpGet]
        public IActionResult GetAllMoviesInCinema(int cinemaId)
        {
            if (cinemaId < 0)
            {
                return BadRequest("The provided cinemaId is not valid!");
            }

            var movies = cinemaRepository.GetAllMoviesInCinema(cinemaId);
            return Ok(movies);
        }

        /// <summary>
        ///     Adds an existing movie to a cinema
        /// </summary>
        [HttpPost]
        public IActionResult AddMovieToCinema(int movieId, int cinemaId)
        {
            if (movieId < 0)
            {
                return BadRequest("The provided movieId is not valid!");
            }

            if (cinemaId < 0)
            {
                return BadRequest("The provided cinema id is not valid!");
            }
            cinemaRepository.AddMovieToCinema(movieId, cinemaId);
            return Ok();
        }

        /// <summary>
        ///     Removes a given movie from a cinema
        /// </summary>
        [HttpGet]
        public IActionResult RemoveMovieFromCinema(int movieId, int cinemaId)
        {
            if (movieId < 0)
            {
                return BadRequest("The provided movie id is not valid!");
            }

            if (cinemaId < 0)
            {
                return BadRequest("The provided cinema id is not valid!");
            }
            cinemaRepository.RemoveMovieFromCinema(movieId, cinemaId);
            return Ok();
        }
    }
}