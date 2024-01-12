using Microsoft.AspNetCore.Mvc;
using Magazyn.Models;
using Newtonsoft.Json;
using System.Text;

namespace Magazyn.Controllers
{
    /// <summary>
    /// Controller for setting up Commodities
    /// </summary>
    [ApiController]
    [Route("/Commodities/")]
    public class CommoditiesController : Controller
    {
        private readonly HttpClient _httpClient;
        /// <summary>
        /// Configure HttpClient to allow Access to a server
        /// </summary>
        /// <param name="httpClient">HttpClient used by the builder to connect to database</param>
        /// <exception cref="ArgumentNullException"></exception>
        public CommoditiesController(HttpClient httpClient) { 
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
        /// GET: Commodities
        /// Gets a list of all commodities inside a specific warehouse
        /// </summary>
        /// <param name="WarehouseId">unique id of a warehouse</param>
        /// <returns></returns>
        // GET: Commodities
        [HttpGet]
        [Route("/Commodities/Index/")]
        public async Task<IActionResult> Index(int WarehouseId)
        {
            try
            {
                HttpContext.Session.SetInt32("WareId", WarehouseId);
                var model = await HttpToModel<List<Commodity>>("http://monika.alwaysdata.net/warehouses/" + HttpContext.Session.GetInt32("WareId").ToString() + "/commodities/all");               
                if (model == null)
                {
                    return View(new List<Commodity>());
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
        /// GET: Commodities/Details/5
        /// Accesses details of a specific database through their id
        /// </summary>
        /// <param name="id">unique id of a Commodity</param>
        /// <returns></returns>
        // GET: Commodities/Details/5
        [HttpGet]
        [Route("/Commodities/Details/")]
        public async Task<IActionResult> Details(int? id)
        {
            try
            {
                var model = await HttpToModel<Commodity>("http://monika.alwaysdata.net/commodities/"+id.ToString());
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
        /// <summary>
        /// GET: Commodities/Create
        /// Opens a Create view
        /// </summary>
        /// <returns></returns>
        // GET: Commodities/Create
        [HttpGet]
        [Route("/Commodities/Create/")]
        public IActionResult Create()
        {
            return View();
        }
        /// <summary>
        /// POST: Commodities/Create
        /// Creates a new Commodity based on data filled in Create view
        /// </summary>
        /// <param name="commodity"></param>
        /// <returns></returns>
        // POST: Commodities/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Route("/Commodities/Create/")]
        public async Task<IActionResult> Create([FromForm][Bind("Id,Name,Counter,TempCounter,Code,Description")] Commodity commodity)
        {
            try
            {
                var jsonCommodity = JsonConvert.SerializeObject(commodity);
                var content = new StringContent(jsonCommodity, Encoding.UTF8, "application/json");

                var postResponse = await _httpClient.PostAsync("http://monika.alwaysdata.net/warehouses/"+ HttpContext.Session.GetInt32("WareId").ToString() + "/commodities", content);
                if (postResponse.IsSuccessStatusCode)
                {
                    return RedirectToAction(nameof(Index), new {warehouseId = HttpContext.Session.GetInt32("WareId") ?? default});
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
        /// <summary>
        ///  GET: Commodities/Edit/5
        ///  Opens an Edit View of an Commodity based on their id
        /// </summary>
        /// <param name="id">unique id of a commodity</param>
        /// <returns></returns>
        // GET: Commodities/Edit/5
        [HttpGet]
        [Route("/Commodities/Edit/")]
        public async Task<IActionResult> Edit(int? id)
        {
            try
            {
                var model = await HttpToModel<Commodity>("http://monika.alwaysdata.net/commodities/" + id.ToString());
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
        /// POST: Commodities/Edit/5
        /// Posts an updated version of a commodity to a database
        /// </summary>
        /// <param name="id">unique id of a commodity</param>
        /// <param name="commodity">modified commodity</param>
        /// <returns></returns>
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Route("/Commodities/Edit/")]
        public async Task<IActionResult> Edit([FromForm][Bind("Id,Name,Counter,TempCounter,Code,Description")] Commodity commodity)
        {
            try
            {
                // W tym miejscu możesz dokonać modyfikacji obiektu przed wysłaniem go na serwer,
                // na przykład używając JsonConvert.SerializeObject(editedWarehouse)

                var jsonWarehouse = JsonConvert.SerializeObject(commodity);
                var content = new StringContent(jsonWarehouse, Encoding.UTF8, "application/json");

                var putResponse = await _httpClient.PutAsync("http://monika.alwaysdata.net/commodities/" + commodity.Id.ToString(), content);

                if (putResponse.IsSuccessStatusCode)
                {
                    return RedirectToAction(nameof(Index), new { warehouseId = HttpContext.Session.GetInt32("WareId") ?? default });
                }
                else
                {
                    // Wystąpił błąd podczas edycji danych
                    Console.WriteLine(putResponse);
                    return View(commodity);
                }
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        /// <summary>
        /// GET: Commodities/Delete/5
        /// Opens a Delete view
        /// </summary>
        /// <param name="id">unique id of a commodity</param>
        /// <returns></returns>
        [HttpGet]
        [Route("/Commodities/Delete/")]
        public async Task<IActionResult> Delete(int? id)
        {
            try
            {
                var model = await HttpToModel<Commodity>("http://monika.alwaysdata.net/commodities/" + id.ToString());
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
        /// POST: Commodities/Delete/5
        /// Deletes a commodity from a database
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        [Route("/Commodities/Delete/")]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            try
            {
                var deleteResponse = await _httpClient.DeleteAsync("http://monika.alwaysdata.net/commodities/" + id);

                if (deleteResponse.IsSuccessStatusCode)
                {
                    return RedirectToAction(nameof(Index), new { warehouseId = HttpContext.Session.GetInt32("WareId") ?? default });
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
