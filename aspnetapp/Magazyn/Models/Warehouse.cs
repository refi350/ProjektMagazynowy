using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Drawing;

namespace Magazyn.Models
{
    public class Warehouse
    {
        //[JsonIgnore]
        [JsonProperty("id")]
        public int Id { get; set; }
        [JsonProperty("name")]
        public string? Name { get; set; }
        [JsonProperty("password")]
        public string? Password { get; set; }
        [ForeignKey("Address")]
        [Required]
        //[JsonIgnore]
        public int AddressId { get; set; }
        [JsonProperty("address")]
        public Address? Address { get; set; }
        [JsonProperty("color")]
        public string? ColorId { get; set; }
        [ForeignKey("Owner")]
        //[JsonIgnore]
        public int OwnerId { get; set; }
        [ForeignKey("OwnerId")]
        [JsonProperty("owner")]
        public Owner? Owner { get; set; }
        //[JsonIgnore]
        public List<Commodity> Commodity { get; set; } = new List<Commodity>();
    }
}
