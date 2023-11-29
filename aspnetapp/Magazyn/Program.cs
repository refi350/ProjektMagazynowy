using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using Magazyn.Data;
using System.Net.Http.Headers;
using System.Text.Json;

//Repository management
//using HttpClient client = new();
//client.DefaultRequestHeaders.Accept.Clear();
//client.DefaultRequestHeaders.Accept.Add(
//    new MediaTypeWithQualityHeaderValue("application/vnd.github.v3+json"));
//client.DefaultRequestHeaders.Add("User-Agent", ".NET Foundation Repository Reporter");

//// Jak moge udostêpniæ to zewnêtrznemu skryptowi, bez nadawania mu typu public
//List<WarehouseRepository> repositories = await ProcessRepositoryAsync(client);

//static async Task<List<WarehouseRepository>> ProcessRepositoryAsync(HttpClient client)
//{
//    //HTTP GET do okreœlonego identyfikatora URI
//    //Metoda uzyskania dostêpu do danych przy u¿yciu strumienia
//    await using Stream stream =
//        await client.GetStreamAsync("http://monika.alwaysdata.net/warehouses/all");

//    //To przyjmuje repozytorium w formie streamu i zgodnie z plikiem Repository pobiera pole Name.
//    //Jak chce wiêcej to muszê dodaæ wiêcej zgodnych pól, ale jako tako dzia³a
//    var repositories =
//        await JsonSerializer.DeserializeAsync<List<WarehouseRepository>>(stream);

//    //Debug stuff
//    //foreach (var repo in repositories ?? Enumerable.Empty<WarehouseRepository>())
//    //{
//    //    Console.WriteLine(repo.id + "\t" + repo.name + "\t" + repo.password + "\t" + repo.color);
//    //}
//    return repositories ?? new();
//}

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorPages();
builder.Services.AddDbContext<MagazynContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("MagazynContext") ?? throw new InvalidOperationException("Connection string 'MagazynContext' not found.")));
builder.Services.AddHttpClient();
//builder.Services.AddSingleton<IRepositoryProvider,RepositoryProvider>();
var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}
app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapRazorPages();

app.Run();


