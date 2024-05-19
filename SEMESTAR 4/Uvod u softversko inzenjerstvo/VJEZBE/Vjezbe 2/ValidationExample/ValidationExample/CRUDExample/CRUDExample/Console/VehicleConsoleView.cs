using CRUDExample.Model;
using CRUDExample.Model.DAO;

namespace CRUDExample.Console;

/*
 * Klasa koju koristimo za interakciju sa korisnikom.
 * Ova klasa sadrži glavnu petlju programa, i učitava
 * i ispisuje podatke korisniku.
 * Ova klasa NE TREBA da sadrži poslovnu logiku!
 * Sva poslovna logika se nalazu u DAO klasi i ova
 * klasa delegira DAO klasi svu poslovnu logiku i
 * prosleđuje joj sve potrebne podatke.
 */
class VehicleConsoleView
{
    private readonly VehiclesDAO _vehiclesDao;

    public VehicleConsoleView(VehiclesDAO vehiclesDao)
    {
        _vehiclesDao = vehiclesDao;
    }
    
    public void RunMenu()
    {
        while (true)
        {
            ShowMenu();
            string userInput = System.Console.ReadLine() ?? "0";
            if (userInput == "0") break;
            HandleMenuInput(userInput);
        }
    }

    private void ShowMenu()
    {
        System.Console.WriteLine("\nChoose an option: ");
        System.Console.WriteLine("1: Show All vehicles");
        System.Console.WriteLine("2: Add vehicle");
        System.Console.WriteLine("3: Update vehicle");
        System.Console.WriteLine("4: Remove vehicle");
        System.Console.WriteLine("5: Show and sort vehicles");
        System.Console.WriteLine("0: Close");
    }
    
    private void HandleMenuInput(string input)
    {
        switch (input)
        {
            case "1":
                ShowAllVehicles();
                break;
            case "2":
                AddVehicle();
                break;
            case "3":
                UpdateVehicle();
                break;
            case "4":
                RemoveVehicle();
                break;
            case "5":
                ShowAndSortVehicles();
                break;
        }
    }
    
    private void ShowAllVehicles()
    {
        PrintVehicles(_vehiclesDao.GetAllVehicles());
    }

    private void PrintVehicles(List<Vehicle> vehicles)
    {
        System.Console.WriteLine("Vehicles: ");
        string header = $"ID {"",6} | Name {"",21} | NumberOfWheels {"",6} |";
        System.Console.WriteLine(header);
        foreach (Vehicle v in vehicles)
        {
            System.Console.WriteLine(v);
        }
    }

    private void AddVehicle()
    {
        Vehicle vehicle = InputVehicle();
        _vehiclesDao.AddVehicle(vehicle);
        System.Console.WriteLine("Vehicle added");
    }

    private Vehicle InputVehicle()
    {
        System.Console.WriteLine("Enter vehicle name: ");
        string name = System.Console.ReadLine() ?? string.Empty;

        System.Console.WriteLine("Enter No. of wheels: ");
        int wheels = ConsoleViewUtils.SafeInputInt();

        return new Vehicle(name, wheels);
    }

    private void UpdateVehicle()
    {
        int id = InputId();
        Vehicle vehicle = InputVehicle();
        vehicle.Id = id;
        Vehicle? updatedVehicle = _vehiclesDao.UpdateVehicle(vehicle);
        if (updatedVehicle == null)
        {
            System.Console.WriteLine("Vehicle not found");
            return;
        }

        System.Console.WriteLine("Vehicle updated");
    }
    
    private int InputId()
    {
        System.Console.WriteLine("Enter vehicle id: ");
        return ConsoleViewUtils.SafeInputInt();
    }

    private void RemoveVehicle()
    {
        int id = InputId();
        Vehicle? removedVehicle = _vehiclesDao.RemoveVehicle(id);
        if (removedVehicle is null)
        {
            System.Console.WriteLine("Vehicle not found");
            return;
        }

        System.Console.WriteLine("Vehicle removed");
    }

    private void ShowAndSortVehicles()
    {
        System.Console.WriteLine("\nEnter page: ");
        int page = ConsoleViewUtils.SafeInputInt();
        System.Console.WriteLine("\nEnter page size: ");
        int pageSize = ConsoleViewUtils.SafeInputInt();
        System.Console.WriteLine("\nEnter sort criteria: ");
        string sortCriteria = System.Console.ReadLine() ?? string.Empty;
        System.Console.WriteLine("\nEnter 0 for ascending, any key for descending: ");
        int sortDirectionInput = ConsoleViewUtils.SafeInputInt();
        SortDirection sortDirection = sortDirectionInput == 0 ? SortDirection.Ascending : SortDirection.Descending;

        PrintVehicles(_vehiclesDao.GetAllVehicles(page, pageSize, sortCriteria, sortDirection));
    }

}