namespace MovieTrackerAPI.Models
{
    public class Cinema
    {
        public Cinema()
        {
            Movies = new List<Movie>();
        }

        #region Properties

        public int Id { get; set; }

        public string Name { get; set; }

        public string Geolocation { get; set; }

        #endregion Properties

        #region NavigationProperties

        public virtual ICollection<Movie> Movies { get; set; }

        #endregion NavigationProperties
    }
}