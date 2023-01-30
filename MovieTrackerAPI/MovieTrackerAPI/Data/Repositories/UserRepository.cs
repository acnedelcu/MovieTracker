using Microsoft.EntityFrameworkCore;
using MovieTrackerAPI.Data.Repositories.Interfaces;
using MovieTrackerAPI.Models;

namespace MovieTrackerAPI.Data.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly AppDbContext dbContext;

        /// <summary>
        ///     Constructor
        /// </summary>
        public UserRepository(AppDbContext dbContext)
        {
            this.dbContext = dbContext;
        }

        public async Task CreateUserAsync(User user)
        {
            if (user == null)
            {
                throw new ArgumentNullException("user");
            }

            await dbContext.Users.AddAsync(user);
            await dbContext.SaveChangesAsync();
        }

        public async Task<List<User>> GetAllUsersAsync()
        {
            return await dbContext.Users.ToListAsync();
        }
    }
}
