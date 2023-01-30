namespace MovieTrackerAPI.Models
{
    public class Movie
    {
        public Movie()
        {
            Users = new List<User>();
            Cinemas = new List<Cinema>();
        }

        #region Properties

        public int Id { get; set; }

        public string Name { get; set; }

        public DateTime ReleaseDate { get; set; }

        public string Country { get; set; }

        public string Language { get; set; }

        public string Genre { get; set; }

        public double Rating { get; set; }

        public string Plot { get; set; }

        public string Poster { get; set; }

        public string Runtime { get; set; }

        #endregion Properties

        #region NavigationProperties

        public virtual ICollection<User>? Users { get; set; }

        public virtual ICollection<Cinema>? Cinemas { get; set; }

        #endregion NavigationProperties
    }
}