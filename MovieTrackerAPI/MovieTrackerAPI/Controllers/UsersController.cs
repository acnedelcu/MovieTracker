using Microsoft.AspNetCore.Mvc;
using MovieTrackerAPI.Data.Repositories.Interfaces;
using MovieTrackerAPI.Models;

namespace MovieTrackerAPI.Controllers
{
    [ApiController]
    [Route("[controller]/[action]")]
    public class UsersController : ControllerBase
    {
        private readonly IUserRepository userRepository;

        /// <summary>
        ///     Constructor
        /// </summary>
        public UsersController(IUserRepository userRepository)
        {
            this.userRepository = userRepository;
        }

        [HttpPost]
        public async Task<IActionResult> CreateUserAsync(User user)
        {
            await userRepository.CreateUserAsync(user);
            return Ok();
        }

        [HttpGet]
        public async Task<IActionResult> GetAllUsers()
        {
            var users = await userRepository.GetAllUsersAsync();
            return Ok(users);
        }
    }
}