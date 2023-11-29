using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Magazyn.Data;
using Magazyn.Models;

namespace Magazyn.Controllers
{
    public class CommoditiesController : Controller
    {

        public CommoditiesController() { 

        }

        // GET: Commodities
        public async Task<IActionResult> Index()
        {
            return View();
        }

        // GET: Commodities/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            return View();
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
        public async Task<IActionResult> Create([Bind("Id,Name,Counter,TempCounter,Code,Description,Image,ExpirationDate,Unit")] Commodity commodity)
        {
            return View();
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
