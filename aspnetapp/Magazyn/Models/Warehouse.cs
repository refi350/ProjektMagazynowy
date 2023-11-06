using System.ComponentModel.DataAnnotations.Schema;
using System.Drawing;

namespace Magazyn.Models
{
    public class Warehouse
    {
        public int Id { get; set; }
        public string? Name { get; set; }
        public string? Password { get; set; }
        public Address? Address { get; set; }
        public string? ColorId { get; set; }
        public byte[]? Image { get; set; }
        [ForeignKey("Owner")]
        public int OwnerId { get; set; }
        public Owner? Owner { get; set; }
    }
}
