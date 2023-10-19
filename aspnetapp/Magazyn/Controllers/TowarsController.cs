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
    public class TowarsController : Controller
    {
        private readonly MagazynContext _context;

        public TowarsController(MagazynContext context)
        {
            _context = context;
        }

        // GET: Towars
        public async Task<IActionResult> Index()
        {
              return View(await _context.Towar.ToListAsync());
        }

        // GET: Towars/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null || _context.Towar == null)
            {
                return NotFound();
            }

            var towar = await _context.Towar
                .FirstOrDefaultAsync(m => m.Id == id);
            if (towar == null)
            {
                return NotFound();
            }

            return View(towar);
        }

        // GET: Towars/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Towars/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Name,Price")] Towar towar)
        {
            if (ModelState.IsValid)
            {
                _context.Add(towar);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(towar);
        }

        // GET: Towars/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null || _context.Towar == null)
            {
                return NotFound();
            }

            var towar = await _context.Towar.FindAsync(id);
            if (towar == null)
            {
                return NotFound();
            }
            return View(towar);
        }

        // POST: Towars/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,Name,Price")] Towar towar)
        {
            if (id != towar.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(towar);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!TowarExists(towar.Id))
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
            return View(towar);
        }

        // GET: Towars/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null || _context.Towar == null)
            {
                return NotFound();
            }

            var towar = await _context.Towar
                .FirstOrDefaultAsync(m => m.Id == id);
            if (towar == null)
            {
                return NotFound();
            }

            return View(towar);
        }

        // POST: Towars/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (_context.Towar == null)
            {
                return Problem("Entity set 'MagazynContext.Towar'  is null.");
            }
            var towar = await _context.Towar.FindAsync(id);
            if (towar != null)
            {
                _context.Towar.Remove(towar);
            }
            
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool TowarExists(int id)
        {
          return _context.Towar.Any(e => e.Id == id);
        }
    }
}
