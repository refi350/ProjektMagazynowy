using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Magazyn.Models
{
    /// <summary>
    /// A model representing a physical adress, or location
    /// </summary>
    public class Address
    {
        /// <summary>
        /// unique id of the Adress
        /// </summary>
        [JsonProperty("id")]
        public int Id { get; set; }
        /// <summary>
        /// Street name of a specific location
        /// </summary>
        [JsonProperty("street_name")]
        public string? StreetName { get; set; }
        /// <summary>
        /// Number of the house
        /// </summary>
        [JsonProperty("house_number")]
        public string? HouseNumber { get; set; }
        /// <summary>
        /// Number of the exact local
        /// </summary>
        [JsonProperty("local_number")]
        public int? LocalNumber { get; set; }
        /// <summary>
        /// City name
        /// </summary>
        [JsonProperty("place")]
        public string? Place {  get; set; }
        /// <summary>
        /// Postal code
        /// </summary>
        [JsonProperty("code")]
        public string? code { get; set; }
    }
}