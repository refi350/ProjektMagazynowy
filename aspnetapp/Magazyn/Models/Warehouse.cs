using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Drawing;

namespace Magazyn.Models
{
    public class Warehouse
    {
        public int Id { get; set; }
        public string? Name { get; set; }
        public string? Password { get; set; }
        [ForeignKey("Address")]
        [Required]
        public int AddressId { get; set; }
        [ForeignKey("AddressId")]
        public Address? Address { get; set; }
        public string? ColorId { get; set; }
        [ForeignKey("Owner")]
        public int OwnerId { get; set; }
        [ForeignKey("OwnerId")]
        public Owner? Owner { get; set; }
        public List<Commodity> Commodity { get; set; } = new List<Commodity>();
    }
}
