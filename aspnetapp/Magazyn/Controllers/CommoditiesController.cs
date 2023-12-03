using Microsoft.AspNetCore.Mvc;
using Magazyn.Models;
using Newtonsoft.Json;
using System.Text;

namespace Magazyn.Controllers
{
    public class CommoditiesController : Controller
    {
        private readonly HttpClient _httpClient;
        public CommoditiesController(HttpClient httpClient) { 
            _httpClient = httpClient ?? throw new ArgumentNullException(nameof(httpClient));
        }

        private async Task<T?> HttpToModel<T>(string address)
        {
            var response = await _httpClient.GetAsync(address);
            if (!response.IsSuccessStatusCode)
            {
                return default;
            }
            var content = await response.Content.ReadAsStringAsync();
            var model = JsonConvert.DeserializeObject<T>(content);
            return model;
        }

        // GET: Commodities
        public async Task<IActionResult> Index(int WarehouseId)
        {
            try
            {
                var model = await HttpToModel<List<Commodity>>("http://monika.alwaysdata.net/warehouses/" + WarehouseId.ToString() + "/commodities/all");
                if (model == null)
                {
                    model = new List<Commodity>();
                    //Trzeba przypisać model do Commodity w tym wypadku
                }

                // Przekazanie modelu do widoku
                return View(model);
            }
            catch (Exception)
            {
                return NotFound();
            }
        }

        // GET: Commodities/Details/5
        public async Task<IActionResult> Details(int WarehouseId,int? id)
        {
            try
            {
                var model = await HttpToModel<Commodity>("http://monika.alwaysdata.net/warehouses/" + WarehouseId.ToString()+"/commodieties/"+id.ToString());
                if (model == null)
                {
                    return View("Index");
                }
                return View(model);
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        // GET: Commodities/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Commodities/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Name,Counter,TempCounter,Code,Description")] Commodity commodity,int WarehouseId)
        {
            try
            {
                var jsonCommodity = JsonConvert.SerializeObject(commodity);
                var content = new StringContent(jsonCommodity, Encoding.UTF8, "application/json");

                var postResponse = await _httpClient.PostAsync("http://monika.alwaysdata.net/warehouses/"+WarehouseId.ToString()+"/commodities", content);
                if (postResponse.IsSuccessStatusCode)
                {
                    return RedirectToAction(nameof(Index));
                }
                else
                {
                    // Wystąpił błąd podczas dodawania danych
                    Console.WriteLine(postResponse);
                    return View(commodity);
                }
            }
            catch
            {
                return View();
            }
        }

        // GET: Commodities/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            return View();
        }

        // POST: Commodities/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,Name,Counter,TempCounter,Code,Description,Image,ExpirationDate,Unit")] Commodity commodity)
        {
            return View();
        }

        // GET: Commodities/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            return View();
        }

        // POST: Commodities/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            return View();
        }
    }
}
