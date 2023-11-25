using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Magazyn.Models
{
    public class Address
    {
        //[JsonIgnore]
        [JsonProperty("id")]
        public int Id { get; set; }
        [JsonProperty("street_name")]
        public string? StreetName { get; set; }
        [JsonProperty("house_number")]
        public string? HouseNumber { get; set; }
        [JsonProperty("local_number")]
        public int? LocalNumber { get; set; }
        [JsonProperty("place")]
        public string? Place {  get; set; }
        [JsonProperty("code")]
        public string? code { get; set; }
    }
}