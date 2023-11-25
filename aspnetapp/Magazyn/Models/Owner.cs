using Newtonsoft.Json;

namespace Magazyn.Models
{
    public class Owner
    {
        //[JsonIgnore]
        [JsonProperty("id")]
        public int Id { get; set; }
        [JsonProperty("name")]
        public string? Name { get; set; }
        [JsonProperty("email")]
        public string? Email { get; set; }
    }
}