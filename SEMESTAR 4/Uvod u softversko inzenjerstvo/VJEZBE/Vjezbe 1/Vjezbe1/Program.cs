using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Vjezbe1
{
    class Program
    {
        static void Main(string[] args)
        {
            List<Car> cars = LoadCarsFromJson("../../../../cars.json");

            Console.WriteLine("Age of each car:");
            foreach (var car in cars)
            {
                int age = DateTime.Now.Year - car.GodinaProizvodnje;
                Console.WriteLine($"- {car.Marka} {car.Model}: {age} years old");
            }

            var recentCars = cars.Where(car => DateTime.Now.Year - car.GodinaProizvodnje <= 5)
                                 .Select(car => $"{car.Marka} {car.Model}")
                                 .ToList();
            Console.WriteLine("\nCars that are 5 years old or less:");
            foreach (var car in recentCars)
            {
                Console.WriteLine("- " + car);
            }

            double averageAge = cars.Select(car => DateTime.Now.Year - car.GodinaProizvodnje).Average();
            Console.WriteLine($"\nAverage age of cars: {averageAge:F2} years");
        }

        static List<Car> LoadCarsFromJson(string filePath)
        {
            string json = File.ReadAllText(filePath);

            JsonSerializerSettings settings = new JsonSerializerSettings
            {
                PreserveReferencesHandling = PreserveReferencesHandling.Objects
            };
            List<Car> cars = JsonConvert.DeserializeObject<List<Car>>(json, settings);

            return cars;
        }
    }
}
