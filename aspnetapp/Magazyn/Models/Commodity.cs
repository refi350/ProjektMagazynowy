using Newtonsoft.Json;

namespace Magazyn.Models
{
    public class Commodity
    {
        [JsonProperty("id")]
        public int Id { get; set; }
        [JsonProperty("name")]
        public string? Name { get; set; }
        [JsonProperty("counter")]
        public int? Counter { get; set; }
        [JsonProperty("temp_counter")]
        public int? TempCounter { get; set; }
        [JsonProperty("code")]
        public long? Code { get; set; }
        [JsonProperty("description")]
        public string? Description { get; set; }
    }


}
