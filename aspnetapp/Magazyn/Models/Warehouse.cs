namespace Magazyn.Models
{
    public class Warehouse
    {
        public int Id { get; set; }
        public string? Password { get; set; }
        public string? ColorId { get; set; }
        public int OsobaId { get; set; }
        public Osoba? OsobaEnt {  get; set; }

    }
}
