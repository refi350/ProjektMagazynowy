using Magazyn.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Magazyn.Controllers
{
    public class HomeController : Controller
    {
        //Tymczasowe, zmienić, kiedy będzie dostępna baza danych
        private readonly List<Towar> _towary;
        public HomeController() 
        {
            _towary = new List<Towar>
            {
                new Towar { Id = 1 ,Name = "Towar 1", Price = 10.99M},
                new Towar { Id = 2 , Name = "Towar 2", Price = 19.99M},
                new Towar { Id = 3 , Name = "Towar 3", Price = 5.99M}
            };

        }

        // GET: TowarController
        public IActionResult Index()
        {
            return View(_towary);
        }

    }
}
