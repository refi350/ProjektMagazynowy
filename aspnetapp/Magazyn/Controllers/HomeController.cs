using Magazyn.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Magazyn.Controllers
{
    public class HomeController : Controller
    {

        public HomeController() 
        {


        }

        // GET: TowarController
        public IActionResult Index()
        {
            return View();
        }

    }
}
