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

        public DbSet<Warehouse>? Warehouse { get; set; }
        public DbSet<Owner>? Owners { get; set; }
        public DbSet<Address>? Address { get; set; }
    }
}
