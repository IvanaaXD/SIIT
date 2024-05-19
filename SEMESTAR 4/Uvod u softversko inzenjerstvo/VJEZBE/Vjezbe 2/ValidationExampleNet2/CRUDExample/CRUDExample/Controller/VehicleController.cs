using CRUDExample.Model;
using CRUDExample.Model.DAO;
using CRUDExample.Observer;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CRUDExample.Controller
{
    public class VehiclesController
    {
        private readonly VehiclesDAO _vehicles;

        public VehiclesController()
        {
            _vehicles = new VehiclesDAO();
        }

        public List<Vehicle> GetAllVehicles()
        {
            return _vehicles.GetAllVehicles();
        }

        public void Add(Vehicle vehicle)
        {
            _vehicles.AddVehicle(vehicle);
        }

        public void Delete(int vehicleId)
        {
            _vehicles.RemoveVehicle(vehicleId);
        }

        public void Subscribe(IObserver observer)
        {
            _vehicles.Subscribe(observer);
        }
    }
}
