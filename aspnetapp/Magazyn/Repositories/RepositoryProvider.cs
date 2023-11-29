using Magazyn.Interfaces;
using System.Net.Http.Headers;
using System.Text.Json;

namespace Magazyn.Repositories
{
    public class RepositoryProvider : IRepositoryProvider
    {
        private readonly HttpClient _client;
        public RepositoryProvider(HttpClient client)
        {
            _client = client;
            ConfigureHttpClient();
        }
        public async Task<List<WarehouseRepository>> GetWarehousesAsync()
        {
            await using Stream stream = await _client.GetStreamAsync("http://monika.alwaysdata.net/warehouses/all");
            return await JsonSerializer.DeserializeAsync<List<WarehouseRepository>>(stream) ?? new List<WarehouseRepository>();
        }
        // typ T nie działa, a chce, żeby był generyczny
        //public async Task<List<T>> GetRepositoryHttpAsync(string http)
        //{
        //    await using Stream stream = await _client.GetStreamAsync(http);
        //    return await JsonSerializer.DeserializeAsync<List<T>>(stream) ?? new ();
        //}
        private void ConfigureHttpClient()
        {
            _client.DefaultRequestHeaders.Accept.Clear();
            _client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/vnd.github.v3+json"));
            _client.DefaultRequestHeaders.Add("User-Agent", ".NET Foundation Repository Reporter");
        }
    }
    
}
