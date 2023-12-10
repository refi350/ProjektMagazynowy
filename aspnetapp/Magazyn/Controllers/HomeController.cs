using Magazyn.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Magazyn.Controllers
{
    [Route("/")]
    [ApiController]
    public class HomeController : Controller
    {

        public HomeController() 
        {


        }

        [HttpGet]
        // GET: TowarController
        public IActionResult Index()
        {
            return View();
        }

    }
}
