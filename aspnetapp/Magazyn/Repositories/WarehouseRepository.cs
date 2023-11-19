namespace Magazyn.Repositories
{
    public record class WarehouseRepository(int id,string name,string password,AddressRepository address, string color,OwnerRepository owner);

}
