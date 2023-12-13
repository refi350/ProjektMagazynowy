using Magazyn.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Text;

namespace Magazyn.Controllers
{
    /// <summary>
    /// Controller for setting up Contractors
    /// </summary>
    [ApiController]
    [Route("/Contractors/")]
    public class ContractorsController : Controller
    {
        private readonly HttpClient _httpClient;
        /// <summary>
        /// Configure HttpClient to allow Access to a server
        /// </summary>
        /// <param name="httpClient">HttpClient used by the builder to connect to database</param>
        /// <exception cref="ArgumentNullException"></exception>
        public ContractorsController(HttpClient httpClient)
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
        /// GET: Contractors
        /// Gets a list of all Contractors inside a specific warehouse
        /// </summary>
        /// <param name="WarehouseId">unique id of a warehouse</param>
        /// <returns></returns>
        // GET: Contractors
        [HttpGet]
        [Route("/Contractors/Index/")]
        public async Task<IActionResult> Index(int WarehouseId)
        {
            try
            {
                HttpContext.Session.SetInt32("WareId", WarehouseId);
                var model = await HttpToModel<List<Contractor>>("http://monika.alwaysdata.net/warehouses/" + HttpContext.Session.GetInt32("WareId").ToString() + "/contractors/all");
                if (model == null)
                {
                    return View(new List<Contractor>());
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
        /// GET: Contractors/Details/5
        /// Accesses details of a specific database through their id
        /// </summary>
        /// <param name="id">unique id of a Contractor</param>
        /// <returns></returns>
        // GET: Contractors/Details/5
        [HttpGet]
        [Route("/Contractors/Details/")]
        public async Task<IActionResult> Details(int? id)
        {
            try
            {
                var model = await HttpToModel<Contractor>("http://monika.alwaysdata.net/contractors/" + id.ToString());
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
        /// GET: Contractors/Create
        /// Opens a Create view
        /// </summary>
        /// <returns></returns>
        // GET: Contractors/Create
        [HttpGet]
        [Route("/Contractors/Create/")]
        public IActionResult Create()
        {
            return View();
        }
        /// <summary>
        /// POST: Contractors/Create
        /// Creates a new Contractor based on data filled in Create view
        /// </summary>
        /// <param name="Contractor"></param>
        /// <returns></returns>
        // POST: Contractors/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Route("/Contractors/Create/")]
        public async Task<IActionResult> Create([FromForm][Bind("Id,Name,Counter,TempCounter,Code,Description")] Contractor Contractor)
        {
            try
            {
                var jsonContractor = JsonConvert.SerializeObject(Contractor);
                var content = new StringContent(jsonContractor, Encoding.UTF8, "application/json");

                var postResponse = await _httpClient.PostAsync("http://monika.alwaysdata.net/warehouses/" + HttpContext.Session.GetInt32("WareId").ToString() + "/contractors", content);
                if (postResponse.IsSuccessStatusCode)
                {
                    return RedirectToAction(nameof(Index), new { warehouseId = HttpContext.Session.GetInt32("WareId") ?? default });
                }
                else
                {
                    // Wystąpił błąd podczas dodawania danych
                    Console.WriteLine(postResponse);
                    return View(Contractor);
                }
            }
            catch
            {
                return View();
            }
        }
        /// <summary>
        ///  GET: Contractors/Edit/5
        ///  Opens an Edit View of an Contractor based on their id
        /// </summary>
        /// <param name="id">unique id of a Contractor</param>
        /// <returns></returns>
        // GET: Contractors/Edit/5
        [HttpGet]
        [Route("/Contractors/Edit/")]
        public async Task<IActionResult> Edit(int? id)
        {
            try
            {
                var model = await HttpToModel<Contractor>("http://monika.alwaysdata.net/contractors/" + id.ToString());
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
        /// POST: Contractors/Edit/5
        /// Posts an updated version of a Contractor to a database
        /// </summary>
        /// <param name="id">unique id of a Contractor</param>
        /// <param name="Contractor">modified Contractor</param>
        /// <returns></returns>
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Route("/Contractors/Edit/")]
        public async Task<IActionResult> Edit([FromForm] [Bind("Id,Name,Counter,TempCounter,Code,Description")] Contractor Contractor)
        {
            try
            {
                // W tym miejscu możesz dokonać modyfikacji obiektu przed wysłaniem go na serwer,
                // na przykład używając JsonConvert.SerializeObject(editedWarehouse)

                var jsonWarehouse = JsonConvert.SerializeObject(Contractor);
                var content = new StringContent(jsonWarehouse, Encoding.UTF8, "application/json");

                var putResponse = await _httpClient.PutAsync("http://monika.alwaysdata.net/contractors/" + Contractor.Id.ToString(), content);

                if (putResponse.IsSuccessStatusCode)
                {
                    return RedirectToAction(nameof(Index), new { warehouseId = HttpContext.Session.GetInt32("WareId") ?? default });
                }
                else
                {
                    // Wystąpił błąd podczas edycji danych
                    Console.WriteLine(putResponse);
                    return View(Contractor);
                }
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        /// <summary>
        /// GET: Contractors/Delete/5
        /// Opens a Delete view
        /// </summary>
        /// <param name="id">unique id of a Contractor</param>
        /// <returns></returns>
        [HttpGet]
        [Route("/Contractors/Delete/")]
        public async Task<IActionResult> Delete(int? id)
        {
            try
            {
                var model = await HttpToModel<Contractor>("http://monika.alwaysdata.net/contractors/" + id.ToString());
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
        /// POST: Contractors/Delete/5
        /// Deletes a Contractor from a database
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        [Route("/Contractors/Delete/")]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            try
            {
                var deleteResponse = await _httpClient.DeleteAsync("http://monika.alwaysdata.net/contractors/" + id);

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
