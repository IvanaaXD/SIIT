using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;

namespace Vjezbe1
{
    public enum Fuel
    {
        Diesel,
        Gasoline
    }

    internal class Motor
    {
        public string Naziv { get; set; }
        public int Kubikaza { get; set; }
        public int Snaga { get; set; }

        public override string ToString()
        {
            return $"Name: {Naziv}, Cc: {Kubikaza}, Power: {Snaga}";
        }
    }

    internal class Car
    {
        public string Model { get; set; }
        public string Marka { get; set; }
        public string Boja { get; set; }
        public int GodinaProizvodnje { get; set; }
        public Fuel Goriva { get; set; }
        public Motor Motor { get; set; }

        public override string ToString()
        {
            return $"Make: {Marka}, Model: {Model}, Year: {GodinaProizvodnje}, Color: {Boja}, Fuel: {Goriva}, Motor: {Motor}";
        }
    }
}