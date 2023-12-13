using Magazyn.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Text;

namespace Magazyn.Controllers
{/// <summary>
/// Controler for setting up Warehouses
/// </summary>
    [Route("/Warehouses/")]
    [ApiController]
    public class WarehousesController : Controller
    {
        private readonly HttpClient _httpClient;
        /// <summary>
        /// Configure HttpClient to allow Access to a server
        /// </summary>
        /// <param name="httpClient">HttpClient used by the builder to connect to database</param>
        /// <exception cref="ArgumentNullException"></exception>
        public WarehousesController(HttpClient httpClient)
        {
            _httpClient = httpClient ?? throw new ArgumentNullException(nameof(httpClient));
        }
        /// <summary>
        /// Converts chosen address of a database to a model
        /// </summary>
        /// <typeparam name="T">database type</typeparam>
        /// <param name="address">address to a database</param>
        /// <returns>T version of a database in a form of a model</returns>
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
        /// <summary>
        /// GET: WarehousesController
        /// Accesses a view of all Warehouses
        /// </summary>
        /// <returns></returns>
        // GET: WarehousesController
        [Route("/Warehouses/Index/")]
        [HttpGet]
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
        /// <summary>
        /// GET: WarehousesController/Details/5
        /// Accesses details of a specific database through their id
        /// </summary>
        /// <param name="id">unique id of a Warehouse</param>
        /// <returns></returns>
        // GET: WarehousesController/Details/5
        [Route("/Warehouses/Details/")]
        [HttpGet]
        public async Task<IActionResult> Details([FromQuery]int id)
        {
            try
            {
                var model = await HttpToModel<Warehouse>("http://monika.alwaysdata.net/warehouses/"+id.ToString());
                HttpContext.Session.SetString("BgColor", model!.ColorId!);
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
        /// <summary>
        /// GET: WarehousesController/Create
        /// Opens a Create view
        /// </summary>
        /// <returns></returns>
        // GET: WarehousesController/Create
        [Route("/Warehouses/Create/")]
        [HttpGet]
        public IActionResult Create()
        {
            return View();
        }
        /// <summary>
        /// POST: WarehousesController/Create
        /// Creates a new warehouse based on data filled in Create view
        /// </summary>
        /// <param name="newWarehouse"></param>
        /// <returns></returns>
        // POST: WarehousesController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Route("/Warehouses/Create/")]
        public async Task<IActionResult> Create([FromForm]Warehouse newWarehouse)
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
        /// <summary>
        /// GET: WarehousesController/Edit/5
        /// Opens an Edit View of an warehouse based on their id
        /// </summary>
        /// <param name="id">unique id of a warehouse</param>
        /// <returns></returns>
        // GET: WarehousesController/Edit/5
        [Route("/Warehouses/Edit/")]
        [HttpGet]
        public async Task<IActionResult> Edit([FromQuery]int id)
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
        /// <summary>
        /// POST: WarehousesController/Edit/5
        /// Posts an updated version of a warehouse to a database
        /// </summary>
        /// <param name="id">unique id of a warehouse</param>
        /// <param name="editedWarehouse">modified warehouse</param>
        /// <returns></returns>
        // POST: WarehousesController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Route("/Warehouses/Edit/")]
        public async Task<IActionResult> Edit([FromForm] Warehouse editedWarehouse)
        {
            try
            {
                // W tym miejscu możesz dokonać modyfikacji obiektu przed wysłaniem go na serwer,
                // na przykład używając JsonConvert.SerializeObject(editedWarehouse)

                var jsonWarehouse = JsonConvert.SerializeObject(editedWarehouse);
                var content = new StringContent(jsonWarehouse, Encoding.UTF8, "application/json");

                var putResponse = await _httpClient.PutAsync("http://monika.alwaysdata.net/warehouses/" + editedWarehouse.Id.ToString(), content);

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
        /// <summary>
        /// GET: WarehousesController/Delete/5
        /// Opens a Delete view
        /// </summary>
        /// <param name="id">unique id of a warehouse</param>
        /// <returns></returns>
        // GET: WarehousesController/Delete/5
        [Route("/Warehouses/Delete/")]
        [HttpGet]
        public async Task<IActionResult> Delete([FromQuery] int id)
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
        /// <summary>
        /// POST: WarehousesController/Delete/5
        /// Deletes a warehouse from a database
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        // POST: WarehousesController/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        [Route("/Warehouses/Delete/")]
        public async Task<IActionResult> DeleteConfirmed([FromQuery]int id)
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
