using Magazyn.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Text;

namespace Magazyn.Controllers
{
    public class WarehousesController : Controller
    {
        private readonly HttpClient _httpClient;

        public WarehousesController(HttpClient httpClient)
        {
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

        // GET: WarehousesController
        public async Task<IActionResult> Index()
        {
            try
            {
                var model = await HttpToModel<List<Warehouse>>("http://monika.alwaysdata.net/warehouses/all");
                if (model == null)
                {
                    return NotFound();
                }

                // Przekazanie modelu do widoku
                return View(model);
            }
            catch (Exception)
            {
                return NotFound();
            }
        }

        // GET: WarehousesController/Details/5
        public async Task<IActionResult> Details(int id)
        {
            try
            {
                var model = await HttpToModel<Warehouse>("http://monika.alwaysdata.net/warehouses/"+id.ToString());
                if (model == null)
                {
                    return View("Index");
                }
                return View(model);
            }
            catch(Exception)
            {
                return BadRequest();
            }
        }

        // GET: WarehousesController/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: WarehousesController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create(Warehouse newWarehouse)
        {
            try
            {
                var jsonWarehouse = JsonConvert.SerializeObject(newWarehouse);
                var content = new StringContent(jsonWarehouse, Encoding.UTF8, "application/json");

                var postResponse = await _httpClient.PostAsync("http://monika.alwaysdata.net/warehouses", content);
                if (postResponse.IsSuccessStatusCode)
                {
                    return RedirectToAction(nameof(Index));
                }
                else
                {
                    // Wystąpił błąd podczas dodawania danych
                    Console.WriteLine(postResponse);
                    return View(newWarehouse);
                }
            }
            catch
            {
                return View();
            }
        }

        // GET: WarehousesController/Edit/5
        public async Task<IActionResult> Edit(int id)
        {
            try
            {
                var model = await HttpToModel<Warehouse>("http://monika.alwaysdata.net/warehouses/" + id.ToString());
                if (model == null)
                {
                    return NotFound();
                }
                return View(model);
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        // POST: WarehousesController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, Warehouse editedWarehouse)
        {
            try
            {
                // W tym miejscu możesz dokonać modyfikacji obiektu przed wysłaniem go na serwer,
                // na przykład używając JsonConvert.SerializeObject(editedWarehouse)

                var jsonWarehouse = JsonConvert.SerializeObject(editedWarehouse);
                var content = new StringContent(jsonWarehouse, Encoding.UTF8, "application/json");

                var putResponse = await _httpClient.PutAsync("http://monika.alwaysdata.net/warehouses/" + id, content);

                if (putResponse.IsSuccessStatusCode)
                {
                    return RedirectToAction(nameof(Index));
                }
                else
                {
                    // Wystąpił błąd podczas edycji danych
                    Console.WriteLine(putResponse);
                    return View(editedWarehouse);
                }
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        // GET: WarehousesController/Delete/5
        public async Task<IActionResult> Delete(int id)
        {
            try
            {
                var model = await HttpToModel<Warehouse>("http://monika.alwaysdata.net/warehouses/" + id.ToString());
                if (model == null)
                {
                    return NotFound();
                }
                return View(model);
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        // POST: WarehousesController/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            try
            {
                var deleteResponse = await _httpClient.DeleteAsync("http://monika.alwaysdata.net/warehouses/" + id);

                if (deleteResponse.IsSuccessStatusCode)
                {
                    return RedirectToAction(nameof(Index));
                }
                else
                {
                    // Wystąpił błąd podczas usuwania danych
                    Console.WriteLine(deleteResponse);
                    return View(id); // lub inną odpowiednią akcję
                }
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }
    }
}
