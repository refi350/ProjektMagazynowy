using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Magazyn.Models
{
    public class Address
    {
        public int Id { get; set; }
        public string? StreetName { get; set; }
        public string? HouseNumber { get; set; }
        public int? LocalNumber { get; set; }
        public string? Place {  get; set; }
        public string? code { get; set; }
        [ForeignKey("Warehouse")]
        public int WarehouseId { get; set; }
        [ForeignKey("WarehouseId")]
        public Warehouse? Warehouse { get; set; }
    }
}