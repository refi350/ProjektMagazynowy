using Newtonsoft.Json;

namespace Magazyn.Models
{
    public class Contractor
    {
        /// <summary>
        /// Id of a contractor
        /// </summary>
        [JsonProperty("id")]
        public int Id { get; set; }
        /// <summary>
        /// Name of a contractor
        /// </summary>
        [JsonProperty("name")]
        public string? Name { get; set; }
        /// <summary>
        /// Address of a contractor
        /// </summary>
        [JsonProperty("address")]
        public Address? Address { get; set; }
        /// <summary>
        /// Is this contractor a recipient
        /// </summary>
        [JsonProperty("recipient")]
        public bool Recipient {  get; set; }
        /// <summary>
        /// Is this contractor a supplier
        /// </summary>
        [JsonProperty("supplier")]
        public bool Supplier { get; set; }
        /// <summary>
        /// NIP code
        /// </summary>
        [JsonProperty("nip")]
        public string? Nip { get; set; }
    }
}
