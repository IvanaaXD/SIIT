using CRUDExample.Model;
using CRUDExample.Storage;
using CRUDExample.Observer;

namespace CRUDExample.Model.DAO;

/*
 * Klasa koju koristimo za pristup podacima (Data Access Object).
 * Ova klasa enkapsulira listu Vehicle objekata i pruža metode
 * za dodavanje, izmenu, brisanje i pregled Vehicle objekata.
 * Dodatno ova klasa koristi VehicleStorage za učitavanje i čuvanje objekata.
 */
public class VehiclesDAO
{
    private readonly List<Vehicle> _vehicles;
    private readonly Storage<Vehicle> _storage;


    public Subject VehicleSubject;

    public VehiclesDAO()
    {
        _storage = new Storage<Vehicle>("vehicles.txt");
        _vehicles = _storage.Load();
        VehicleSubject = new Subject();
    }

    private int GenerateId()
    {
        if (_vehicles.Count == 0) return 0;
        return _vehicles[^1].Id + 1;
    }

    public Vehicle AddVehicle(Vehicle vehicle)
    {
        vehicle.Id = GenerateId();
        _vehicles.Add(vehicle);
        _storage.Save(_vehicles);
        VehicleSubject.NotifyObservers();
        return vehicle;
    }

    public Vehicle? UpdateVehicle(Vehicle vehicle)
    {
        Vehicle? oldVehicle = GetVehicleById(vehicle.Id);
        if (oldVehicle is null) return null;

        oldVehicle.NumberOfWheels = vehicle.NumberOfWheels;
        oldVehicle.Name = vehicle.Name;

        _storage.Save(_vehicles);
        VehicleSubject.NotifyObservers();
        return oldVehicle;
    }

    public Vehicle? RemoveVehicle(int id)
    {
        Vehicle? vehicle = GetVehicleById(id);
        if (vehicle == null) return null;

        _vehicles.Remove(vehicle);
        _storage.Save(_vehicles);
        VehicleSubject.NotifyObservers();
        return vehicle;
    }

    private Vehicle? GetVehicleById(int id)
    {
        return _vehicles.Find(v => v.Id == id);
    }

    public List<Vehicle> GetAllVehicles()
    {
        return _vehicles;
    }

    public List<Vehicle> GetAllVehicles(int page, int pageSize, string sortCriteria, SortDirection sortDirection)
    {
        IEnumerable<Vehicle> vehicles = _vehicles;

        // sortiraj vehicles ukoliko je sortCriteria naveden
        switch (sortCriteria)
        {
            case "Id":
                vehicles = _vehicles.OrderBy(x => x.Id);
                break;
            case "Name":
                vehicles = _vehicles.OrderBy(x => x.Name);
                break;
            case "NumberOfWheels":
                vehicles = _vehicles.OrderBy(x => x.NumberOfWheels);
                break;
        }

        // promeni redosled ukoliko ima potrebe za tim
        if (sortDirection == SortDirection.Descending)
            vehicles = vehicles.Reverse();

        // paginacija
        vehicles = vehicles.Skip((page - 1) * pageSize).Take(pageSize);

        return vehicles.ToList();
    }
}