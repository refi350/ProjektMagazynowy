using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Magazyn.Models;
using System.Drawing;

namespace Magazyn.Data
{
    public class MagazynContext : DbContext
    {
        public MagazynContext (DbContextOptions<MagazynContext> options)
            : base(options)
        {
        }



        public DbSet<Magazyn.Models.Towar> Towar { get; set; } = default!;

        public DbSet<Magazyn.Models.Warehouse>? Warehouse { get; set; }
    }
}
