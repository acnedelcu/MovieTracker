using MovieTrackerAPI.Models;

namespace MovieTrackerAPI.Data.Repositories.Interfaces
{
    public interface IUserRepository
    {
        /// <summary>
        ///     Adds a new user to the database
        /// </summary>
        Task CreateUserAsync(User user);

        /// <summary>
        ///     Returns all the users from the database
        /// </summary>
        Task<List<User>> GetAllUsersAsync();
    }
}