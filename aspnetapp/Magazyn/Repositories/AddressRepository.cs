namespace Magazyn.Repositories
{
    public record class AddressRepository(int id, string street_name,string house_number, int local_number, string place, string code);
}
