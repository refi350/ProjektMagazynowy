using Magazyn.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace Magazyn.Controllers
{
    public class WarehousesController : Controller
    {
        private readonly HttpClient _httpClient;

        public WarehousesController(HttpClient httpClient)
        {
            _httpClient = httpClient ?? throw new ArgumentNullException(nameof(httpClient));
        }

        // GET: WarehousesController
        public async Task<IActionResult> Index()
        {
            try
            {
                var response = await _httpClient.GetAsync("http://monika.alwaysdata.net/warehouses/all");
                if (!response.IsSuccessStatusCode)
                {
                    return NotFound();
                }
                var content = await response.Content.ReadAsStringAsync();
                var model = JsonConvert.DeserializeObject<List<Warehouse>>(content);

                // Przekazanie modelu do widoku
                return View(model);
            }
            catch (Exception)
            {
                return NotFound();
            }
        }

        // GET: WarehousesController/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: WarehousesController/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: WarehousesController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: WarehousesController/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: WarehousesController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id, IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: WarehousesController/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: WarehousesController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(int id, IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }
    }
}
