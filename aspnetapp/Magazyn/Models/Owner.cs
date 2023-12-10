using Newtonsoft.Json;

namespace Magazyn.Models
{
    /// <summary>
    /// A model representing a physical Owner of a warehouse
    /// </summary>
    public class Owner
    {
        /// <summary>
        /// unique id of the Owner
        /// </summary>
        [JsonProperty("id")]
        public int Id { get; set; }
        /// <summary>
        /// Name of the Owner
        /// </summary>
        [JsonProperty("name")]
        public string? Name { get; set; }
        /// <summary>
        /// Email of the Owner
        /// </summary>
        [JsonProperty("email")]
        public string? Email { get; set; }
    }
}