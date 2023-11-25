using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Magazyn.Data;
using Magazyn.Models;
using Newtonsoft.Json;

namespace Magazyn.Controllers
{
    public class WarehousesControllerDepreciated : Controller
    {
        private readonly HttpClient _httpClient;

        public WarehousesControllerDepreciated(HttpClient httpClient)
        {
           _httpClient = httpClient ?? throw new ArgumentNullException(nameof(httpClient));
        }

        // GET: Warehouses
        public async Task<IActionResult> Index()
        {
            try
            {
                var response = await _httpClient.GetAsync("http://monika.alwaysdata.net/warehouses/all");
                if (!response.IsSuccessStatusCode)
                {
                    return View();
                }
                var content = await response.Content.ReadAsStringAsync();
                var model = JsonConvert.DeserializeObject<Warehouse>(content);

                // Przekazanie modelu do widoku
                return View(model);
            }
            catch (Exception)
            {
                return View();
            }
        }
        /*
        // GET: Warehouses/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null || _context.Warehouse == null)
            {
                return NotFound();
            }

            var warehouse = await _context.Warehouse
                .Include(w => w.Owner)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (warehouse == null)
            {
                return NotFound();
            }

            return View(warehouse);
        }

        // GET: Warehouses/Create
        public IActionResult Create()
        {
            
            ViewData["OwnerId"] = new SelectList(_context.Set<Owner>(), "Id", "Name");
            ViewData["AddressId"] = new SelectList(_context.Set<Address>(),"Id","StreetName");
            return View();
        }

        // POST: Warehouses/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Name,Password,Address," +
            "ColorId,OwnerId,Address.StreetName,Address.HouseNumber,Address.LocalNumber," +
            "Address.Place,Address.code")] Warehouse warehouse)
        {
            if (ModelState.IsValid)
            {

                _context.Add(warehouse);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["OwnerId"] = new SelectList(_context.Set<Owner>(), "Id", "Name", warehouse.OwnerId);
            return View(warehouse);
        }

        // GET: Warehouses/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null || _context.Warehouse == null)
            {
                return NotFound();
            }

            var warehouse = await _context.Warehouse.FindAsync(id);
            if (warehouse == null)
            {
                return NotFound();
            }
            ViewData["OwnerId"] = new SelectList(_context.Set<Owner>(), "Id", "Id", warehouse.OwnerId);
            return View(warehouse);
        }

        // POST: Warehouses/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,Name,ColorId,Image,OwnerId")] Warehouse warehouse)
        {
            if (id != warehouse.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(warehouse);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!WarehouseExists(warehouse.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            ViewData["OwnerId"] = new SelectList(_context.Set<Owner>(), "Id", "Id", warehouse.OwnerId);
            return View(warehouse);
        }

        // GET: Warehouses/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null || _context.Warehouse == null)
            {
                return NotFound();
            }

            var warehouse = await _context.Warehouse
                .Include(w => w.Owner)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (warehouse == null)
            {
                return NotFound();
            }

            return View(warehouse);
        }

        // POST: Warehouses/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (_context.Warehouse == null)
            {
                return Problem("Entity set 'MagazynContext.Warehouse'  is null.");
            }
            var warehouse = await _context.Warehouse.FindAsync(id);
            if (warehouse != null)
            {
                _context.Warehouse.Remove(warehouse);
            }
            
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool WarehouseExists(int id)
        {
          return (_context.Warehouse?.Any(e => e.Id == id)).GetValueOrDefault();
        }*/
    }
}
