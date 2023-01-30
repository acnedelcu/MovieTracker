namespace MovieTrackerAPI.Models
{
    public class User
    {
        public User()
        {
            WatchedMovies = new List<Movie>();
        }


        #region Properties

        public int Id { get; set; }

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public string Username { get; set; }

        public string EmailAddress { get; set; }

        public string Password { get; set; }

        public string Role { get; set; }

        #endregion

        #region NavigationProperties

        public virtual ICollection<Movie> WatchedMovies { get; set; }

        #endregion
    }
}
