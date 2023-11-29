namespace Magazyn.Models
{
    public class Commodity
    {
        public int Id { get; set; }
        public string? Name { get; set; }
        public int? Counter { get; set; }
        public int? TempCounter { get; set; }
        public long? Code { get; set; }
        public string? Description { get; set; }
        public byte[]? Image { get; set; }
        public DateTime? ExpirationDate { get; set; }
        public Unit? Unit { get; set; }

    }

    public enum Unit
    {
        PIECE = 0,
        KILOGRAM = 1,
        LITER = 2
    }
}
