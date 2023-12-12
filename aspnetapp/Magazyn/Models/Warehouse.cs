using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Drawing;

namespace Magazyn.Models
{
    /// <summary>
    /// A model representing a Warehouse
    /// </summary>
    public class Warehouse
    {
        /// <summary>
        /// Unique id of a warehouse
        /// </summary>
        [JsonProperty("id")]
        public int Id { get; set; }
        /// <summary>
        /// Name of a warehouse
        /// </summary>
        [JsonProperty("name")]
        public string? Name { get; set; }
        /// <summary>
        /// Password of a warehouse
        /// </summary>
        [JsonProperty("password")]
        public string? Password { get; set; }
        [ForeignKey("Address")]
        [Required]
        public int AddressId { get; set; }
        /// <summary>
        /// Reference to a specific address model
        /// </summary>
        [JsonProperty("address")]
        public Address? Address { get; set; }
        /// <summary>
        /// Color of a background hold in a hexcode
        /// </summary>
        [JsonProperty("color")]
        public string? ColorId { get; set; }
        [ForeignKey("Owner")]
        //[JsonIgnore]
        public int OwnerId { get; set; }
        /// <summary>
        /// Reference to a specific owner model
        /// </summary>
        [ForeignKey("OwnerId")]
        [JsonProperty("owner")]
        public Owner? Owner { get; set; }
        /// <summary>
        /// List of all commodities
        /// </summary>
        public List<Commodity> Commodity { get; set; } = new List<Commodity>();
        /// <summary>
        /// List of all contractors
        /// </summary>
        public List<Contractor> Contractors { get; set; } = new List<Contractor>();
    }
}
