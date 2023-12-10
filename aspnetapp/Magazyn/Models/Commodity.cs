using Newtonsoft.Json;

namespace Magazyn.Models
{
    /// <summary>
    /// A model representing currently stored contents inside a warehouse
    /// </summary>
    public class Commodity
    {
        /// <summary>
        /// unique id of the Commodity
        /// </summary>
        [JsonProperty("id")]
        public int Id { get; set; }
        /// <summary>
        /// Name of the Commodity
        /// </summary>
        [JsonProperty("name")]
        public string? Name { get; set; }
        /// <summary>
        /// Current amount of the commodity
        /// </summary>
        [JsonProperty("counter")]
        public int? Counter { get; set; }
        /// <summary>
        /// Updated amount of the commodity 
        /// </summary>
        [JsonProperty("temp_counter")]
        public int? TempCounter { get; set; }
        /// <summary>
        /// Code of the commodity
        /// </summary>
        [JsonProperty("code")]
        public long? Code { get; set; }
        /// <summary>
        /// Detailed description of a commodity
        /// </summary>
        [JsonProperty("description")]
        public string? Description { get; set; }
        public int? WarehouseId { get; set; }
    }


}
